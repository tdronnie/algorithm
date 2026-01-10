import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] trees = new int[n];
        int max = 0;
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, trees[i]);
        }

        long left = 1;
        long right = max;
        long answer = 0;

        while (left <= right) {
            long mid = (left + right) / 2; // 자를 높이 이분탐색

            long treeAmount = 0;
            for (int i = 0; i < n; i++) {
                treeAmount += Math.max((trees[i] - mid), 0); // 절단 높이보다 낮은 나무는 잘리지 않는다.
            }

            if (treeAmount >= m) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(answer);

    }
}
