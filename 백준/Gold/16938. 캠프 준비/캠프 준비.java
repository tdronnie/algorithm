import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, l, r, x, caseCount=0;
    static int[] problem;
    static boolean[] selected;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); //문제 수
        l = Integer.parseInt(st.nextToken()); //최소 문제 난이도 합
        r = Integer.parseInt(st.nextToken()); //최대 문제 난이도 합
        x = Integer.parseInt(st.nextToken()); //가장 어려운 문제와 쉬운 문제의 최소 난이도 차이

        st = new StringTokenizer(br.readLine());
        problem = new int[n];
        for (int i = 0; i < n; i++) {
            problem[i] = Integer.parseInt(st.nextToken());
        }

        //2개 이상의 원소를 포함하는 부분집합 구하기
        selected = new boolean[n];
        getPick(0);
        System.out.println(caseCount);
    }
    private static void getPick(int cnt) {
        //n개의 원소 모두 탐색끝냄
        if(cnt == n){
            int min = 1000000, max = -1, pickCnt=0, level=0;
            for (int i = 0; i < n; i++) {
                if(selected[i]){
                    pickCnt++;
                    min = Math.min(min, problem[i]);
                    max = Math.max(max, problem[i]);
                    level += problem[i];
                }
            }
            //문제 구성 조건에 맞는지 확인
            if(pickCnt<2) return; //두 문제 이상
            if(level<l || level>r) return; //난이도 합
            if(max-min < x) return; //난이도 차이
            caseCount++;
            return;

        }
        selected[cnt] = true;
        getPick(cnt + 1);
        selected[cnt] = false;
        getPick(cnt + 1);
    }
}