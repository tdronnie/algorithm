import java.io.*;

public class Main {

    public static void main(String[] args)  throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String octal = br.readLine();

        for (int i = 0; i < octal.length(); i++) {
            int num = octal.charAt(i) - '0';
            if (i == 0) {
                sb.append(String.format("%3s", Integer.toBinaryString(num)).replace(" ", ""));
            } else {
                sb.append(String.format("%3s", Integer.toBinaryString(num)).replace(" ", "0"));
            }
        }

        System.out.print(sb);

    }
}
