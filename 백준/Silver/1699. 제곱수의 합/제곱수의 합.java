import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] minKinds = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            // 우선 1^2을 i번 더한 것을 최소항으로 저장
            minKinds[i] = i;
            // 현재 수보다 작거나같은 제곱수들에 대해서 모두 검사
            // 포함할 수 있는 가장 큰 제곱수를 포함하고 해당 제곱수 항개수(1) 더하기
            for (int j = 1; j * j <= i; j++) {
                if (minKinds[i] > minKinds[i - (j * j)] + 1) {
                    minKinds[i] = minKinds[i - (j*j)] + 1;
                }
            }
        }

        System.out.println(minKinds[n]);
    }
}
