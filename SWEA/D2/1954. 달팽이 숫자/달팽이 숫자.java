import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine());
            int[][] map = new int[n][n];

            int x=0, y=0, dir=0;
            int num = 1;
            // 다음 칸이 이미 1이상이거나 범위 넘어가는 경우 다음 방향으로 전환
            end:
            while(true){

                while(true) {
                    if (x < 0 || y < 0 || x >= n || y >= n || map[x][y] != 0) break;
                    map[x][y] = num++;
                    x += dx[dir];
                    y += dy[dir];
                }
                //한 칸 더 간것 빼주기
                x -= dx[dir];
                y -= dy[dir];
                
                dir = (dir + 1) % 4; //방향 전환 후 이동
                x += dx[dir];
                y += dy[dir];

                if(x<0 || y<0 || x>=n || y>=n || map[x][y]!=0) break;
            }

            sb.append("#").append(tc).append("\n");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    sb.append(map[i][j]).append(" ");
                }
                sb.append("\n");
            }

        }
        System.out.println(sb);

    }
}