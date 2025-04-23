import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Pair {
    int com, time;

    Pair(int com, int time) {
        this.com = com;
        this.time = time;
    }

    public int getCom() {
        return com;
    }

    public int getTime() {
        return time;
    }
}
public class Main {

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int test = Integer.parseInt(br.readLine());

        for (int t = 0; t < test; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            ArrayList<Pair>[] arr = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                arr[i] = new ArrayList<>();
            }

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                arr[b].add(new Pair(a, s));
            }

            int[] answer = hack(c, arr, n);
            System.out.println(answer[0] + " " + answer[1]);

        }
    }

    static int[] hack(int start, ArrayList<Pair>[] arr, int n) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.getTime() - b.getTime());
        int[] times = new int[n+1];

        Arrays.fill(times, Integer.MAX_VALUE);
        times[start] = 0;
        pq.add(new Pair(start, 0));

        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            int com = curr.com;
            int time = curr.time;

            if(times[com] < time ) continue;

            for(Pair next : arr[com]){
                int nextCom = next.com;
                int nextTime = next.time;

                if (times[nextCom] > times[com] + nextTime) {
                    times[nextCom] = times[com] + nextTime;
                    pq.add(new Pair(nextCom, times[nextCom]));
                }
            }
        }

        int hackedCount = 0;
        int totalTime = 0;
        for(int i=1; i<=n; i++){
            if(times[i] != Integer.MAX_VALUE){
                hackedCount++;
                totalTime = Math.max(totalTime, times[i]);
            }
        }
        return new int[]{hackedCount, totalTime};
    }
}