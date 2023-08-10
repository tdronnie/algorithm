import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.*;

public class Main {

    private static int[][] arr;
    private static int[] moveR = {0, 1, 0, -1};
    private static int[] moveC = {1, 0, -1, 0};
    private static boolean[][] visited; //배열 회전 위한 방문배열
    private static boolean[] visitedK; //연산 순열 위한 방문배열
    private static int n, m, k;
    private static int min = Integer.MAX_VALUE;
    private static int[][] cases;
    private static int[] rslt;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 행
        m = Integer.parseInt(st.nextToken()); // 열
        k = Integer.parseInt(st.nextToken()); // 연산 개수
        arr = new int[n + 1][m + 1]; // 인덱스 1부터 저장
        rslt = new int[k];
        cases = new int[k][3];

        // 배열 저장
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // k번 연산 수행
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            cases[i][0] = Integer.parseInt(st.nextToken());
            cases[i][1] = Integer.parseInt(st.nextToken());
            cases[i][2] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[n + 1][m + 1];
        visitedK = new boolean[k];
        permutation(0, k);

        System.out.println(min);
    }

    //연산1 -> 연산2가 더 최솟값 가지는지 연산2 -> 연산1이 더 최솟값 가지는지 순열
    private static void permutation(int cnt, int k) {

        // 연산 k번 완료
        if (cnt == k) {
            int[][] cpy = new int[n + 1][m + 1]; //여러 순서의 방법에 대해 복사 배열로 연산 진행
            for (int i = 1; i <= n; i++)
                for (int j = 1; j <= m; j++)
                    cpy[i][j] = arr[i][j];


            for (int i = 0; i < rslt.length; i++) { //저장한 연산 순서 종류만큼
                //rslt[i] -> 특정연산, case[i][0] ->특정 연산의 r, case[i][1] ->특정 연산의 c, case[i][2] ->특정 연산의 s
                int a = cases[rslt[i]][0] - cases[rslt[i]][2]; //r-s
                int b = cases[rslt[i]][1] - cases[rslt[i]][2]; //c-s
                int num = 0;
                while (a + num < cases[rslt[i]][0] && b + num < cases[rslt[i]][1]) {
                    visited = new boolean[n + 1][m + 1];
                    turnArr(a + num, b + num, a + 2 * cases[rslt[i]][2] - num, b + 2 * cases[rslt[i]][2] - num, cpy); // 시작 행렬좌표와 끝 행렬좌표 주고 계산, 양쪽 행렬 1씩 줄임
                    num++;
                }
            }
            getRowMin(cpy);
            return;
        }
        //순열 만들기
        for (int i = 0; i < k; i++) {
            if (!visitedK[i]) {
                visitedK[i] = true;
                rslt[cnt] = i; //연산 가능순서 저장
                permutation(cnt + 1, k);
                visitedK[i] = false;
            }
        }
    }

    private static void getRowMin(int[][] cpy) {
        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int j = 1; j <= m; j++) {
                sum += cpy[i][j];
            }
            min = Math.min(min, sum);
        }
    }

    private static void turnArr(int startR, int startC, int endR, int endC, int[][] cpy) {
        int k = 0;
        int newX = startR, newY = startC;
        Queue<Integer> q = new LinkedList<>();// 현재 값 다음 값에 저장하기 위한 큐 이용 ->(1,0)->(0,0)->...
        q.add(cpy[newX][newY]); // 처음 좌표 넣어주기
        //우하좌상 순

        while (!visited[startR][startC]) { // 처음 좌표에 방문하지않은 동안 계속 진행

            newX += moveR[k];
            newY += moveC[k];
            if (newX >= startR && newY >= startC && newX <= endR && newY <= endC && !visited[newX][newY]) {
                visited[newX][newY] = true; // 현재 좌표 방문처리
                q.add(cpy[newX][newY]); // 현재 값 큐에 저장
                cpy[newX][newY] = q.poll();// 큐 안에 담긴 값은 바로 예전 값

            } else { // 더이상 진행 불가하면 방향 바꿈
                // 한칸 더 간것 빼주기
                newX -= moveR[k];
                newY -= moveC[k];
                k++;
                if (k == 4) // 시계 한바퀴 다 돌았다
                    k = 0;
            }
        }
    }
}