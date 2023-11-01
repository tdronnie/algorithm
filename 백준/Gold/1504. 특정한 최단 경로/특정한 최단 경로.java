import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //정점 개수
        int e = Integer.parseInt(st.nextToken()); //간선 개수

        map = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == j) //자기자신까지의 최단경로는 0
                    map[i][j] = 0;
                else
                    map[i][j] = Integer.MAX_VALUE; //무한대로 초기화

            }
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); //a번 정점
            int b = Integer.parseInt(st.nextToken()); //b번 정점
            int c = Integer.parseInt(st.nextToken()); //가중치
            map[a][b] = c;
            map[b][a] = c;
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        //임의의 노드로부터 모든 노드까지의 최단거리 구하기
        for (int i = 1; i <= n; i++) {
            for (int x = 1; x <= n; x++) {
                for (int y = 1; y <= n; y++) {
                    map[x][y] = Math.min(map[x][y], map[x][i] + map[i][y]);
                }
            }
        }
        
//        for (int i = 1; i <= n; i++) {
//        	for (int j = 1; j <= n; j++) {
//        		System.out.print(map[i][j]+ " ");
//        	}
//        	System.out.println();
//			
//		}

        //1 - v1 - v2 - n 경로 사이 갈수 없는 경우 바로 -1출력
        if (map[1][v1] == Integer.MAX_VALUE || map[v1][v2] == Integer.MAX_VALUE || map[v2][v1] == Integer.MAX_VALUE || map[v2][n] == Integer.MAX_VALUE || map[v1][n] == Integer.MAX_VALUE)
            System.out.println(-1);

        else {
            //어떤 경유노드를 먼저 가는 것이 최단경로일지
//            v1이 1번인 경우,v2가 n번인 경우도 존재 가능
            long rslt1=Integer.MAX_VALUE, rslt2=Integer.MAX_VALUE;
            //경유노드와 1번, n번이 다른 노드일때
            if (v1 != 1 && v2 != n) {
                if (map[1][v1] != Integer.MAX_VALUE && map[v1][v2] != Integer.MAX_VALUE && map[v2][n] != Integer.MAX_VALUE)
                    rslt1 = map[1][v1] + map[v1][v2] + map[v2][n];
                if(map[1][v2] != Integer.MAX_VALUE && map[v2][v1] != Integer.MAX_VALUE && map[v1][n] != Integer.MAX_VALUE)
                    rslt2 = map[1][v2] + map[v2][v1] + map[v1][n];
            }
            //v1이 1번이고 v2가 n이 아닐때
            else if(v1==1 && v2!=n){
                if(map[1][v2] != Integer.MAX_VALUE && map[v2][n] != Integer.MAX_VALUE){
                    rslt1 = map[1][v2] + map[v2][n];
                }
            }
            //v1는 1번이 아니고 v2가 n일 때
            else if(v1!=1 && v2==n){
                if(map[1][v1]!=Integer.MAX_VALUE && map[v1][n] != Integer.MAX_VALUE){
                    rslt1 = map[1][v1] + map[v1][n];
                }
            }
            //v1가 1번이고 v2도 n인 경우
            else if(v1==1 && v2==n){
                if(map[1][n] != Integer.MAX_VALUE)
                    rslt1 = map[1][n];
            }

            System.out.println(Math.min(rslt1, rslt2));
        }
    }
}