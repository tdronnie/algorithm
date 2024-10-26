import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int[] orders;
    static int order = 1;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        orders = new int[N+1];

        for(int i=1; i<=N; i++){
            graph[i] = new ArrayList<>();
        }
        visited = new boolean[N+1];
        Arrays.fill(visited, false);

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        for(int i=1; i<=N; i++){
            Collections.sort(graph[i]);
        }

        dfs(R);

        for(int i=1; i<=N; i++){
            sb.append(orders[i]).append("\n");
        }
        System.out.print(sb);
    }

    public static void dfs(int start){
        visited[start] = true;
        orders[start] = order++;

        for(int i=0; i<graph[start].size(); i++){
            int node = graph[start].get(i);
            if(visited[node]) continue;
            dfs(node);
        }
    }
}