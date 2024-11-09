import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Integer.parseInt(st.nextToken());
            long y = Integer.parseInt(st.nextToken());

            System.out.println(findShort(x, y));
        }
    }

    public static long findShort(long start, long end) {

        //거리에 따라 경우의 수 나누기
        long distance = end - start;
        long sqrt = (long)(Math.sqrt(distance));

        //거리가 제곱수라면 홀수
        if(distance == sqrt * sqrt){
            return sqrt * 2 - 1;
        }
        //제곱수 사이에서 거리^2 + 거리 이하라면
        if(distance <= sqrt * sqrt + sqrt){
            return sqrt * 2;
        }
        else{ //초과라면
            return sqrt * 2 + 1;
        }
    }
}