import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int[] moveX = {-1, 0, 1, 0};
    static int[] moveY = {0, 1, 0 ,-1};
    static int[][] checkMap, map;
    static int n;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int num = 1;
        n = Integer.parseInt(br.readLine());

        while(n != 0) {

            map = new int[n][n];
            checkMap = new int[n][n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    checkMap[i][j] = Integer.MAX_VALUE;
                }
            }

            findMinMinus();
            sb.append("Problem ").append(num++).append(": ").append(checkMap[n - 1][n - 1]).append("\n");

            n = Integer.parseInt(br.readLine());
        }

        System.out.println(sb);

    }

    static void findMinMinus() {
        boolean[][] visited = new boolean[n][n];
        PriorityQueue<int[]> q =  new PriorityQueue<>(((o1, o2) -> o1[2] - o2[2]));

        checkMap[0][0] = map[0][0];
        q.add(new int[]{0, 0, map[0][0]});

        while (!q.isEmpty()) {
            int[] value = q.poll();
            int x = value[0];
            int y = value[1];
            int cost = value[2];

            if(visited[x][y]) continue;
            visited[x][y] = true;

            for (int k = 0; k < 4; k++) {
                int newX = x + moveX[k];
                int newY = y + moveY[k];

                if(newX < 0 || newY < 0 || newX >= n || newY >= n) continue;
                if(visited[newX][newY]) continue;

                int newCost = cost + map[newX][newY];

                if(checkMap[newX][newY] > newCost){
                    checkMap[newX][newY] = newCost;
                    q.add(new int[]{newX, newY, newCost});
                }
            }
        }
    }
}