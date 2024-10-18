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
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            switch(st.nextToken()) {
                case "1":
                    stack.push(Integer.parseInt(st.nextToken()));
                    break;

                case "2":
                    if ((!stack.isEmpty())) {
                        sb.append(stack.pop());
                    } else {
                        sb.append(-1);
                    }
                    break;

                case "3":
                    sb.append(stack.size());
                    break;

                case "4":
                    if(stack.isEmpty()) {
                        sb.append(1);
                    } else{
                        sb.append(0);
                    }
                    break;
                case "5":
                    if(!stack.isEmpty()) {
                        sb.append(stack.peek());
                    } else{
                        sb.append(-1);
                    }
                    break;
            }

            if(sb.length() != 0){
                sb.append("\n");
                System.out.print(sb);
                sb.setLength(0);
            }
        }
    }
}