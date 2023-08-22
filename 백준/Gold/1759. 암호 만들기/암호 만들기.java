import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int l, c;
    private static char[] input; //입력 배열
    private static char[] ans; //정답 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 암호는 오름차순으로 이루어짐
        // 최소 한개의 모음, 두개의 자음
        StringTokenizer st = new StringTokenizer(br.readLine());

        l = Integer.parseInt(st.nextToken()); // 서로 다른 알파벳 소문자 개수
        c = Integer.parseInt(st.nextToken()); // 사용 문자 종류

        input = new char[c];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < c; i++)
            input[i] = st.nextToken().charAt(0);

        //단어 오름차순 정렬하기 위해서 미리 정렬
        Arrays.sort(input);
        ans = new char[l];

        //모음 자음 조합 단어 만들기
        comb(0, 0, 0, 0);


    }

    public static void comb(int cnt, int idx, int cnt1, int cnt2) {

        //길이 충족되었을 때, 모음이 최소 한개 이상, 자음이 최소 두개 이상이라면 출력
        if (cnt == l) {
            if (cnt1 >= 1 && cnt2 >= 2) {
                for (char ch : ans) {
                    System.out.print(ch);
                }
                System.out.println();
            }
            return;
        }

        //자음인 경우 자음 카운트 하나 늘려주고 조합 돌리기
        for (int i = idx; i < c; i++) {

            char ch = input[i]; //문자
            ans[cnt] = ch;  //현재 턴의 문자 정답배열에 넣어주기
            //모음인경우 모음 카운트 하나 늘려주고 조합 돌리기
            //인덱스는 중복 불가하므로 탐색한 인덱스 뒤로 넘겨줌
            if ("aeiou".indexOf(ch) != -1) { // 모음인 경우
                comb(cnt + 1, i + 1, cnt1 + 1, cnt2);
            }
            //자음인 경우 자음 카운트 하나 늘려주고 조합 돌리기
            else {
                comb(cnt + 1, i + 1, cnt1, cnt2 + 1);
            }
        }
    }
}