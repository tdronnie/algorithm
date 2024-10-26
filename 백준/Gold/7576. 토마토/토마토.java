import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] box;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        box = new int[N][M];
        List<int[]> tomato = new ArrayList<>();
        boolean tobe = false;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if(box[i][j] == 1)
                    tomato.add(new int[]{i, j});
                if(box[i][j] == 0)
                    tobe = true;
            }
        }

        if(!tobe) {
            System.out.print(0);
            return;
        }

        BFS(tomato);

        int maxDay = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(box[i][j] == 0) {
                    System.out.print(-1);
                    return;
                }
                maxDay = Math.max(maxDay, box[i][j]);
            }
        }
        System.out.print(maxDay-1);
    }

    static int[] moveX = {-1, 0, 1, 0};
    static int[] moveY = {0, 1, 0, -1};

    public static void BFS(List<int[]> locs) {
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> q = new ArrayDeque<>();
        for(int[] loc : locs)
            q.add(new int[]{loc[0], loc[1]});

        while(!q.isEmpty()) {
            int[] tomato = q.poll();
            for(int i=0; i<4; i++) {
                int nx = tomato[0] + moveX[i];
                int ny = tomato[1] + moveY[i];

                if(nx <0 || ny <0 || nx>= N || ny >= M || box[nx][ny] == -1 || box[nx][ny] == 1) continue;
                if(visited[nx][ny]) continue;

                visited[nx][ny] = true;
                box[nx][ny] = box[tomato[0]][tomato[1]] + 1;

                q.add(new int[]{nx, ny});
            }
        }
    }
}