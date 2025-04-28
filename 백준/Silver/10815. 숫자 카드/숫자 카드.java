import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Set<Integer> numbers = new HashSet<>();

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            numbers.add(num);
        }

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < m; i++) {
            sb.append(numbers.contains(Integer.parseInt(st.nextToken())) ? 1 : 0).append(" ");
        }
        System.out.println(sb);
    }
}