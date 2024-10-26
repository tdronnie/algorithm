import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int minStep = Integer.MAX_VALUE;
    static HashMap<Integer, Integer> ladder, snake;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ladder = new HashMap<>();
        snake = new HashMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            ladder.put(from, to);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            snake.put(from, to);
        }

        BFS(1);
        System.out.print(minStep);
    }

    static int[] move = {1, 2, 3, 4, 5, 6};

    public static void BFS(int start) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[101];
        Arrays.fill(visited, false);
        queue.add(new int[]{start, 0});
        visited[start] = true;

        while(!queue.isEmpty()) {
            int[] now = queue.poll();

            if(now[0] == 100){
                minStep = Math.min(now[1], minStep);
                continue;
            }

            for(int i=0; i<6; i++){
                int next = now[0] + move[i];
                int step = now[1];

                if(next > 100 || visited[next]) continue;

                visited[next] = true;
                if(ladder.containsKey(next)){
                    queue.add(new int[]{ladder.get(next), step+1});
                    continue;
                }
                if(snake.containsKey(next)){
                    queue.add(new int[]{snake.get(next), step+1});
                    continue;
                }
                queue.add(new int[]{next, step+1});
            }
        }
    }
}