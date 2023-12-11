import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int n, startX, startY, endX, endY;
    static int[][] board;

    static class Loc implements Comparable<Loc> {
        int x, y, move;

        public Loc(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }

        @Override
        public int compareTo(Loc o) {
            return this.move - o.move;
        }
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine());
            board = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    board[i][j] = Integer.MAX_VALUE;
                }
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            startX = Integer.parseInt(st.nextToken());
            startY = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            endX = Integer.parseInt(st.nextToken());
            endY = Integer.parseInt(st.nextToken());

            if(startX == endX && startY == endY){
                sb.append(0).append("\n");
                continue;
            }
            findMinMove();
            sb.append(board[endX][endY]).append("\n");
        }
        System.out.println(sb);

    }

    static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

    private static void findMinMove() {
        PriorityQueue<Loc> pq = new PriorityQueue<Loc>();
        board[startX][startY] = 0;
        pq.add(new Loc(startX, startY, 0));

        while (!pq.isEmpty()) {

            int pqSize = pq.size();
            for (int i = 0; i < pqSize; i++) {
                Loc loc = pq.poll();
                for (int k = 0; k < 8; k++) {

                    int newX = loc.x + dx[k];
                    int newY = loc.y + dy[k];

                    if(newX <0 || newY<0 || newX>=n ||newY>= n) continue;

                    if(board[newX][newY] > loc.move + 1){
                        board[newX][newY] = loc.move + 1;
                        pq.add(new Loc(newX, newY, board[newX][newY]));
                    }
                }

            }


        }

    }


}