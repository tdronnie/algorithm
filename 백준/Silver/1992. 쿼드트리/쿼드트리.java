import java.io.*;

public class Main {

    private static int[][] arr;
    private static StringBuilder sb = new StringBuilder();
    private static boolean check = true;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        //각 사분면 분할
        divide(0, 0, n);

        System.out.println(sb.toString());


    }

    public static void divide(int i, int j, int size) {
        boolean check = true;
        // 사분면 탐색, 현재 부분이 하나의 숫자로만 이루어져있지 않다면 재귀실행
        for (int x = i; x < i + size; x++) {
            for (int y = j; y < j + size; y++) {
                if (arr[x][y] != arr[i][j]) {//같지 않은 경우 나누어서 분기 시작
                    check = false;
                    break;
                }
            }
            if (!check)
                break;
        }
        if (check) { //다 같은 경우 압축하고 분기 끝내기
            sb.append(arr[i][j]);
            return;
        }
        //다음 분기
        sb.append("(");
        // 1사분면
        divide(i, j, size / 2);
        // 2사분면
        divide(i, j + size / 2, size / 2);
        // 3사분면
        divide(i + size / 2, j, size / 2);
        // 4사분면
        divide(i + size / 2, j + size / 2, size / 2);
        sb.append(")");


    }
}
