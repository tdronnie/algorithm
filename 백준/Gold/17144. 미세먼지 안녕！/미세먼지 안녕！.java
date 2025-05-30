import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int row, col, time;
    static int[][] room;
    static int[] machine;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        time = Integer.parseInt(st.nextToken());
        room = new int[row][col];
        machine = new int[2];

        int k = 0;

        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if(room[i][j] == -1){
                    machine[k] = i;
                    k++;
                }
            }
        }

        start();
        System.out.println(amountDust());
    }

    static void start() {
        for(;time > 0; time--) {
            expansion();
            machineOn();
        }
    }

    static void expansion() {
        int[][] roomCp = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (room[i][j] != 0 && room[i][j] != -1) {
                    int count = 0;

                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if(nx < 0 || ny <0 || nx >= row || ny >= col || room[nx][ny] == -1) continue;

                        count++;
                        roomCp[nx][ny] += room[i][j] / 5;
                    }
                    room[i][j] -= count * (room[i][j] / 5);
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                room[i][j] += roomCp[i][j];
            }
        }
    }

    static void machineOn() {
        int upX = machine[0];

        for (int i = upX - 2; i >= 0; i--) {
            room[i + 1][0] = room[i][0];
        }

        for (int j = 1; j < col; j++) {
            room[0][j - 1] = room[0][j];
        }

        for (int i = 1; i <= upX; i++) {
            room[i - 1][col - 1] = room[i][col - 1];
        }

        for (int j = col - 2; j > 0; j--) {
            room[upX][j + 1] = room[upX][j];
        }
        room[upX][1] = 0;

        int downX = machine[1];

        for (int i = downX + 2; i < row; i++) {
            room[i - 1][0] = room[i][0];
        }

        for (int j = 1; j < col; j++) {
            room[row - 1][j - 1] = room[row - 1][j];
        }

        for (int i = row - 2; i >= downX; i--) {
            room[i + 1][col - 1] = room[i][col - 1];
        }

        for (int j = col - 2; j > 0; j--) {
            room[downX][j + 1] = room[downX][j];
        }
        room[downX][1] = 0;

    }

    static int amountDust() {
        int dust = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (room[i][j] != 0 && room[i][j] != -1) {
                    dust += room[i][j];
                }
            }
        }
        return dust;
    }
}
