import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int test = Integer.parseInt(br.readLine());

        for (int T = 0; T < test; T++) {
            int n = Integer.parseInt(br.readLine());
            HashMap<String, ArrayList<String>> map = new HashMap<>();

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String name = st.nextToken();
                String category = st.nextToken();
                map.computeIfAbsent(category, a -> new ArrayList<>()).add(name);
            }

                int kinds = 1;
                for (String category : map.keySet()) {
                    kinds *= (map.get(category).size() + 1);
                }
                sb.append(kinds-1).append("\n");
        }
        System.out.println(sb);
    }
}