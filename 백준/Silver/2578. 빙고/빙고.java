import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int[][] board;
    private static boolean bingo = false;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        board = new int[5][5];
        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int n = 0, cnt=0;
        for (int r = 0; r < 5; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < 5; c++) {
                removeNum(Integer.parseInt(st.nextToken()), ++n);
                if (bingo) {
                    return;
                }
            }
        }
    }
    private static void removeNum(int num, int cnt) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (num == board[i][j]) {
                    board[i][j] = 0;
                    //없앤 숫자 주변부터 빙고 가능여부 탐색
                    if (cnt >= 5 && isBingo()) { //제거한 숫자가 5개이상이고 빙고라면
                        System.out.println(cnt);
                        bingo = true;
                        return;
                    }
                    return;
                }
            }
        }
    }

    private static boolean isBingo() {

        int cnt = 0;
        int bingoCnt=0;

        //행 검사
        for(int r = 0; r<5; r++){
            for (int c = 0; c < 5; c++) {
                if (board[r][c] == 0) {
                    cnt++;
                }
            }
            if(cnt == 5)
                bingoCnt++;
            cnt = 0;
        }

        //열 검사
        for(int c = 0; c<5; c++){
            for (int r = 0; r < 5; r++) {
                if (board[r][c] == 0) {
                    cnt++;
                }
            }
            if(cnt == 5)
                bingoCnt++;
            cnt = 0;
        }

        //좌하향 대각선 검사
        int c = 0, r = 0;
        while (c < 5) {
            if (board[r++][c++] == 0) {
                cnt++;
            }
        }

        if (cnt == 5)
            bingoCnt++;

        cnt = 0;
        //우상향 대각선 검사
        c = 0;
        r = 4;
        while (c < 5) {
            if (board[r--][c++] == 0) {
                cnt++;
            }
        }
        if (cnt == 5)
            bingoCnt++;

        if(bingoCnt >= 3)
            return true;
        return false;
    }

}