import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    static int[] moveX = {-1, 0, 1, 0};
    static int[] moveY = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        int minBroke = makeMaze(map, n);
        System.out.println(minBroke);
    }

    static int makeMaze(int[][] map, int n) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        boolean[][] visited = new boolean[n][n];
        int minBroke = Integer.MAX_VALUE;
        pq.add(new int[]{0, 0, 0});

        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int x = poll[0];
            int y = poll[1];
            int broke = poll[2];

            if(visited[x][y]) continue;
            visited[x][y] = true;

            if (x == n - 1 && y == n - 1) {
                minBroke = Math.min(minBroke, broke);
                continue;
            }

            for(int k=0; k<4; k++){

                int newX = x + moveX[k];
                int newY = y + moveY[k];

                if(newX <0 || newY < 0 || newX >= n ||newY >= n) continue;

                //검은 방
                if (map[newX][newY] == 0) {
                    pq.add(new int[]{newX, newY, broke + 1});
                } else{ //흰 방
                    pq.add(new int[]{newX, newY, broke});
                }
            }
        }
        return minBroke;
    }
}