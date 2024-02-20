import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m, cnt;
    static int[][] map;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        cnt = 0;

        st = new StringTokenizer(br.readLine());
        int startX = Integer.parseInt(st.nextToken());
        int startY = Integer.parseInt(st.nextToken());
        int startD = Integer.parseInt(st.nextToken());


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        getCleaning(startX, startY, startD);
        System.out.println(cnt+1); //시작 위치 더해주기

    }

    //반시계 방향
    static int[] dx = {-1 ,0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    private static void getCleaning(int x, int y, int d) {

        map[x][y] = 2;


        int k = d;
        //현재 방향 시작으로 4방 탐색 시작
        for (int l = 0; l < 4; l++) {

            k--;
            if(k==-1) k=3;

            int newX = x + dx[k];
            int newY = y + dy[k];

            //범위 넘어가지 않고 빈칸이라면 청소하고 다음 턴으로 넘어가기
            //벽과 청소된 칸이 아닌경우
            if(newX>=0 && newY>=0 && newX<n && newY<m && map[newX][newY] == 0){
                cnt++; //청소 카운트++
                getCleaning(newX, newY, k);
                return; //이전 턴은 아예 끝내기

            }
        }
        //만약 사방에 모두 갈 수 없는 경우 현재 방향을 본채로 뒤로 한캄
        int goback = (d + 2) % 4; //뒤의 방향
        x += dx[goback];
        y += dy[goback];
        if(x<0 || y<0 || x>=n || y>=m || map[x][y] == 1) return; //뒤로 갔을 때 벽이라면 작업 끝내기
        //벽이 아니라면 한 칸 후진하고 다시 사방탐색
        getCleaning(x, y, d);

        }
    }