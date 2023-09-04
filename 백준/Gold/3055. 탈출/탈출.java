import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static Character map[][];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<int[]> sq = new LinkedList<int[]>();	//고슴도치 전용큐
    static Queue<int[]> wq = new LinkedList<int[]>();	//물 전용큐
    static int answer = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer( br.readLine() );
        R = Integer.parseInt( st.nextToken() );
        C = Integer.parseInt( st.nextToken() );

        //*: 물 / .: 비어있음 / X: 돌
        map = new Character[R][C];

        for( int i=0; i<R; i++ ) {
            String str = br.readLine();
            for( int j=0; j<C; j++ ) {
                map[i][j] = str.charAt(j);

                //BFS를 이용하므로 S 혹은 *이 나오면
                //바로 각 큐에 넣어준다.
                if( map[i][j] == 'S' )
                    sq.add( new int[] {i, j, 0} );
                else if( map[i][j] == '*' )
                    wq.add( new int[] {i, j} );
            }
        }

        BFS();

        System.out.println( answer==Integer.MAX_VALUE ? "KAKTUS" : answer );
    }

    public static void BFS() {

        while( !sq.isEmpty() ) {
            //먼저 물을 이동시킨다.
        	int w_len = wq.size();
            for( int i=0; i<w_len; i++ ) {
                int[] cur_w = wq.poll();

                for( int j=0; j<4; j++ ) {
                    int nx = cur_w[0] + dx[j];
                    int ny = cur_w[1] + dy[j];

                    //물의 새로운 좌표가 map범위 안에 있고, 새로운 좌표로 이동할 수 있으면
                    if( nx>=0 && nx<R && ny>=0 && ny<C && map[nx][ny]=='.' ) {
                        //물의 새로운 좌표를 물로 채운다.
                        map[nx][ny] = '*';

                        //물의 새로운 좌표를 물큐에 넣어준다.
                        wq.add( new int[] { nx, ny } );
                    }
                }
            }

            //물 이동시켰으므로 다음으로 고슴도치 이동시킨다.
            int s_len = sq.size();
            for( int i=0; i<s_len; i++ ) {
                int[] cur_s = sq.poll();

                for( int j=0; j<4; j++ ) {
                    int nx = cur_s[0] + dx[j];
                    int ny = cur_s[1] + dy[j];
                    int time = cur_s[2];

                    //고슴도치 새로운 좌표가 map범위 안에 있다면
                    if( nx>=0 && nx<R && ny>=0 && ny<C ) {
                        //고슴도치 새 좌표가 비버굴에 도착했으면 종료
                        if( map[nx][ny] == 'D' ) {
                            answer = Math.min( answer, time+1 );
                            return;
                        }
                        //고슴도치 새 좌표가 갈 수 있는 공간이면
                        else if( map[nx][ny] == '.' ) {
                            map[nx][ny] = 'S';
                            sq.add( new int[] { nx, ny, time+1 } );
                        }
                    }
                }
            }
        }


    }
}