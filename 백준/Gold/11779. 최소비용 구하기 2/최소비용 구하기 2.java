import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Bus{
    int dest, cost;

    Bus(int dest, int cost) {
        this.dest = dest;
        this.cost = cost;
    }

    public int getDest() {
        return dest;
    }

    public int getCost() {
        return cost;
    }
}
public class Main {

    static int n, m;
    static ArrayList<Bus>[] arr;
    static int[] path;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        arr = new ArrayList[n+1];

        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList();
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            arr[start].add(new Bus(end, cost));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int goal = Integer.parseInt(st.nextToken());

        sb.append(findMinRoute(start, goal)).append("\n");
        ArrayList<Integer> route = new ArrayList<>();

        route.add(goal);
        while(true){
            int pre = path[goal];
            if(pre == -1){
                break;
            }
            route.add(pre);
            goal = pre;
        }

        sb.append(route.size()).append("\n");
        for (int i= route.size()-1; i>=0; i--) {
            sb.append(route.get(i)).append(" ");
        }

        System.out.println(sb);



    }

    static int findMinRoute(int start, int goal) {
        boolean[] visited = new boolean[n+1];
        int[] check = new int[n+1];
        Arrays.fill(check, Integer.MAX_VALUE);

        path = new int[n+1];
        Arrays.fill(path, -1);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int s = poll[0];
            int cost = poll[1];

            if(visited[s]) continue;
            visited[s] = true;

            if(s == goal){
                return cost;
            }

            for (int i = 0; i < arr[s].size(); i++) {
                int dest = arr[s].get(i).getDest();
                int nextCost = arr[s].get(i).getCost();

                int newCost = nextCost + cost;

                if(!visited[dest] && check[dest] > newCost){
                    check[dest] = newCost;

                    path[dest] = s;
                    pq.add(new int[]{dest, newCost});
                }
            }
        }
        return -1;
    }
}