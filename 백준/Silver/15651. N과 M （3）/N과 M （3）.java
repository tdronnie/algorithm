import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] nums;
    static List<int[]> result;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        nums = new int[m];
        result = new ArrayList<>();

        NPerMDup(0);

        for (int[] numbers : result) {
            for (int i = 0; i < m; i++) {
                sb.append(numbers[i]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void NPerMDup(int count) {
        if (count == m) {
            int[] numbers = Arrays.copyOf(nums, m);
            result.add(numbers);
            return;
        }

        for (int i = 1; i <= n; i++) {
            nums[count] = i;
            NPerMDup(count + 1);
        }
    }
}
