import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        String n = st.nextToken();
        int b = Integer.parseInt(st.nextToken());

        int answer = 0;
        int len = n.length();

        for (int i = 0; i < len; i++) {
            char c = n.charAt(len - 1 - i);
            int num=0;
            if(c >= 'A' &&  c <= 'Z'){
                num = c - 'A' + 10;
            } else {
                num = c - '0';
            }

            int pow = 1;
            for (int j = 0; j < i; j++) {
                pow *= b;
            }
            answer += (num * pow);
        }
        System.out.println(answer);
    }
}