import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, cnt;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        cnt = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //가로 0 세로 1 대각선 2
        dfs(0, 1, 0);

        System.out.println(cnt);

    }

    public static boolean isValid(int r, int c) {
        if (r >= n || c >= n || map[r][c] == 1)
            return false;
        return true;
    }

    public static void dfs(int r, int c, int op) {
        // 마지막 칸에 도착
        if (r == n - 1 && c == n - 1) {
            cnt++;
            return;
        }
        //가로
        if (op == 0) {
            if (isValid(r, c + 1)) { //가로로 가기
                dfs(r, c + 1, 0);
            }
        }
        //세로
        else if (op == 1) {
            if (isValid(r + 1, c)) { //세로로 가기
                dfs(r + 1, c, 1);
            }
        }
        //대각선
        else if(op == 2){
            if (isValid(r, c + 1)) { //가로로 가기
                dfs(r, c + 1, 0);
            }if (isValid(r + 1, c)) { //세로로 가기
                dfs(r + 1, c, 1);
            }
        }

        if (isValid(r, c + 1) && isValid(r + 1, c + 1) && isValid(r + 1, c)) { //대각선으로 가기
            dfs(r + 1, c + 1, 2);
        }
    }


}
