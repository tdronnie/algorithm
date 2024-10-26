import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static List<Integer>[] graph;
    static int[] orders;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        orders = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        for (int i=1; i<=N; i++) {
            Collections.sort(graph[i]);
            Collections.reverse(graph[i]);
        }

        bfs(R);

        for (int i = 1; i <= N; i++) {
            sb.append(orders[i]).append("\n");
        }

        System.out.print(sb);
    }

    public static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(visited, false);
        int order = 1;

        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            orders[node] = order++;
            for (int sideNode : graph[node]) {
                if (visited[sideNode]) continue;
                visited[sideNode] = true;
                queue.add(sideNode);
            }
        }
    }
}