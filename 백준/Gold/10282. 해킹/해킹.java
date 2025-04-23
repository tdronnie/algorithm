import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class Dependency {
    int com, time;

    Dependency(int com, int time) {
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

            ArrayList<Dependency>[] arr = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                arr[i] = new ArrayList<>();
            }

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                arr[b].add(new Dependency(a, s));
            }

            int[] answer = hack(c, arr, n);
            System.out.println(answer[0] + " " + answer[1]);
        }
    }

    static int[] hack(int start, ArrayList<Dependency>[] arr, int n) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        boolean[] visited = new boolean[n + 1];
        int[] times = new int[n+1];
        Arrays.fill(times, Integer.MAX_VALUE);
        times[start] = 0;
        pq.add(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int com = poll[0];
            int time = poll[1];

            for (int i = 0; i < arr[com].size(); i++) {
                int nextCom = arr[com].get(i).getCom();
                int newTime = arr[com].get(i).getTime();

                if (times[nextCom] > time + newTime) {
                    times[nextCom] = time + newTime;
                    pq.add(new int[]{nextCom, time + newTime});
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