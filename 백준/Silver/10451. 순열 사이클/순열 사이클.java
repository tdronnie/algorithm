import java.io.*;
import java.util.*;

public class Main {

    static List<Integer>[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        for (int test = 0; test < t; test++) {
            int n = Integer.parseInt(br.readLine());
            arr = new ArrayList[n + 1];
            boolean[] visited = new boolean[n + 1];
            int count = 0;

            for (int i = 1; i <= n; i++) {
                arr[i] = new ArrayList<>();
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i].add(Integer.parseInt(st.nextToken()));
            }

            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    bfs(i, visited);
                    count++;
                }
            }

            sb.append(count).append("\n");
        }

        System.out.print(sb);
    }

    static void bfs(int start, boolean[] visited) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int current = q.poll();

            for (int next : arr[current]) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.add(next);
                }
            }
        }

    }
}
