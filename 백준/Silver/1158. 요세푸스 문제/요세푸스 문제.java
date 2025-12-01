import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringJoiner sj = new StringJoiner(", ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Queue<Integer> line = new ArrayDeque<>();
        int[] output = new int[n];
        int count = 1;
        int index = 0;

        for (int i = 1; i <= n; i++) {
            line.offer(i);
        }

        while (!line.isEmpty()) {
            if (count == k) {
                output[index++] = line.poll();
                count = 1;
            } else {
                line.offer(line.poll());
                count++;
            }
        }

        for (int num : output) {
            sj.add(num + "");
        }

        System.out.print("<" + sj + ">");

    }
}
