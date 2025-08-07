import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] buildings = new int[n];

        for (int i = 0; i < n; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());
        }

        int maxSight = 0;

        for (int i = 0; i < n; i++) {
            int sight = 0;
            double minSlope = Double.MAX_VALUE;

            for (int idx = i - 1; idx >= 0; idx--) {
                double slope = (double)(buildings[idx] - buildings[i]) / (idx - i);
                if (idx == i - 1 || slope < minSlope) {
                    minSlope = slope;
                    sight++;
                }
            }

            double maxSlope = Double.MIN_VALUE;

            for (int idx = i + 1; idx < n; idx++) {
                double slope = (double)(buildings[idx] - buildings[i]) / (idx - i);
                if (idx == i + 1 || slope > maxSlope) {
                    maxSlope = slope;
                    sight++;
                }
            }

            maxSight = Math.max(maxSight, sight);

        }

        System.out.println(maxSight);
    }
}
