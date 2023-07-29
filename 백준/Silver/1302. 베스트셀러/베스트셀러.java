import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine()); //하루동안 팔린 책의 개수

        HashMap<String, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            String book = br.readLine();
            if (map.containsKey(book))
                map.put(book,  map.get(book)+ 1); //이미 나왔던 책이면 기존 개수+1
            else {
                map.put(book, 1); //나오지 않은 책이라면 1로 시작
            }
        }

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
        Collections.sort(entries, (o1, o2) -> {
            if(o1.getValue() == o2.getValue()) //팔린 수 같을 경우
                return o1.getKey().compareTo(o2.getKey()); //책 이름 사전 순 정렬, 오름차순 정렬
            return o2.getValue().compareTo(o1.getValue()); //팔린 수에 따라 내림차순 정렬
        });

        sb.append(entries.get(0).getKey() + "\n"); //정렬된 후 가장 앞의 책 출력
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}