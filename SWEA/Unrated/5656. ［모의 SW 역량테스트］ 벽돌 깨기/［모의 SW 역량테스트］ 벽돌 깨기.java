import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static int n, w, h, min = Integer.MAX_VALUE;
    static int[][] field, copyField;
    static int[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); //구슬 개수
            w = Integer.parseInt(st.nextToken()); //열 개수
            h = Integer.parseInt(st.nextToken()); //행 개수

            field = new int[h][w]; //벽돌 정보

            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    field[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            selected = new int[n];
            min = Integer.MAX_VALUE;
            //구슬 어디에 떨어뜨릴지 정하기
            selectW(0);

            sb.append("#").append(tc).append(" ").append(min).append("\n");
        }
        System.out.println(sb.toString());
    }

    //중복순열
    public static void selectW(int cnt) {
        if (cnt == n) {
            //n개의 구슬을 벽돌로 떨어뜨리기
            drop(selected);
            return;
        }
        for (int i = 0; i < w; i++) {
            selected[cnt] = i;
            selectW(cnt + 1);
        }
    }

    private static void drop(int[] selected) {
        //구슬 n개 던지는 한 턴마다 복사배열 생성해서 적용
        copyField = new int[h][w];
        for (int x = 0; x < h; x++) {
            for (int y = 0; y < w; y++) {
                copyField[x][y] = field[x][y];
            }
        }
        //n개의 구슬 떨어뜨리기
        for (int i = 0; i < n; i++) {
            int row = 0;
            int col = selected[i];
            //1이상의 벽돌이 나올때까지 구슬 떨어짐
            while (copyField[row][col] == 0 && row < h - 1) row++;
            //벽돌이 없는 경우
            if (row == h) continue;

            //연쇄 폭발 시작
            breakBricks(row, col, copyField);
            //빈공간으로 벽돌 떨어지기
            fillBlank(copyField);

        }
//        남은 벽돌 세기
        int cnt = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (copyField[i][j] != 0) {
                    cnt++;
                }
            }
        }
        min = Math.min(min, cnt);
    }

    private static void fillBlank(int[][] copy) {
        //모든 열에 대해서 탐색
        int[][] fillCopy = new int[h][w];
        for (int j = 0; j < w; j++) {
            int start = h - 1;
            for (int i = h - 1; i >= 0; i--) {
                if (copy[i][j] > 0) {
                    fillCopy[start][j] = copy[i][j];
                    start--; //0이 아닌 값으로 벽돌이 채워져 있다면 그대로 쌓아올린다.
                }
                //0이라면 copy배열은 한탄 올라가고 fill배열은 그대로
            }
        }

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                copyField[i][j] = fillCopy[i][j]; //구슬 n개 던지고 공백 메꾸기도 완료 경우의 수를 위한 복사본에 덮어쓰기
            }
        }
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    //벽돌 없애주기, bfs
    public static void breakBricks(int r, int c, int[][] copy) {

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r, c});

        while (!q.isEmpty()) {
            int[] val = q.poll();
            int bound = copy[val[0]][val[1]];
            copy[val[0]][val[1]] = 0; //구슬 맞은 벽돌 폭발
            for (int i = 0; i < 4; i++) {
                int nx = val[0];
                int ny = val[1];

                for (int j = 0; j < bound - 1; j++) {
                    nx += dx[i];
                    ny += dy[i];
                    //범위 넘어선 경우 해당 방향 끝내기
                    if (!isValid(nx, ny)) break;
                    //1인 경우 0으로만 바꿔주기
                    if (copy[nx][ny] == 1) {
                        copy[nx][ny] = 0;
                    }

                    //1이상인 경우 큐에 넣기
                    if (copy[nx][ny] > 1) {
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }

    public static boolean isValid(int x, int y) {
        if (x < 0 || y < 0 || x >= h || y >= w) {
            return false;
        }
        return true;
    }
}