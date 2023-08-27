import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    static int[][] board;
    static int n, min, maxCcnt;
    // 전선 연결하지 않거나 상하좌우 탐색
    static int[] moveR = {-1, 1, 0, 0};
    static int[] moveC = {0, 0, -1, 1};

    // 코어의 위치 저장하는 코어 클래스
    static class Core {
        int r, c;

        public Core(int r, int c) {
            super();
            this.r = r;
            this.c = c;
        }

    }

    static List<Core> cores;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {

            n = Integer.parseInt(br.readLine());// n줄
            board = new int[n][n];
            cores = new ArrayList<>();
            min = Integer.MAX_VALUE;
            maxCcnt = Integer.MIN_VALUE;

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken()); // 0빈칸, 1코어
                    if (board[i][j] == 1 && i != 0 && j != 0 && i != n - 1 && j != n - 1) { // 가장자리 코어는 전선 필요 없음
                        cores.add(new Core(i, j)); // 코어 클래스 저장
                    }
                }
            }


            setWire(0, 0, 0);
            System.out.println("#" + test_case + " " + min);
        }
    }

    // 전선 겹치지 않는지 확인
    private static void setWire(int idx, int Ccnt, int wire) {

        if (idx == cores.size()) { // 마지막 저장 코어까지 탐색 완료
            if (maxCcnt < Ccnt) { // 최대 코어 수보다 클때
                maxCcnt = Ccnt;
                min = wire;
            } else if (maxCcnt == Ccnt) { // 최대 코어 수와 같을 때
                min = Math.min(min, wire);
            }
            return;
        }

        Core core = cores.get(idx);
        int cnt;
        for (int j = 0; j < 4; j++) { // 4방 탐색 한방향으로 될때까지 계속 탐색
            // 좌표는 원래 코어 위치에 고정
            int newR = core.r;
            int newC = core.c;
            cnt = 0;

            while (true) {
                newR += moveR[j];
                newC += moveC[j];
                if (newR >= 0 && newC >= 0 && newR < n && newC < n) {
                    if (board[newR][newC] != 1) {//범위도 맞고 전선도 아닌 경우
                        cnt++; // 전선 길이++
                    }
                    if(board[newR][newC] == 1){ //전선 만난다면 해당 분기 불가
                        cnt = 0;
                        break;
                    }
                } else //범위 맞지 않은 경우
                    break;
            }
            
            //전선깔기, 못가는 부분으로 변경
            int startR = core.r;
            int startC = core.c;
            for (int i = 0; i < cnt; i++) {
                startR += moveR[j];
                startC += moveC[j];
                
                board[startR][startC] = 1;
            }
             //전선 연결 불가 코어, 그대로 뎁스만 추가해서 넘긴다
            if(cnt == 0){
                setWire(idx+1, Ccnt, wire);
            }
            //전선 연결 가능 코어, 뎁스 추가하고 지금까지의 전선수와 현재 분기에 새로 깐 전선 수를 더해서 넘긴다
            //넘기고 깔은 전선 원복 시키기
            else{
                setWire(idx + 1, Ccnt+1, wire+cnt);

                startR = core.r;
                startC = core.c;
                for (int i = 0; i < cnt; i++) {
                    startR += moveR[j];
                    startC += moveC[j];

                    board[startR][startC] = 0;
                }
            }

        }

    }

}
