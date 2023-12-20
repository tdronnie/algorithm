import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        //m개의 공유기를 딱 설치할 수 있도록 거리 줄여가기, 간격이 너무 좁으면 거리 벌려줌
        //처음과 끝 거리 초기화
        int start = 1;
        int end = arr[arr.length - 1] - arr[0] + 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            //설치 가능한 공유기 개수 체크
            if (checkCnt(mid) < c) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        //start는 mid+1
        System.out.println(start-1);
    }

    private static int checkCnt(int mid) {
        int cnt = 1; //첫번째 집 설치
        int preLoc = arr[0];

        //mid만큼의 간격으로 공유기 놔보기
        for (int i = 1; i < n; i++) {
            int newLoc = arr[i];

            //mid간격보다 같거나 커질 때 공유기 놓기
            if (newLoc - preLoc >= mid) {
                cnt++;
                preLoc = newLoc;
            }
        }
        return cnt;
    }

}