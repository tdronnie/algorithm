import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static boolean[] visited;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int num = Integer.parseInt(st.nextToken());
        int compCount = Integer.parseInt(st.nextToken());

        List<Integer>[] shorters = new ArrayList[num + 1];
        List<Integer>[] tallers = new ArrayList[num + 1];
        int result = 0;

        for (int i = 1; i <= num; i++) {
            shorters[i] = new ArrayList<>();
            tallers[i] = new ArrayList<>();
        }

        for (int i = 0; i < compCount; i++) {
            st = new StringTokenizer(br.readLine());
            int smaller = Integer.parseInt(st.nextToken());
            int taller = Integer.parseInt(st.nextToken());
            shorters[smaller].add(taller);
            tallers[taller].add(smaller);

        }


        for (int i = 1; i <= num; i++) {
            visited = new boolean[num + 1];
            int smallCount = checkOrder(shorters, i);
            visited = new boolean[num + 1];
            int tallCount = checkOrder(tallers, i);
            if (smallCount + tallCount == num-1) {
                result++;
            }
        }

        System.out.println(result);

    }

    static int checkOrder(List<Integer>[] order, int num){

        int count = 0;

        for (int next : order[num]) {
            if(visited[next]) continue;
            visited[next] = true;
            count++;
            count += checkOrder(order, next);
        }

        return count;
    }
}
