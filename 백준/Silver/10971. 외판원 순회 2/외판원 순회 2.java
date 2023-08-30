import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int[][] costs;
    static boolean[] visited;
    static int n;
    static long totalCost = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine()); // 도시 수

        costs = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                costs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            visited = new boolean[n]; //도시 방문 배열 초기화
            dfs(i, i, 0);
        }

        System.out.println(totalCost);
    }

    public static void dfs(int from, int step, long cost) {
        visited[from] = true;

        //만약 모든 도시 방문했다면
        if (checkVisited()) {
            //마지막 도시에서 처음도시로 가는길 있는지부터 확인
            if (costs[step][from] != 0) {
                //마지막 도시에서 처음도시로 가는 비용 더해준 최종결과 얻기
                //최종 결과들의 중 최솟값이 정답
                totalCost = Math.min(totalCost, cost + costs[step][from]);
            }
            return;
        }

        //이후 도시에서 이어져있는 도시고 방문하지 않은 도시라면 방문
        for (int i = 0; i < n; i++) {
            if (costs[step][i] != 0 && !visited[i]) {
                visited[i] = true;
                dfs(from, i, cost + costs[step][i]); //지금까지의 비용과 현재 스텝의 도시까지의 비용 합쳐줘서 다음도시로
                visited[i] = false;//다른 경우의 수를 위한 방문배열 false 처리!!!
            }
        }
    }

    private static boolean checkVisited() {
        for (int i = 0; i < n; i++) {
            if (!visited[i])
                return false;
        }
        return true;
    }
}