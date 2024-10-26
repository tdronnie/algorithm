import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static StringBuilder sb;
    static int N;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        for(int i=1; i<=N; i++){
            Collections.sort(graph[i]);
        }

        visited = new boolean[N+1];
        Arrays.fill(visited, false);

        dfs(V);

        visited = new boolean[N+1];
        Arrays.fill(visited, false);
        sb.append("\n");

        bfs(V);

        System.out.print(sb);

    }

    public static void dfs(int start){
        visited[start] = true;
        sb.append(start).append(" ");

        for(int node : graph[start]){
            if(visited[node]) continue;
            visited[node] = true;
            dfs(node);
        }
    }

    public static void bfs(int start){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        visited[start] = true;

        while(!q.isEmpty()){
            int node = q.poll();
            sb.append(node).append(" ");
            for(int neighbor : graph[node]){
                if(visited[neighbor]) continue;
                visited[neighbor] = true;
                q.add(neighbor);
            }
        }
    }
}