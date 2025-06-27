import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int row, col;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        int[][] board = new int[row][col];
        int cheese = 0;

        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 1) cheese++;
            }
        }

        System.out.println(melting(board, 1, cheese));

    }

    static int melting(int[][] board, int time, int remain) {

        board = outside(board, 0, 0);

        int melt = 0;
        int[][] cpBoard = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                cpBoard[i][j] = board[i][j];
            }
        }

        for (int i = 1; i < row-1; i++) {
            for (int j = 1; j < col-1; j++) {
                if(board[i][j] == 1){
                    int count = 0;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if(nx < 0 || ny < 0 || nx >= row || ny >= col) continue;
                        if(board[nx][ny] == -1){
                            count++;
                        }
                        if (count >= 2) {
                            cpBoard[i][j] = -1;
                            melt++;
                            break;
                        }
                    }
                }
            }
        }

        if(remain - melt == 0) return time;
        return melting(cpBoard, time + 1, remain - melt);
    }

    static int[][] outside(int[][] board, int x, int y) {
        boolean[][] visited = new boolean[row][col];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;
        board[x][y] = -1;

        while (!q.isEmpty()) {
            int[] loc = q.poll();
            int currX = loc[0];
            int currY = loc[1];

            for (int k = 0; k < 4; k++) {
                int nx = currX + dx[k];
                int ny = currY + dy[k];

                if(nx < 0 || ny <0 || nx >= row || ny >= col || board[nx][ny] == 1) continue;
                if(visited[nx][ny]) continue;
                visited[nx][ny] = true;

                board[nx][ny] = -1;
                q.add(new int[]{nx, ny});
            }
        }
        return board;
    }
}
