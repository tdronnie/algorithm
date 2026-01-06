import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken()); // 최대 100
        int m = Integer.parseInt(st.nextToken()); // 최대 100
        int k = Integer.parseInt(st.nextToken());

        int[][] kind = new int[101][101];

        // a든 z든 둘중 하나만 쓰인다면 무조건 경우의 수는 한가지
        for (int i = 1; i <= 100; i++) {
            kind[i][0] = kind[0][i] = 1;
        }

        // a을 더하거나 z를 더하거나 각 개수가 만족할때까지 구하기
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                kind[i][j] = kind[i - 1][j] + kind[i][j - 1]; // a 추가하는 경우 먼저 고려 -> 사전순
                kind[i][j] = Math.min(kind[i][j], 1000000000); // K번째까지만 고려
            }
        }

        //k가 경우의 수를 넘어가는 경우 종료
        if (kind[n][m] < k) {
            System.out.print(-1);
            return;
        }

        // n+m 길이의 k번째 문자열 만들기
        // kind 배열이 이루어진대로 따라가기
        // kind[2][4]의 경우 kind[1][4](5) + kind[2][3](10), 만약 10번째 찾는다면 a가 먼저쓰인 kind[1][4]에는 없으므로 kind[2][3]부분으로 탐색
        // 이런식으로 a나 z 하나씩 이어가기

        int aCount = n;
        int zCount = m;

        for (int i = 0; i < n + m; i++) {
            if (aCount == 0) { // 주어진 a모두 다 썼다면 나머지 z로 채우기
                sb.append("z");
                zCount--;
                continue;
            }
            if (zCount == 0) {
                sb.append("a");
                aCount--;
                continue;
            }

            int curr = kind[aCount - 1][zCount]; // a를 추가하는 경우

            if (k <= curr) { // K번째가 a를 추가하는 경우에 위치
                sb.append("a");
                aCount--;
            } else { // k번째가 z를 추가하는 경우에 위치
                k -= curr;// k번째 조정, 앞의 a추가 경우크기 뺴주기
                sb.append("z");
                zCount--;

            }
        }

        System.out.print(sb);

    }

}
