import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static boolean[][][] visited;
    static int minStep = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][2];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        BFS(0, 0, 1, 0);

        if (minStep == Integer.MAX_VALUE)
            System.out.print(-1);
        else
            System.out.print(minStep);
    }

    static int[] moveX = {-1, 0, 1, 0};
    static int[] moveY = {0, 1, 0, -1};

    public static void BFS(int x, int y, int step, int destroy) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x, y, step, destroy});
        visited[x][y][destroy] = true;

        while(!q.isEmpty()) {
            int[] loc = q.poll();
            int nowX = loc[0];
            int nowY = loc[1];
            int nowStep = loc[2];
            int nowDestroy = loc[3];

            if (nowX == N - 1 && nowY == M - 1) {
                minStep = nowStep;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = nowX + moveX[i];
                int ny = nowY + moveY[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                if(map[nx][ny] == 0) {
                    if(visited[nx][ny][nowDestroy]) continue;
                    visited[nx][ny][nowDestroy] = true;
                    q.add(new int[]{nx, ny, nowStep + 1, nowDestroy});
                } else{
                    if(nowDestroy == 1) continue;
                    if(visited[nx][ny][1]) continue;
                    visited[nx][ny][1] = true;
                    q.add(new int[]{nx, ny, nowStep + 1, 1});
                }
            }
        }
    }
}