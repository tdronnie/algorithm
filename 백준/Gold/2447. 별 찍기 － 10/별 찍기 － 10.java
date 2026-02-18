import java.io.*;

public class Main {

    static char[][] stars;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        stars = new char[n][n];

        makeStars(0, 0, n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(stars[i][j]);
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static void makeStars(int x, int y, int size) {

        // 크기가 3인 경우, (x, y)를 좌상단 기준으로 3*3패턴 입력
        if (size == 3) {
            for (int i = x; i < x + 3; i++) {
                for (int j = y; j < y + 3; j++) {
                    if (i == x + 1 && j == y + 1) {
                        stars[i][j] = ' ';
                    } else {
                        stars[i][j] = '*';
                    }
                }
            }
            return;
        }

        int nextSize = size/3;

        //가운데 비우기
        for (int i = x + nextSize; i < x + nextSize*2; i++) {
            for (int j = y + nextSize; j < y + nextSize*2; j++) {
                stars[i][j] = ' ';
            }
        }

        // 가운데 제외한 8부분 재귀 실행
        makeStars(x, y, nextSize);
        makeStars(x, y+nextSize, nextSize);
        makeStars(x, y+nextSize*2, nextSize);
        makeStars(x+nextSize, y, nextSize);
        makeStars(x+nextSize, y+nextSize*2, nextSize);
        makeStars(x+nextSize*2, y, nextSize);
        makeStars(x+nextSize*2, y+nextSize, nextSize);
        makeStars(x+nextSize*2, y+nextSize*2, nextSize);
    }
}
