import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static List<int[]> result;
    static int[] nums;
    static boolean[] visited;
    static StringBuilder sb;


    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        result = new ArrayList<>();
        nums = new int[m];
        visited = new boolean[n + 1];

        NComM(0, 1);

        System.out.println(sb);
    }

    static void NComM(int count, int start) {
        if (count == m) {
            for (int num : nums) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i <= n; i++) {
            if(visited[i]) continue;

            nums[count] = i;
            visited[i] = true;
            NComM(count+1, i);
            visited[i] = false;
        }
    }
}
