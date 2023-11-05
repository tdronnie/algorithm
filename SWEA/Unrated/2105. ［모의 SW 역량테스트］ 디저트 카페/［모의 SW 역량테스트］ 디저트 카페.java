import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int n, startX, startY, dessert, max;
    static int[][] map;
    static boolean[] ate;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine()); //정사각형 한 변 길이
            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            visited = new boolean[n][n];
            max = -1;
            //최소 사각형 만들어질 수 있는 범위 내에서 탐색해주면서 사각형되는지 판단하기
            for (int i = 0; i < n - 2; i++) {
                for (int j = 1; j < n - 1; j++) {
                    ate = new boolean[101]; //이미 먹은 디저트, 1번부터 100번까지
                    dessert = 0;
                    //사각형 완성여부 보기 위한 시작좌표
                    startX = i;
                    startY = j;
                    //0 : 우하방향, 1 : 좌하방향, 2 : 좌상방향, 3 : 우상방향
                    search(i, j, 0);

                }
            }
            sb.append("#").append(tc).append(" ").append(max).append("\n");
        }
        System.out.println(sb.toString());
    }

    //시계방향 회전
    static int[] dx = {1, 1, -1, -1};
    static int[] dy = {1, -1, -1, 1};

    //dfs, 시계방향으로 한 턴만 돌려주기
    private static void search(int x, int y, int d) {
        visited[x][y] = true; //위치 방문처리
        ate[map[x][y]] = true; //디저트 방문 처리
        dessert++;
        for (int i = d; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            //범위 맞지 않은 경우 방향 바꾸기
            if (!isValid(nx, ny)) continue;
            //사각형이 만들어졌을 경우
            if (nx == startX && ny == startY && dessert >= 4) {
                max = Math.max(max, dessert);
                return;
            }
            // 이미 방문하거나 먹은 디저트인 경우 방향 바꾸기
            if (visited[nx][ny] || ate[map[nx][ny]]) continue;

            //아닌 경우
            search(nx, ny, i);
            dessert--;
            visited[nx][ny] = false; //위치 방문처리
            ate[map[nx][ny]] = false; //디저트 방문 처리


        }


    }

    public static boolean isValid(int x, int y) {
        if (x < 0 || y < 0 || x >= n || y >= n) {
            return false;
        }
        return true;
    }
}