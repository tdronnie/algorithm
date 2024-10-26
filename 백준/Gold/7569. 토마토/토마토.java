import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, H;
    static int[][][] box;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        box = new int[N][M][H];
        List<int[]> tomato = new ArrayList<>();
        boolean tobe = false;

        for(int k = 0; k < H; k++) {
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<M; j++) {
                    box[i][j][k] = Integer.parseInt(st.nextToken());
                    if(box[i][j][k] == 1)
                        tomato.add(new int[]{i, j, k});
                    if(box[i][j][k] == 0)
                        tobe = true;
                }
            }

        }

        if(!tobe) {
            System.out.print(0);
            return;
        }

        BFS(tomato);

        int maxDay = 0;
        for(int k=0; k<H; k++) {
            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    if(box[i][j][k] == 0) {
                        System.out.print(-1);
                        return;
                    }
                    maxDay = Math.max(maxDay, box[i][j][k]);
                }
            }
        }

        System.out.print(maxDay-1);
    }

    static int[] moveX = {-1, 0, 1, 0, 0, 0};
    static int[] moveY = {0, 1, 0, -1, 0, 0};
    static int[] moveH = {0, 0, 0, 0, 1, -1};

    public static void BFS(List<int[]> locs) {
        boolean[][][] visited = new boolean[N][M][H];
        Queue<int[]> q = new ArrayDeque<>();
        for(int[] loc : locs)
            q.add(new int[]{loc[0], loc[1], loc[2]});

        while(!q.isEmpty()) {
            int[] tomato = q.poll();
            for(int i=0; i<6; i++) {
                int nx = tomato[0] + moveX[i];
                int ny = tomato[1] + moveY[i];
                int nh = tomato[2] + moveH[i];

                if(nx <0 || ny <0 || nx>= N || ny >= M || nh <0 || nh >= H || box[nx][ny][nh] == -1 || box[nx][ny][nh] == 1) continue;
                if(visited[nx][ny][nh]) continue;

                visited[nx][ny][nh] = true;
                box[nx][ny][nh] = box[tomato[0]][tomato[1]][tomato[2]] + 1;

                q.add(new int[]{nx, ny, nh});
            }
        }
    }
}