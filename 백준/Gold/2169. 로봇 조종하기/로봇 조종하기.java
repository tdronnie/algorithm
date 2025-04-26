import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(detect(map, n, m));
    }

    static int detect(int[][] map, int n, int m) {
        int[][] dp = new int[n][m];
        //tmp[x][y][0]에는 왼쪽 -> 오른쪽 값
        //tmp[x][y][1]에는 오른쪽 -> 왼쪽 값
        int[][][] tmp = new int[n][m][2];

        dp[0][0] = map[0][0];

        //1행 먼저 계산, 왼쪽 -> 오른쪽 갱신만
        for (int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i-1] + map[0][i];
        }

        //2행부터 행 계산, 먼저 위 -> 아래 갱신, [i][1]부터 왼쪽 -> 오른쪽, [i][m-1]부터 오른쪽 -> 왼쪽 갱신
        for (int i = 1; i < n; i++) {
            //위 -> 아래
            for (int j = 0; j < m; j++) {
                tmp[i][j][0] = dp[i-1][j] + map[i][j];
                tmp[i][j][1] = dp[i-1][j] + map[i][j];
            }
            //왼쪽 -> 오른쪽
            for(int j=1; j<m; j++){
                tmp[i][j][0] = Math.max(tmp[i][j][0], tmp[i][j - 1][0] + map[i][j]);
            }
            //오른쪽 -> 왼쪽
            for(int j=m-2; j>=0; j--){
                tmp[i][j][1] = Math.max(tmp[i][j][1], tmp[i][j + 1][1] + map[i][j]);
            }
            //제일 큰 값으로 갱신
            for (int j = 0; j < m; j++) {
                dp[i][j] = Math.max(tmp[i][j][0], tmp[i][j][1]);
            }

        }

        return dp[n - 1][m - 1];
    }
}