import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Route {
    int dest, dis;

    Route(int dest, int cost) {
        this.dest = dest;
        this.dis = cost;
    }

    public int getDest() {
        return dest;
    }

    public int getDis() {
        return dis;
    }
}

public class Main {

    static int maxItem, totalMaxItem = Integer.MIN_VALUE;
    static ArrayList<Route>[] arr;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int[] item = new int[n+1];
        arr = new ArrayList[n+1];

        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            item[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<r; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            arr[a].add(new Route(b, l));
            arr[b].add(new Route(a, l));
        }

        for (int i = 1; i <= n; i++) {
            findMaxItem(i, m, n, item);
            totalMaxItem = Math.max(totalMaxItem, maxItem);
        }
        System.out.println(totalMaxItem);

    }

    static void findMaxItem(int spot, int m, int n, int[] item) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        boolean[] visited = new boolean[n+1];
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[spot] = 0;
        pq.add(new int[]{spot, 0, item[spot]});

        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int from = poll[0];
            int dis = poll[1];

            if(dis > m) continue;

            if(visited[from]) continue;
            visited[from] = true;

            for (int i = 0; i < arr[from].size(); i++) {
                int dest = arr[from].get(i).getDest();
                int nextDis = arr[from].get(i).getDis();

                int totalDis = dis + nextDis;

                if(!visited[dest] && m >= totalDis && distance[dest] > totalDis){
                    distance[dest] = totalDis;
                    pq.add(new int[]{dest, totalDis});
                }
            }
        }

        int totalItems = 0;
        for (int i = 1; i <= n; i++) {
            if (distance[i] <= m) {
                totalItems += item[i];
            }
        }
        maxItem = totalItems;
    }
}