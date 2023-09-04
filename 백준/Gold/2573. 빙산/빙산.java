import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[][] map, meltCnt;
    static boolean[][] visited;
    static int[] moveR={-1, 1,0,0};
    static int[] moveC={0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int time = 0, cnt, area; //걸리는 시간, 녹이는 카운트, 빙산 개수
        while(true){
            
            //탐색할 때마다 변수들 초기화
            cnt =0;
            area=0;
            visited = new boolean[n][m];
            meltCnt = new int[n][m];
            
            //가장자리 제외한 부분 영역탐색
            for(int i=1; i<n-1; i++){
                for(int j=1; j<m-1; j++){
                    if(map[i][j] >0 && !visited[i][j]) { //빙산이고 아직 탐색하지 않은 것일 경우
                        bfs(i, j); //영역 탐색
                        area++; //빙산개수++
                    }
                }
            }
            //두 부분으로 나누어지거나 빙산이 모두 녹아서 없어졌다면 끝내기
            if(area>=2 || area == 0){
                if(area==0)
                    System.out.println(0);
                else
                    System.out.println(time);
                break;
            }
            
            //빙산 한층씩 녹이기
            for(int i=1; i<n-1; i++){
                for(int j=1; j<m-1; j++){
                    if(map[i][j]>0){ //빙산
                        cnt=0; //덩어리 몇갠지 체크위해 초기화
                        //상하좌우 빙산없으면 카운트
                        for(int k=0; k<4; k++){
                            int newR = i + moveR[k];
                            int newC = j + moveC[k];
                            if(map[newR][newC] == 0)cnt++; //인접한 부분이 0이면 한번 녹이기
                        }
                        meltCnt[i][j] = cnt; //녹은양 갱신

                    }
                }
            }
            //map배열에 녹은만큼 빼주기
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    map[i][j] = map[i][j]-meltCnt[i][j];
                    if(map[i][j]<0) //0이하로 업데이트되도 0
                        map[i][j] = 0;
                }
            }
            time++; //시간 지남
        }
    }

    static void bfs(int i, int j){
        Queue<int[]> queue= new LinkedList<>();

        queue.offer(new int[]{i, j});

        while(!queue.isEmpty()) {
            int[] val= queue.poll();

            for(int k=0; k<4; k++) {
                int newI= val[0]+moveR[k];
                int newJ= val[1]+moveC[k];
                if(map[newI][newJ]>0&&!visited[newI][newJ]) {
                    visited[newI][newJ]= true;
                    queue.offer(new int[]{newI, newJ});
                }
            }
        }
        
    }
}
