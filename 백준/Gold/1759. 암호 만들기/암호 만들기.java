import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int length, words;
    static char[] chars;
    static char[] code;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        length = Integer.parseInt(st.nextToken());
        words = Integer.parseInt(st.nextToken());
        chars = new char[words];
        code = new char[length];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < words; i++) {
            chars[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(chars);

        makeCode(0, 0, 0, 0);

        System.out.println(sb);

    }

    static void makeCode(int count, int start, int countVow, int countCon) {
        if (count == length) {
            if (countVow >= 1 && countCon >= 2) {
                for (char alphabet : code) {
                    sb.append(alphabet);
                }
                sb.append("\n");
            }
            return;
        }

        for (int i = start; i < words; i++) {
            code[count] = chars[i];
            if("aeiou".indexOf(chars[i]) != -1){
                makeCode(count + 1, i + 1, countVow + 1, countCon);
            } else {
                makeCode(count + 1, i + 1, countVow, countCon + 1);
            }
        }
    }
}
