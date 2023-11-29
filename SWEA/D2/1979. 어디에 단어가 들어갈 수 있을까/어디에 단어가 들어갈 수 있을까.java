import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); //가로세로 길이
            int k = Integer.parseInt(st.nextToken()); //단어 길이 k
            ArrayList<Integer> canWordCnt = new ArrayList<>();

            String[][] board = new String[n][n];
            for (int i = 0; i < n; i++) {
                String row = br.readLine().replace(" ", ""); //공백 없애기

                //행별로 열요소 저장해놓기
                for (int j = 0; j < n; j++) {
                    board[j][i] = row.substring(j, j + 1);
                }

                //i행에서 가능한 단어길이 찾기
                String[] len1 = row.split("0");

                //행에서 나올 수 있는 단어길이 저장
                for (String wordCnt : len1) {
                    if (wordCnt.startsWith("1")) {
                        canWordCnt.add(wordCnt.length());
                    }
                }
            }

            //행 탐색 모두 끝냈으면 저장해놓은 열 탐색
            for (int i = 0; i < n; i++) {
                String col = "";
                for (int j = 0; j < n; j++) {
                    col = col.concat(board[i][j]);
                }

                String[] len1 = col.split("0");

                //열에서 나올 수 있는 단어길이 저장
                for (String wordCnt :
                        len1) {
                    if (wordCnt.startsWith("1")) {
                        canWordCnt.add(wordCnt.length());
                    }

                }

            }
            //행렬 나올 수 있는 단어 탐색 모두 마쳤다면 k길이 몇번 나왔는지 확인
            int ans = Collections.frequency(canWordCnt, k);
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

}