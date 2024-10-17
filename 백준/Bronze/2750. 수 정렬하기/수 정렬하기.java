import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        TreeSet<Integer> ts = new TreeSet<>();

        for(int i=0; i<N; i++){
            ts.add(Integer.parseInt(br.readLine()));
        }

        for(int num : ts){
            sb.append(num).append("\n");
        }

        System.out.println(sb);
    }
}