import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, startX, startY, endX, endY;
    static int[][] board;

    static boolean[][] visited;

    static class Loc{
        int x, y, move;

        public Loc(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine());
            board = new int[n][n];
            visited = new boolean[n][n];

            StringTokenizer st = new StringTokenizer(br.readLine());
            startX = Integer.parseInt(st.nextToken());
            startY = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            endX = Integer.parseInt(st.nextToken());
            endY = Integer.parseInt(st.nextToken());

            sb.append(findMinMove()).append("\n");
        }
        System.out.println(sb);

    }

    static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};
    public static int findMinMove() {

        Queue<Loc> q = new ArrayDeque<>();
        visited[startX][startY] = true;
        q.add(new Loc(startX, startY, 0));

        while (!q.isEmpty()) {
            Loc loc = q.poll();

            if (loc.x == endX && loc.y == endY) {
                return loc.move;
            }

            for (int k = 0; k < 8; k++) {
                int newX = loc.x + dx[k];
                int newY = loc.y + dy[k];

                if(newX<0 || newY<0 || newX>=n || newY>=n) continue;
                if(visited[newX][newY]) continue;

                visited[newX][newY] = true;
                q.add(new Loc(newX, newY, loc.move + 1));

            }
        }
        return -1;

    }
}