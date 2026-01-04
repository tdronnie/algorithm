import java.io.*;
import java.util.*;

public class Main {

    static int[] arr;
    static int[] depth; // 방문 순서 저장
    static boolean[] visited; // 탐색 완료한 노드 여부
    static int cycleNode; //사이클에 속한 노드 수
    static int count; //방문 순서 카운터

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        for (int test = 0; test < t; test++) {
            int n = Integer.parseInt(br.readLine());
            arr = new int[n + 1];
            depth = new int[n + 1];
            visited = new boolean[n + 1];
            cycleNode = 0;
            count = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= n; i++) {
                arr[i] =Integer.parseInt(st.nextToken()); // 각 학생 한명만 지목
            }

            for (int i = 1; i <= n; i++) {
                if (depth[i] == 0) {
                    dfs(i);
                }
            }
            sb.append(n - cycleNode).append("\n");

        }
        System.out.print(sb);

    }

    static void dfs(int node) {
        depth[node] = ++count;
        int next = arr[node];

        if (depth[next] == 0) { // 방문하지 않은 노드
            dfs(next);
        } else if (!visited[next]) { // 방문 순서가 0 이상(재방문)이고 아직 탐색 완료되지 않았다면 사이클
            cycleNode += depth[node] - depth[next] + 1;
        }
        visited[node] = true; // 현재 노드 탐색 완료 처리
    }
}
