import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int N;
    static List<Integer>[] graph;
    static boolean[] visited;
    static int[] orders;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];

        for(int i=1; i<= N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0 ;i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            //양방향이므로 서로 추가
            graph[u].add(v);
            graph[v].add(u);
        }

        //각 노드 배열에 저장된 인접 노드들 오름차순으로 정렬
        for(int i=1; i<=N; i++){
            Collections.sort(graph[i]);
        }

        bfs(R);
        for (int i=1; i<=N; i++) {
            sb.append(orders[i]).append('\n');
        }
        System.out.print(sb);
    }

    private static void bfs(int start) {

        Queue<Integer> queue =new ArrayDeque<>();
        visited = new boolean[N+1];
        Arrays.fill(visited, false);
        orders = new int[N + 1];
        int order = 1;

        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            orders[node] = order++;
            for(int sideNode : graph[node]) {
                if(visited[sideNode]) continue;
                visited[sideNode] = true;
                queue.add(sideNode);
            }
        }
    }
}