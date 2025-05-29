import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][][] shapes = {
            {{0, 0}, {1, 0}, {2, 0}, {3, 0}}, {{0, 0}, {0, 1}, {0, 2}, {0, 3}},

            {{0, 0}, {0, 1}, {1, 0}, {1, 1}},

            {{0, 0}, {0, 1}, {1, 0}, {2, 0}}, {{0, 0}, {0, 1}, {1, 1}, {2, 1}},
            {{0, 0}, {1, 0}, {2, 0}, {2, 1}}, {{0, 0}, {1, 0}, {2, 0}, {2, -1}},
            {{0, 0}, {0, 1}, {0, 2}, {1, 0}}, {{0, 0}, {0, 1}, {0, 2}, {1, 2}},
            {{0, 0}, {1, 0}, {1, 1}, {1, 2}}, {{0, 0}, {0, 1}, {0, 2}, {-1, 2}},

            {{0, 0}, {1, 0}, {1, 1}, {2, 1}}, {{0, 0}, {1, 0}, {1, -1}, {2, -1}},
            {{0, 0}, {0, 1}, {1, 1}, {1, 2}}, {{0, 0}, {0, 1}, {1, 0}, {1, -1}},

            {{0, 0}, {-1, 1}, {0, 1}, {0, 2}}, {{0, 0}, {0, 1}, {1, 1}, {0, 2}},
            {{0, 0}, {1, 0}, {1, 1}, {2, 0}}, {{0, 0}, {1, 0}, {1, -1}, {2, 0}}};

    static int row, col;
    static int[][] paper;
    static int maxSum = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        paper = new int[row][col];

        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                check(i, j);
            }
        }
        System.out.println(maxSum);
    }

    static void check(int x, int y) {
        for (int[][] shape : shapes) {
            int sum = 0;
            boolean valid = true;

            for (int[] pos : shape) {
                int newX = x + pos[0];
                int newY = y + pos[1];

                if(newX <0 || newY <0 || newX >= row || newY >= col){
                    valid = false;
                    break;
                }
                sum += paper[newX][newY];
            }
            if(valid){
                maxSum = Math.max(maxSum, sum);
            }
        }
    }
}
