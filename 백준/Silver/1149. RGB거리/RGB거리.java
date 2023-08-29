import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] r,g,b, ansR, ansG, ansB;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        //각 집을 인덱스로 하는 색의 비용 저장 배열
        r = new int[n];
        g = new int[n];
        b = new int[n];
        ansR = new int[n];
        ansG = new int[n];
        ansB = new int[n];

        for(int i=0; i<n; i++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            r[i] = Integer.parseInt(st.nextToken());
            g[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());

        }
        
        //메모이제이션 사용하기 위해서 가장 첫번째 집의 RGB값을 결과 배열에 저장
        ansR[0] = r[0];
        ansG[0] = g[0];
        ansB[0] = b[0];


        //마지막 세곳중 더 최솟값 나오는 곳이 정답
        int min = Math.min(setColor(n-1, 'r'), setColor(n-1, 'g'));
        min = Math.min(min, setColor(n-1, 'b'));
        System.out.println(min);

    }

    //n개 집 각각 컬러고르기
    public static int setColor(int n, char ch){
        //아직 칠하지 않은 집의 경우
        if(ch == 'r' && ansR[n] == 0) {
            ansR[n] = Math.min(setColor(n-1, 'g'), setColor(n-1, 'b')) + r[n];//현재 페인트 칠하는 비용도 포함시켜주어야 함
        }
        if(ch == 'g' && ansG[n] == 0) {
            ansG[n] = Math.min(setColor(n-1, 'r'), setColor(n-1, 'b')) + g[n];
        }
        if(ch == 'b' && ansB[n] == 0){
                ansB[n] = Math.min(setColor(n-1, 'r'), setColor(n-1, 'g')) + b[n];
        }
        //칠해진 경우
        if(ch == 'r')
            return ansR[n];
        else if(ch == 'g')
            return ansG[n];
        else
            return ansB[n];
    }
}
