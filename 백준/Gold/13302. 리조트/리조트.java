import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static boolean[] canUse;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dp = new int[n + 1][41];
        canUse = new boolean[n + 1];
        Arrays.fill(canUse, true);

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 0;

        if (m > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                int day = Integer.parseInt(st.nextToken());
                canUse[day] = false;
            }
        }

        System.out.println(findMinCharge());
    }

    static int findMinCharge() {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0});

        while (!q.isEmpty()) {
            int[] pair = q.poll();
            int day = pair[0];
            int coupon = pair[1];

            for (int i = 1; i <= 5; i += 2) {
                int nextDay = day + i;
                if (nextDay > n) continue;

                if (i == 1) {
                    if (!canUse[nextDay]) {
                        dp[nextDay][coupon] = Math.min(dp[nextDay][coupon], dp[day][coupon]);
                        q.add(new int[]{nextDay, coupon});
                        break;
                    }
                    //쿠폰 사용
                    if (coupon >= 3 && dp[nextDay][coupon - 3] > dp[day][coupon]) {
                        dp[nextDay][coupon - 3] = dp[day][coupon];
                        q.add(new int[]{nextDay, coupon - 3});
                    }
                    //일일권 사용
                    if (dp[nextDay][coupon] > dp[day][coupon] + 10000) {
                        dp[nextDay][coupon] = dp[day][coupon] + 10000;
                        q.add(new int[]{nextDay, coupon});
                    }
                }
                //3일연속권
                if (i == 3 && dp[nextDay][coupon + 1] > dp[day][coupon] + 25000) {
                    dp[nextDay][coupon + 1] = dp[day][coupon] + 25000;
                    q.add(new int[]{nextDay, coupon + 1});
                }
                //5일연속권
                if (i == 5 && dp[nextDay][coupon + 2] > dp[day][coupon] + 37000) {
                    dp[nextDay][coupon + 2] = dp[day][coupon] + 37000;
                    q.add(new int[]{nextDay, coupon + 2});
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int j = 0; j <= 40; j++) {
            result = Math.min(result, dp[n][j]);
        }
        return result;
    }
}