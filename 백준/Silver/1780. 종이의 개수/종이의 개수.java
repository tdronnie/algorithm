import java.io.*;
import java.util.*;
public class Main {

    static int[][] paper;
    static int[] result;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(br.readLine());
        result = new int[3];
        paper = new int[length][length];
        for (int i = 0; i < length; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < length; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        paperCount(0, 0, length);
        for (int n : result) {
            System.out.println(n);
        }

    }

    static void paperCount(int x, int y, int len) {

        int num = paper[x][y];
        for (int i = x; i < x+len; i++) {
            for (int j = y; j < y+len; j++) {
                if (paper[i][j] != num) { // 현재 길이에서 같은 수로 이루어져 있지 않을 때 9등분
                    paperCount(x, y, len / 3);
                    paperCount(x, y+(len/3), len / 3);
                    paperCount(x, y+(len/3)*2, len / 3);
                    paperCount(x+(len/3), y, len / 3);
                    paperCount(x+(len/3), y+(len/3), len / 3);
                    paperCount(x+(len/3), y+(len/3)*2, len / 3);
                    paperCount(x+(len/3)*2, y, len / 3);
                    paperCount(x+(len/3)*2, y+(len/3), len / 3);
                    paperCount(x+(len/3)*2, y+(len/3)*2, len / 3);
                    return;
                }
            }
        }
        result[num+1]++;
    }
}
