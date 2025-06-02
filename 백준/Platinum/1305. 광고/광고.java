import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int length = Integer.parseInt(br.readLine());
        String sign = br.readLine();

        int[] failureArray = makeFailureArray(sign, length);

        for (int i = 0; i < length - failureArray[length - 1]; i++) {
            sb.append(sign.charAt(i));
        }
        System.out.println(length - failureArray[length - 1]);
    }

    static int[] makeFailureArray(String sign, int length) {
        int[] failureArray = new int[length];
        int j=0;
        failureArray[0] = 0;

        for (int i = 1; i < length; i++) {
            while (j > 0 && sign.charAt(i) != sign.charAt(j)) {
                j = failureArray[j - 1];
            }

            if (sign.charAt(i) == sign.charAt(j)) {
                j++;
            }

            failureArray[i] = j;
        }
        return failureArray;
    }
}
