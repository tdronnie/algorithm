import java.util.*;

class Solution {
    public int solution(int[][] info, int n, int m) {
        // dp[i][j] = A 흔적 i, B 흔적 j가 가능한지 여부
        boolean[][] dp = new boolean[n][m];
        dp[0][0] = true;

        for (int[] stuff : info) {
            int a = stuff[0];
            int b = stuff[1];
            boolean[][] next = new boolean[n][m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (!dp[i][j]) continue;

                    // A가 훔치는 경우, A 흔적 합 i + a < n
                    if (i + a < n) {
                        next[i + a][j] = true;
                    }

                    // B가 훔치는 경우, B 흔적 합 j + b < m
                    if (j + b < m) {
                        next[i][j + b] = true;
                    }
                }
            }
            dp = next;
        }

        // A의 흔적 최소인 것 찾기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dp[i][j]) {
                    return i;
                }
            }
        }

        return -1;
    }
}