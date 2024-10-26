import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static List<Integer> areaCount;
    static int count;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        areaCount = new ArrayList<>();
        visited = new boolean[N][N];

        for(int i=0; i<N; i++){
            String str = br.readLine();
            for(int j=0; j<N; j++){
                map[i][j] = str.charAt(j) - '0';
            }
        }

        count = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(map[i][j]!=0 && !visited[i][j]){
                    count = 1;
                    BFS(i, j);
                    areaCount.add(count);
                }
            }
        }

        sb.append(areaCount.size()).append("\n");

        Collections.sort(areaCount);

        for (int n : areaCount) {
            sb.append(n).append("\n");
        }

        System.out.print(sb);
    }

    static int[] moveX = {-1, 0, 1, 0};
    static int[] moveY = {0, 1, 0, -1};

    public static void BFS(int x, int y){
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;

        while(!q.isEmpty()){
            int[] arr = q.poll();
            for(int i=0; i<4; i++){
                int nx = arr[0] + moveX[i];
                int ny = arr[1] + moveY[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 0) continue;
                if(visited[nx][ny]) continue;

                visited[nx][ny] = true;
                count++;
                q.add(new int[]{nx, ny});
            }
        }
    }
}