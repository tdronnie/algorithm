import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        int maxN = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                maxN = Math.max(maxN, map[i][j]);
            }
        }

        //높이 최댓값 내에서 물이 안찬 경우부터 물 차오르게 하면서 남은 안전지역의 개수 구하기
        int maxSection=0;
        for (int h = 0; h <= maxN; h++) {
            visited = new boolean[n][n];
            //방문하지 않았고 현재 높이보다 높은 경우 영역 탐색 시작
            int areaCnt = 0; //영역 개수
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j] && map[i][j] > h) {
                        areaCnt += dfs(i, j, h);
                    }
                }
            }
            maxSection = Math.max(maxSection, areaCnt);
        }
        System.out.println(maxSection);


    }
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    private static int dfs(int i, int j, int h) {
        visited[i][j] = true;

        for (int k = 0; k < 4; k++) {
            int newX = i + dx[k];
            int newY = j + dy[k];

            if(newX<0 || newY<0 || newX>=n || newY>=n) continue;
            if(visited[newX][newY]) continue;
            if(map[newX][newY] > h) dfs(newX, newY, h);
        }

        return 1;
    }




}