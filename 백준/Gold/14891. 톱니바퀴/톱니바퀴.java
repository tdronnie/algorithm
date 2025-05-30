import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] parts = new int[5][8];
    static int[][] bridge = new int[5][2];
    static int part1R, part2L, part2R, part3L, part3R, part4L;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 1; i <= 4; i++) {
                bridge[i][0] = 6;
                bridge[i][1] = 2;
        }

        for (int i = 1; i <= 4; i++) {
            String string = br.readLine();
            for (int j = 0; j < 8; j++) {
                parts[i][j] = string.charAt(j) - '0';
            }
        }

        int moveCount = Integer.parseInt(br.readLine());

        for (int count = 0; count < moveCount; count++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int partNum = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            moveParts(partNum, dir, partNum);
        }
        System.out.println(totalScore());
    }

    static void moveParts(int num, int dir, int pre) {

        if (num > 1 && pre != num -1) {
            int idx = bridge[num][0];
            int idxLeft = bridge[num - 1][1];
            if (parts[num][idx] != parts[num - 1][idxLeft]) {
                moveParts(num-1, dir*(-1), num);
            }
        }
        if (num < 4 && pre != num + 1) {
            int idx = bridge[num][1];
            int idxRight = bridge[num + 1][0];
            if (parts[num][idx] != parts[num + 1][idxRight]) {
                moveParts(num + 1, dir * (-1), num);
            }
        }

        if (dir == 1) {
            bridge[num][0]--;
            bridge[num][1]--;
            if(bridge[num][0] < 0) bridge[num][0] += 8;
            if(bridge[num][1] < 0) bridge[num][1] += 8;
        } else {
            bridge[num][0]++;
            bridge[num][1]++;
            if(bridge[num][0] >= 8) bridge[num][0] -= 8;
            if(bridge[num][1] >= 8) bridge[num][1] -= 8;
        }

    }

    static int totalScore() {
        int totalScore = 0;
        int pow = 1;

        for (int i = 1; i <= 4; i++) {
            int index = bridge[i][1] - 2;
            if (bridge[i][1] - 2 < 0) {
                index += 8;
            }
            if (parts[i][index] == 1) {
                totalScore += pow;
            }
            pow *= 2;
        }
        return totalScore;
    }
}
