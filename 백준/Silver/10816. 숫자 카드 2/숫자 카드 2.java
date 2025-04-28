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

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashMap<Integer, Integer> numCount = new HashMap<>();

        while (st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken());
            numCount.put(num, numCount.getOrDefault(num, 0) + 1);
        }

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int findCountNum = Integer.parseInt(st.nextToken());
            sb.append(numCount.get(findCountNum) == null ? 0 : numCount.get(findCountNum)).append(" ");
        }
        System.out.println(sb);
    }
}