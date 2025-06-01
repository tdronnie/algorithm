import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String text = br.readLine();
        String pattern = br.readLine();

        int[] startIdxs = makestartIndxs(pattern);

        findPatternLoc(text, pattern, startIdxs);

    }

    static void findPatternLoc(String text, String pattern, int[] idxs) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        int j=0;

        for (int i = 0; i < text.length(); i++) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = idxs[j - 1];
            }

            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
            }

            if (j == pattern.length()) {
                count++;
                sb.append(i - j + 2).append(" ");
                j = idxs[j - 1];
            }
        }

        System.out.println(count);
        System.out.println(sb);
    }

    static int[] makestartIndxs(String pattern) {
        int[] idxs = new int[pattern.length()];

        idxs[0] = 0;
        int j=0;

        for (int i = 1; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = idxs[j - 1];
            }

            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }

            idxs[i] = j;
        }
        return idxs;
    }
}
