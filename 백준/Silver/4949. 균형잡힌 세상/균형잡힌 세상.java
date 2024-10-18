import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String str = br.readLine();

            if (str.equals(".")) break;

            StringTokenizer st = new StringTokenizer(str);
            Stack<Character> stack = new Stack<>();

            wordDetect:
            while (st.hasMoreTokens()) {
                String word = st.nextToken();
                String onlyPS = word.replaceAll("[a-zA-Z. ]", "");

                if (onlyPS.isEmpty())
                    continue;

                for (char ch : onlyPS.toCharArray()) {
                    switch (ch) {
                        case '(':
                            stack.push(ch);
                            break;
                        case ')':
                            if (stack.isEmpty() || stack.peek() != '(') {
                                stack.push(ch);
                                break wordDetect;
                            }
                            stack.pop();
                            break;
                        case '[':
                            stack.push(ch);
                            break;
                        case ']':
                            if (stack.isEmpty() || stack.peek() != '[') {
                                stack.push(ch);
                                break wordDetect;
                            }
                            stack.pop();
                            break;
                    }
                }
            }
            sb.append(stack.isEmpty() ? "yes\n" : "no\n");
        }
        System.out.print(sb);
    }
}