import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m;
    static int[] weight, selected;
    static ArrayList<Integer> ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); //소 마리수
        m = Integer.parseInt(st.nextToken()); //선별할 수

        st = new StringTokenizer(br.readLine());
        weight = new int[n];
        for (int i = 0; i < n; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        selected = new int[m]; //선택된 m마리 소
        ans = new ArrayList<>(); //가능한 소수 담는 리스트
        pick(0, 0); //선별할 소 m마리 뽑기

//        중복제거 불가
//        Collections.sort(ans);
        if(ans.isEmpty()){
            System.out.println(-1);
            return;
        }
        Set<Integer> sortedList = new TreeSet<>(ans);
        for (int val :
                sortedList) {
            System.out.print(val + " ");
        }
    }

    public static void pick(int cnt, int idx) {
        if (cnt == m) {
            //m마리 뽑기 완료
            //몸무게 합이 소수인지 확인 소수라면 합 리턴
            sumIsPrime(selected);
            return;

        }
        for (int i = idx; i < n; i++) {
            selected[cnt] = weight[i];
            pick(cnt + 1, i + 1);

        }
    }

    private static void sumIsPrime(int[] selected) {

        int sum = 0;
        for (int cow : selected) {
            sum += cow;
        }
        for (int i = 2; i <= Math.sqrt(sum); i++) {
            if (sum % i == 0)
                return;
        }
        ans.add(sum);
    }
}