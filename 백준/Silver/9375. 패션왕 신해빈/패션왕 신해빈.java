import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine()); //테스트케이스 수
        for (int test_case = 1; test_case <= T; test_case++) {

            HashMap<String, Integer> map = new HashMap<>();
            int n = Integer.parseInt(br.readLine()); //의상 수

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine()); //의상과 종류 입력받기
                String stuff = st.nextToken();
                String category = st.nextToken();
                map.put(category, map.getOrDefault(category, 0) + 1); //종류 별 의상들 카운트, 의상 이름은 모두 다름
            }
            //의상이 k개라면 안입는 것 포함 k+1번의 방법이 있음
            int rslt = 1;
            for (Integer cnt: map.values()){
                rslt *= (cnt+1); //각 의상 개수+1 만큼 조합들... 모든 종류에 대한 조합이므로 곱
            }
            System.out.println(rslt-1); //공집합 제외
        }
    }
}
