import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Barn {
    int to, road;

    Barn(int to, int road) {
        this.to = to;
        this.road = road;
    }
}
public class Main {

    static ArrayList<Barn>[] arr;
    static int[] minDis;
    static int n;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new ArrayList[n + 1];
        minDis = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }
        Arrays.fill(minDis, Integer.MAX_VALUE);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr[a].add(new Barn(b, c));
            arr[b].add(new Barn(a, c));
        }

        findMinFeed();
        System.out.println(minDis[n]);

    }

    static void findMinFeed() {
        PriorityQueue<Barn> pq = new PriorityQueue<>((a, b) -> a.road - b.road);
        boolean[] visited = new boolean[n + 1];
        pq.add(new Barn(1, 0));

        while (!pq.isEmpty()) {
            Barn curr = pq.poll();
            int to = curr.to;
            int road = curr.road;

            if(visited[to]) continue;
            visited[to] = true;

            for (Barn barn : arr[to]) {

                if (minDis[barn.to] > road + barn.road) {
                    minDis[barn.to] = road + barn.road;
                    pq.add(new Barn(barn.to, minDis[barn.to]));
                }
            }
        }
    }
}