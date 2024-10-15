import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        String A = st.nextToken();
        String B = st.nextToken();

        for(int idx=2; idx>=0; idx--){
            int Anum = A.charAt(idx) - '0';
            int Bnum = B.charAt(idx) - '0';
            if(Anum > Bnum){
                sb.append(A).reverse();
                break;
            }
            if(Anum < Bnum){
                sb.append(B).reverse();
                break;
            }
        }

        System.out.println(sb);
    }
}