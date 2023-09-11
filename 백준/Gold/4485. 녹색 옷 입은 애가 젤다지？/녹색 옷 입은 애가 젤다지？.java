import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[][] map, minus;
    static boolean[][] visited;
    static int[] moveR = {-1, 1, 0, 0};
    static int[] moveC = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = 1;

        //입력이 0이 들어올 때까지 계속 입력 받기
        while (true) {
            n = Integer.parseInt(br.readLine());
            if (n == 0)
                break;

            map = new int[n][n];
            visited = new boolean[n][n];
            minus = new int[n][n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    minus[i][j] = Integer.MAX_VALUE; //다익스트라 초기화
                }
            }

            bfs();
            System.out.println("Problem " + test++ + ": " + minus[n - 1][n - 1]); //가장 마지막칸까지 가는데 가장 최소 비용(루피 조금만 잃음)


        }
    }

    public static boolean isValid(int r, int c) {
        if (r < 0 || r >= n || c < 0 || c >= n)
            return false;
        return true;
    }

    public static void bfs() {
        Queue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2]-o2[2];
            }
        });

        q.add(new int[]{0, 0, map[0][0]}); //초기에 처음 좌표와 처음 좌표까지 가는 최단거리 큐에 넣기
        minus[0][0] = map[0][0];

        while (!q.isEmpty()) {
            int[] val = q.poll();
            int valR = val[0];
            int valC = val[1];
            int valMinus = val[2];

            for (int k = 0; k < 4; k++) {
                int newR = valR + moveR[k];
                int newC = valC + moveC[k];

                if (isValid(newR, newC)) {
                    if (minus[newR][newC] > valMinus + map[newR][newC]) {
                        minus[newR][newC] = valMinus + map[newR][newC];
                        q.add(new int[]{newR, newC, minus[newR][newC]});
                    }
                }
            }
        }
    }
}
