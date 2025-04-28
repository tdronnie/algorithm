import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashMap<String, String> nameKey = new HashMap<>();
        HashMap<String, String> numKey = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String num = String.valueOf(i);
            nameKey.put(num, name);
            numKey.put(name, num);
        }

        for (int i = 0; i < m; i++) {
            String q = br.readLine();
            sb.append(nameKey.get(q) != null ? nameKey.get(q) : numKey.get(q)).append("\n");
        }
        System.out.println(sb);
    }
}