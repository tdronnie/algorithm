import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int sLength = Integer.parseInt(br.readLine());
        String string = br.readLine();
        String pattern = "";

        for (int i = 0; i < n; i++) {
            pattern = pattern.concat("IO");
        }
        pattern = pattern.concat("I");

        int[] startIdxs = makeStartIdxArray(pattern);

        System.out.println(findPattern(string, pattern, startIdxs));

    }

    static int findPattern(String str, String pattern, int[] startIdxs) {
        int count = 0;
        int pIdx = 0;

        for (int i = 0; i < str.length(); i++) {
            while (pIdx > 0 && str.charAt(i) != pattern.charAt(pIdx)) {
                pIdx = startIdxs[pIdx - 1];
            }

            if (str.charAt(i) == pattern.charAt(pIdx)) {
                pIdx++;
            }

            if (pIdx == pattern.length()) {
                count++;
                pIdx = startIdxs[pIdx - 1];
            }
        }

        return count;
    }

    static int[] makeStartIdxArray(String pattern) {
        int[] startIdxs = new int[pattern.length()];

        startIdxs[0] = 0;
        int check = 0;

        for (int i = 1; i < pattern.length(); i++) {

            //문자가 다르면 이전 시작 인덱스로 이동
            while (check > 0 && pattern.charAt(i) != pattern.charAt(check)) {
                check = startIdxs[check - 1];
            }

            //문자가 같으면 매칭하는 길이 늘려가기
            if (pattern.charAt(i) == pattern.charAt(check)) {
                check++;
            }

            // 현재 위치까지 매칭길이나 돌아가는 인덱스 저장
            startIdxs[i] = check;
        }

        return startIdxs;
    }
}
