import java.io.*;

public class Solution {
    private static int[] moveRow = {0, 1, 0, -1}; //우하좌상 이동
    private static int[] moveCol = {1, 0, -1, 0};

    private static int N;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {

            StringBuilder sb = new StringBuilder();
            N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N]; //달팽이 배열
            boolean[][] visited = new boolean[N][N]; //방문처리 배열
            int k = 0;
            int num = 1; //저장할 숫자
            int newX = 0;
            int newY = 0;

            while (true) {
                // 우하좌상 순서로 돌리기
                while (check(newX, newY)) { //범위 유효한 동안
                    if (!visited[newX][newY]) { //방문하지 않은 경우
                        map[newX][newY] = num;
                        visited[newX][newY] = true;
                    } else {
                        break;
                    }
                    newX += moveRow[k];
                    newY += moveCol[k];
                    num++;
                }
                // 앞으로 한 칸 간 것 뺴주기
                newX -= moveRow[k];
                newY -= moveCol[k];

                k++;
                if (k == 4) //우하좌상 다 돌면 다시 우로 시작
                    k = 0;
                newX += moveRow[k];
                newY += moveCol[k];

                //이동 했을 때 범위에 벗어나거나 이미 방문한 곳이면 종료
                if (!check(newX, newY) || visited[newX][newY]) {
                    break;
                }
            }
            sb.append("#"+test_case+"\n");
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    sb.append(map[i][j] + " ");
                }
                sb.append("\n");
            }
            bw.write(sb.toString());

        }
        bw.flush();
        bw.close();
        br.close();
    }

    //범위 체크 메서드
    public static boolean check(int newX, int newY){
        return newX >= 0 && newY >= 0 && newX < N && newY < N;
    }
}
