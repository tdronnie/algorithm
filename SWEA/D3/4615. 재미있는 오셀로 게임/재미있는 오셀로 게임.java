import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

    static int n, m;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); //한 변의 길이 4, 6, 8중 하나
            m = Integer.parseInt(st.nextToken()); //돌 놓는 수

            board = new int[n + 1][n + 1];

            //board중앙에 초기세팅
            for (int i = n/2; i <= n/2+1 ; i++) {
                for (int j = n / 2; j <= n / 2 + 1; j++) {
                    if(i == j)
                        board[i][j] = 2;
                    else
                        board[i][j] = 1;

                }
            }
            int cnt = 0; //돌 놓는 카운트
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int col = Integer.parseInt(st.nextToken());
                int row = Integer.parseInt(st.nextToken());
                int color = Integer.parseInt(st.nextToken()); //1흑,  2백

                //보드에 빈 곳이 없는 경우 끝내기
                if (cnt == n * n) {
                    break;
                }
                
                //해당 좌표에 이미 돌이 있는 경우는 놓지 못한다
                if (board[row][col] != 0) {
                    continue;
                }
                //자신의 돌 사이에 상대 돌이 있을 경우에만 돌 놓기 가능
                if(findChanges(row, col, color)) {
                    board[row][col] = color;
                    cnt++; //돌 카운트++
                }

            }

            int[] ans = calColorCnt(board);
            sb.append("#").append(tc).append(" ").append(ans[0]).append(" ").append(ans[1]).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int[] calColorCnt(int[][] board) {
        int cnt1 = 0, cnt2 = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (board[i][j] == 1)
                    cnt1++;
                else if (board[i][j] == 2) {
                    cnt2++;
                }
            }

        }
        return new int[]{cnt1, cnt2};
    }

    //현재 돌 둔 좌표 기준 8방탐색 필요
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    private static boolean findChanges(int row, int col, int color) {
        boolean flag = false;

        for (int i = 0; i < 8; i++) {
            int newR = row + dx[i];
            int newC = col + dy[i];

            //범위 나가는 경우
            if (!isValid(newR, newC)) continue;
            //같은 색 돌이거나 아직 빈 좌표인 경우
            if (board[newR][newC] == color || board[newR][newC] == 0) continue;


            //다른 색 돌이 인접해 있다면, 중간의 상대 돌 색 바꾸기 위해 다음에 나오는 자기 돌 색깔까지 탐색
            ArrayList<int[]> chList = new ArrayList<>();
            //바꿀 리스트에 추가
            chList.add(new int[]{newR, newC});
            while (true) {
                newR += dx[i];
                newC += dy[i];

                //범위 나가는 경우 불가
                if (!isValid(newR, newC)) break;
                //빈 좌표 나옴.. 색 바꾸기 불가
                if (board[newR][newC] == 0) break;
                //같은 색 나옴, 중간의 상대 색 다 바꾸기
                if (board[newR][newC] == color) {
                    changeColor(chList, color);
                    flag = true;
                    break;
                }
                //상대 색 나온 경우 바꿀리스트에 추가
                chList.add(new int[]{newR, newC});
            }
        }
        return flag;
    }

    private static void changeColor(ArrayList<int[]> chList, int color) {
        for (int[] val : chList) {
            board[val[0]][val[1]] = color;
        }
    }

    private static boolean isValid(int newR, int newC) {
        return newR >= 1 && newC >= 1 && newR <= n && newC <= n;

    }
}