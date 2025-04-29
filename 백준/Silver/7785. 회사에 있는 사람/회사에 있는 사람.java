import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Set<String> log = new HashSet<>();

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            if (st.nextToken().equals("enter")) {
                log.add(name);
            } else {
                log.remove(name);
            }
        }

        ArrayList<String> enterNames = new ArrayList<>(log);
        Collections.sort(enterNames, Collections.reverseOrder());

        for (String name : enterNames) {
            sb.append(name).append("\n");
        }

        System.out.println(sb);


    }
}