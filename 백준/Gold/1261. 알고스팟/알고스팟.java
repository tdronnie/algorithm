import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] moveX = {-1, 0, 1, 0};
    static int[] moveY = {0, 1, 0, -1};
    static int n, m;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = chars[j] - '0';
            }
        }
        System.out.println(findMinRoute(map));
    }

    static int findMinRoute(int[][] map) {

        boolean[][] visited = new boolean[n][m];
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        q.add(new int[]{0, 0, 0});
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] value = q.poll();
            int x = value[0];
            int y = value[1];
            int drill = value[2];

            if (x == n - 1 && y == m - 1) {
                return drill;
            }

            for (int i = 0; i < 4; i++) {
                int newX = x + moveX[i];
                int newY = y + moveY[i];

                if (newX < 0 || newY < 0 || newX >= n || newY >= m || visited[newX][newY]) continue;
                visited[newX][newY] = true;

                if (map[newX][newY] == 1) {
                    q.add(new int[]{newX, newY, drill + 1});
                } else{
                    q.add(new int[]{newX, newY, drill});
                }
            }
        }
        return -1;
    }
}
