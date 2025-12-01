import java.io.*;
import java.util.*;

public class Main {

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        for (int test = 0; test < t; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[] nums = new int[n];
            // 최대 100개의 데이터 그중 2개의 수에 대해 연산 100C2 -> 4950쌍
            // 나올 수 있는 최대공약수 최댓값 1000000
            // 따라서 최대공약수 합의 최댓값 4950 * 1000000 = 4950000000, int범위 초과
            long sum = 0;

            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            if (n == 1) {
                sb.append(nums[0]).append("\n");
                continue;
            }

            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    int result = gcd(nums[i], nums[j]);
                    sum += result;
                }
            }
            sb.append(sum).append("\n");

        }
        System.out.print(sb);
    }

    static int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a % b);
    }
}
