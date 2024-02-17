import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int F, S, G, U, D, minMove = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        findStartLink(S);

        if (minMove == Integer.MAX_VALUE) {
            sb.append("use the stairs");
        }
        else{
            sb.append(minMove);
        }

        System.out.println(sb);
    }

    private static void findStartLink(int start) {

        Queue<int[]> q = new ArrayDeque<>();
        boolean[] visited = new boolean[F + 1];
        visited[start] = true;
        q.add(new int[]{start, 0});

        while (!q.isEmpty()) {

            int[] val = q.poll();
            int curr = val[0];
            int currMove = val[1];

            //G층에 도착한 경우 끝내기
            if(curr == G) {
                minMove = Math.min(minMove, currMove);
                return;
            }

            //아니라면 위 아래로 갈 수 있는지 따져서 큐에 넣어주기
            if (curr + U <= F && !visited[curr + U]) {
                visited[curr + U] = true;
                q.add(new int[]{curr + U, currMove + 1});
            }

            if (curr - D > 0 && !visited[curr - D]) {
                visited[curr - D] = true;
                q.add(new int[]{curr - D, currMove + 1});
            }
        }
    }
}