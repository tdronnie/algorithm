import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int n, k, max = 0;
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            map = new int[n][n];
            max = 0;

            //지도에 나타나는 지형의 높이 1~9
            int maxVal = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    maxVal = Math.max(maxVal, map[i][j]);
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] == maxVal) {
                        visited = new boolean[n][n];
                        visited[i][j] = true;
                        dfs(i, j, map[i][j], 1, 0); //시작 좌표, 현재 높이, 등산로 길이, 공사 여부
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(max).append("\n");

        }
        System.out.println(sb);
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    private static void dfs(int x, int y, int curr, int len, int cnt) {

        //상시로 최대 등산로 길이 확인
        max = Math.max(max, len);


        for (int t = 0; t < 4; t++) {
            int newX = x + dx[t];
            int newY = y + dy[t];

            if(newX<0 || newY<0 || newX>=n || newY>=n) continue;

            if(visited[newX][newY]) continue;

            //먼저 가려는 곳 방문 처리
            visited[newX][newY] = true;
            //낮은 지형이면 바로 dfs
            if(curr > map[newX][newY]){
                dfs(newX, newY, map[newX][newY], len+1, cnt);
            }

            //같거나 높은 지형이면 공사 안했다면 dfs
            else{
                // 공사안했다면 최대 k만큼 공사했을 때 현재 좌표보다 낮아지는지 확인
                if (cnt == 0 && map[newX][newY]-k < curr) {
                    //가능하다면 최대한 많이 갈 수 있도록 현재 위치의 -1만큼만 깎아준다
                    dfs(newX, newY, curr-1, len+1, cnt+1);
                }
                //공사 이미 했다면 패스
            }
            //다음 경우의 수 위해 방문처리 취소
            visited[newX][newY] = false;

        }
    }
}