import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int num, maxSum;
    static int[] match = {5, 3, 4, 1, 2, 0};
    static int[][] around = {{1, 2, 3, 4}, {0, 2, 4, 5}, {0, 1, 3, 5}, {0, 2, 4, 5}, {0, 1, 3, 5}, {1, 2, 3, 4}};
    static int[][] aroundMax; //i번째 주사위의 j면 주변 숫자들 중 최댓값
    static int[][] dices;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        num = Integer.parseInt(br.readLine());
        dices = new int[num][6];
        maxSum = Integer.MIN_VALUE;
        aroundMax = new int[num][6];

        for (int i = 0; i < num; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                dices[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < num; i++) {
            for (int j = 0; j < 6; j++) {
                int max = 0;
                for (int k = 0; k < 4; k++) {
                    max = Math.max(max, dices[i][around[j][k]]);
                }
                aroundMax[i][j] = max;
            }
        }

        for (int i = 0; i < 6; i++) {
            makeDiceDummy(0, i, 0);
        }

        System.out.println(maxSum);

    }

    static void makeDiceDummy(int diceNum, int matchIdx, int sum) {

        sum += aroundMax[diceNum][matchIdx];

        if (diceNum == num-1) {
            maxSum = Math.max(maxSum, sum);
            return;
        }

        int nextBottomIdx = 0;
        for (int i = 0; i < 6; i++) {
            if (dices[diceNum + 1][i] == dices[diceNum][matchIdx]) {
                nextBottomIdx = match[i];
                break;
            }
        }

        makeDiceDummy(diceNum + 1, nextBottomIdx, sum);
    }
}
