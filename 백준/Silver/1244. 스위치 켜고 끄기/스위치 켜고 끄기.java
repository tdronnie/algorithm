import java.io.*;
import java.util.*;

public class Main {
    static int[] arr; // 스위치 저장 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine()); // 스위치 개수
        arr = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine()); // 스위치 입력
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int stu = Integer.parseInt(br.readLine()); // 학생 수
        for (int i = 0; i < stu; i++) {
            st = new StringTokenizer(br.readLine());
            String person = st.nextToken(); // 성별
            int swchNum = Integer.parseInt(st.nextToken()); // 스위치 번호

            switch (person) {
                case "1": // 남자
                    // 남학생이라면 자신의 숫자의 배수인 스위치 상태 바꾸기
                    changeM(swchNum);
                    break;

                case "2": // 여자
                    // 여학생이라면 최대 좌우 대칭 구간의 스위치 상태 바꾸기
                    changeW(swchNum);
                    break;
            }
        }
        for(int i=1; i<=n; i++) {
            bw.write(arr[i]+ " ");
            if(i % 20 == 0) //20개씩 출력
                bw.newLine();

        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static void changeSwch(int swchNum) {
        if (arr[swchNum] == 0)
            arr[swchNum] = 1;
        else
            arr[swchNum] = 0;
    }

    private static void changeW(int swchNum) {
        changeSwch(swchNum); //일단 자기 자신 바꾸기
        int i = swchNum - 1;
        int j = swchNum + 1;

        while (i >= 1 && j < arr.length) {
            // 대칭 아니라면 자신만 바꾸고 끝내기
            if (arr[i] != arr[j]) {
                return;
            } else {
                // 대칭이라면 스위치 상태 변경
                changeSwch(i--);
                changeSwch(j++);
            }
        }
    }

    private static void changeM(int swchNum) {
        // 배수인 경우 바꾸기
        for (int i = swchNum; i < arr.length; i++) {
            if (i % swchNum == 0)
                changeSwch(i);
        }
    }
}
