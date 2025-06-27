import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static int len;
    static int[][] map;
    static HashMap<Integer, Character> changes;
    static ArrayList<int[]> snake;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        len = Integer.parseInt(br.readLine());
        map = new int[len+1][len+1];
        snake = new ArrayList<>();

        int apples = Integer.parseInt(br.readLine());
        for (int i = 0; i < apples; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map[x][y] = 1;
        }

        int dirs = Integer.parseInt(br.readLine());
        changes = new HashMap<>();
        for (int i = 0; i < dirs; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            changes.put(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
        }

        snake.add(new int[]{1, 1});
        map[1][1] = 2;
        System.out.println(move(1,1, 1, 1));

    }

    static int move(int x, int y, int dir, int time) {

        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if(nx < 1 || ny <1 || nx > len || ny > len) return time;

        if(map[nx][ny] == 2)return time;

        //사과 있음, 현재 칸 뱀으로 만들기
        if (map[nx][ny] == 1) {
            map[nx][ny] = 2;
            snake.add(0, new int[]{nx, ny});
        }

        //빈칸이면 현재 칸 뱀으로, 끝지점 찾아서 빈칸으로 만들기
        else if (map[nx][ny] == 0) {
            map[nx][ny] = 2;
            snake.add(0, new int[]{nx, ny});
            int[] removeLoc = snake.remove(snake.size()-1);
            map[removeLoc[0]][removeLoc[1]] = 0;

        }

        if (changes.containsKey(time)) {
            char chDir = changes.get(time);
            switch (chDir) {
                case 'L':
                    return move(nx, ny, (dir - 1 + 4) % 4, time + 1);
                case 'D':
                    return move(nx, ny, (dir + 1 + 4) % 4, time + 1);
            }
        }
         else {
            return move(nx, ny, dir, time + 1);
        }

        return time;
    }
}
