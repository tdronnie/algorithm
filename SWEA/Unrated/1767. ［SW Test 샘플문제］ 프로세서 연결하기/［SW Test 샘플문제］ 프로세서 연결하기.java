import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

    static int n, maxCoreN, minWireLen;
    static int[][] board;

    static class Core {
        int r, c;

        public Core(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static ArrayList<Core> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine());

            board = new int[n][n];
            list = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    //가장 자리 제외한 나머지 코어들만 전선깔아주기
                    if (board[i][j] == 1 && i != 0 && i != n - 1 && j != 0 && j != n - 1) {
                        list.add(new Core(i, j));
                    }
                }
            }

            maxCoreN = Integer.MIN_VALUE;
            minWireLen = Integer.MAX_VALUE;
            setWire(0, 0, 0);
            sb.append("#").append(tc).append(" ").append(minWireLen).append("\n");

        }
        System.out.println(sb.toString());
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void setWire(int cnt, int coreNum, int wireLen) {

        //모든 코어를 한번씩 탐색 끝낸 경우
        if (cnt == list.size()) {
            //탐색 결과 연결된 코어의 수가 같은 경우
            if (coreNum == maxCoreN) {
                minWireLen = Math.min(minWireLen, wireLen); //전선 길이 합이 최소가 되는 값 구하기
            }
            //연결된 코어수가 더 많은 경우 최우선으로 업데이트
            else if(coreNum > maxCoreN) {
                maxCoreN = coreNum;
                minWireLen = wireLen;
            }
            return;
        }

        Core core = list.get(cnt); //cnt번째 코어에 전선 연결하기

        for (int i = 0; i < 4; i++) {
            int newR = core.r;
            int newC = core.c;
            int wire = 0;
            while (true) {
                newR += dx[i];
                newC += dy[i];

                if (!isValid(newR, newC)) break; //범위 넘어설때까지 온 겨우 전선 놓기 가능
                //코어나 전선(깔리면 1로 변경해줌)이 위치한 좌표인 경우 전선 놓기 불가능
                if (board[newR][newC] == 1) {
                    wire = 0;
                    break;
                }
                wire++;


            }

            //깔린 전선 있을 경우 결괏값 다음 턴으로 넘겨주기
            //전선깔린 코어 더해줄지 말지 정해야하기 떼문에 전선이 깔린지 안깔린지에 따라 분기
            int sr = core.r;
            int sc = core.c;
            if (wire != 0) {
                //전선 깔기
                for (int k = 0; k < wire; k++) {
                    sr += dx[i];
                    sc += dy[i];
                    board[sr][sc] = 1;
                }
                setWire(cnt + 1, coreNum + 1, wireLen + wire);

            //깔은 전선 원복!! 같은 코어의 다른 방향 탐색 위해
                sr = core.r;
                sc = core.c;
                for (int k = 0; k < wire; k++) {
                    sr += dx[i];
                    sc += dy[i];
                    board[sr][sc] = 0;
                }
            } else {
                setWire(cnt + 1, coreNum, wireLen);

            }



        }
    }

    private static boolean isValid(int row, int col) {
        if (row < 0 || col < 0 || row >= n || col >= n) {
            return false;
        }
        return true;

    }
}