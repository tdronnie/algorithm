import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] mount = new int[26];
        Arrays.fill(mount, -1);

        String S = br.readLine();
        for(int i=0; i<S.length(); i++) {
            int alphabetOrder = S.charAt(i) - 'a';
            if(mount[alphabetOrder] == -1){
                mount[alphabetOrder] = i;
            }
        }

        for (int n : mount) {
            sb.append(n).append(" ");
        }
        System.out.println(sb);
    }
}