import java.io.*;
import java.util.*;

public class Main {

    static int n, x;
    static ArrayList<int[]>[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        arr = new ArrayList[n+1];
        int longestTime = 0;

        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            arr[start].add(new int[]{end, time});

        }


        for (int i = 1; i <= n; i++) {
            longestTime = Math.max(longestTime, findFastest(i, x) + findFastest(x, i));
        }

        System.out.println(longestTime);
    }

    static int findFastest(int start, int end) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int[] time = new int[n+1];
        Arrays.fill(time, Integer.MAX_VALUE);
        pq.add(new int[]{start, 0});
        time[start] = 0;

        while (!pq.isEmpty()) {
            int[] value = pq.poll();
            int pos = value[0];
            int t = value[1];

            if(time[pos] < t) continue;

            if(pos == end) return t;

            for (int i = 0; i < arr[pos].size(); i++) {
                int next = arr[pos].get(i)[0];
                int weight = arr[pos].get(i)[1];

                if (time[next] > t + weight) {
                    time[next] = t + weight;
                    pq.add(new int[]{next, time[next]});
                }
            }
        }

        return -1;
    }

}
