import java.io.*;
import java.util.*;
public class Main {

    static int n;
    static List<Integer>[] arr;
    static int[] parent;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new ArrayList[n + 1];
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < n-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            arr[node1].add(node2);
            arr[node2].add(node1);
        }

        bfs();

        for (int i=2; i<=n; i++) {
            System.out.println(parent[i]);
        }

    }

    static void bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        q.add(1);
        visited[1] = true;

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int next : arr[node]) {
                if(visited[next]) continue;
                visited[next] = true;
                parent[next] = node; // 다음 노드의 부모를 현재 노드로
                q.add(next);
            }
        }
    }
}
