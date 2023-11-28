import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static int n, w, h, min;
    static int[][] board, copyBoard;
    static int[] balls;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            board = new int[h][w];
            min = Integer.MAX_VALUE;

            //공 떨어뜨릴 경우의 수 구하고
            //떨어져서 벽돌과 부딪힌 좌표부터 벽돌 깨뜨리기
            //깨진 빈자리 중력적용

            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            balls = new int[n];
            start(0);
            sb.append("#").append(tc).append(" ").append(min).append("\n");
        }
        System.out.println(sb);
    }

    private static void start(int cnt) {

        if (cnt == n) {
            //board 내에 산발적인 변화가 많기 때문에 복사배열 생성
            copyBoard = new int[h][w];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    copyBoard[i][j] = board[i][j];
                }
            }
            for (int k = 0; k < n; k++) {

                //구슬 떨어뜨릴 열 모두 골랐다면 떨어지는 좌표 구하기
                int row = 0;
                int col = balls[k];

                //벽돌에 맞을 때까지 떨어뜨려 주기
                while (row < h - 1 && copyBoard[row][col] == 0) row++;
                //바닥에 맞았다면 끝내기
                if (row == h) continue;

                //벽돌에 맞는다면, 벽돌 깨기
                breakBricks(row, col, copyBoard);
                //빈공간 중력 적용
                removeEmpty(copyBoard);

            }
            //가장 많은 벽돌이 제거되는 경우의 수 구하기
            int cntBricks = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (copyBoard[i][j] > 0)
                        cntBricks++;
                }
            }
            min = Math.min(min, cntBricks);
            return;
        }

        //중복 순열
        for (int i = 0; i < w; i++) {
            balls[cnt] = i;
            start(cnt + 1);
        }


    }

    private static void removeEmpty(int[][] copy) {
        //빈공간 채원주는 것도 역시 복사배열 내에서 변화 많이 일어나므로 깔끔하게 새로운 배열에 값 적용 후 덮어쓰기
        //0일 때는 copyBoard만 한 행 올라가고 rslt배열은 그대로
        int[][] rslt = new int[h][w];
        for (int j = 0; j < w; j++) {
            int ground = h - 1;
            for (int i = h - 1; i >= 0; i--) {
                if (copy[i][j] > 0) {
                    rslt[ground][j] = copy[i][j];
                    ground--;
                }
            }
        }

        //copyBoard에 복사
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                copyBoard[i][j] = rslt[i][j];
            }
        }
    }

    private static void print() {

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.print(copyBoard[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    private static void breakBricks(int row, int col, int[][] copyBoard) {
        Queue<int[]> q = new ArrayDeque<>();

        q.add(new int[]{row, col});

        while (!q.isEmpty()) {
            int[] val = q.poll();
            int bound = copyBoard[val[0]][val[1]];
            copyBoard[val[0]][val[1]] = 0;
            //반경 1이면 바로 끝내주기
            if (bound == 1) {
                return;
            }

            for (int k = 0; k < 4; k++) {
                //구슬 맞는 좌표부터 시작
                int newX = val[0];
                int newY = val[1];
                //반경 깨뜨리기
                for (int i = 0; i < bound - 1; i++) {
                    newX += dx[k];
                    newY += dy[k];

                    //범위체크
                    if (!isValid(newX, newY)) break;

                    //0이면 통과
                    if (copyBoard[newX][newY] == 0)
                        continue;
                    //1이면 0으로만 바꿔주기
                    if (copyBoard[newX][newY] == 1) {
                        copyBoard[newX][newY] = 0;
                        continue;
                    }
                    //1이상이면 큐에 넣어주기
                    q.add(new int[]{newX, newY});
                }
            }
        }

    }

    private static boolean isValid(int newX, int newY) {
        return newX >= 0 && newY >= 0 && newX < h && newY < w;
    }
}