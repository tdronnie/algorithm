import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();
        int order = 1;

        for (int i = 0; i < N; i++) {
            if (arr[i] == order) {
                order++;
                continue;
            }

            while (!stack.isEmpty() && stack.peek() == order) {
                stack.pop();
                order++;
            }

            if (arr[i] == order) {
                order++;
                continue;
            }

            stack.push(arr[i]);
        }

        while (!stack.isEmpty() && stack.peek() == order) {
            stack.pop();
            order++;
        }

        sb.append(stack.isEmpty() ? "Nice" : "Sad");
        System.out.print(sb);
    }
}