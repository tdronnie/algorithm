import java.io.*;

public class Main {

    static int n;
    static long[] strLen;
    static long[] xCount;
    static long[] yCount;
    static long[] zCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());

        // 각 문자열 길이 구하기
        strLen = new long[n + 1];

        // 각 문자열 문자 빈도수 구하기
        xCount = new long[n + 1];
        yCount = new long[n + 1];
        zCount = new long[n + 1];

        if (n >= 1) {
            strLen[1] = 1;
            xCount[1] = 1;
            yCount[1] = 0;
            zCount[1] = 0;
        }

        if (n >= 2) {
            strLen[2] = 2;
            xCount[2] = 0;
            yCount[2] = 1;
            zCount[2] = 1;
        }

        if (n >= 3) {
            strLen[3] = 2;
            xCount[3] = 1;
            yCount[3] = 0;
            zCount[3] = 1;
        }

        // 1단계 + 2단계 = 4단계 -> 4단계 이상부터 i단계 = i-3단계 + i-2단계
        for (int i = 4; i <= n; i++) {
            strLen[i] = strLen[i - 3] + strLen[i - 2];
            xCount[i] = xCount[i - 3] + xCount[i - 2];
            yCount[i] = yCount[i - 3] + yCount[i - 2];
            zCount[i] = zCount[i - 3] + zCount[i - 2];
        }

        switch(num) {
            case 1 : // n단계의 문자열 길이
                System.out.print(strLen[n]);
                break;
            case 2: // n단계의 문자열에서 k번째 문자
                long kth = Long.parseLong(br.readLine());
                char kthChar = findKth(n, kth);
                System.out.print(kthChar);
                break;
            case 3 : // n번째 문자열에서 특정 문자 빈도수
                char countChar = br.readLine().charAt(0);
                if (countChar == 'X') {
                    System.out.print(xCount[n]);
                } else if (countChar == 'Y') {
                    System.out.print(yCount[n]);
                } else {
                    System.out.print(zCount[n]);
                }
                break;

        }
    }

    // 단계 문자열마다 이전 문자열(i-3, i-2)을 알고 있으므로 k번째가 속하는 부분 좁혀가기
    static char findKth(int step, long k) {

        if (step == 1) return 'X';
        if (step == 2) return (k == 1 ? 'Y' : 'Z');
        if (step == 3) return (k == 1 ? 'Z' : 'X');

        if (k <= strLen[step - 3]) {
            return findKth(step - 3, k);
        } else {
            return findKth(step - 2, k - strLen[step - 3]);
        }
    }
}
