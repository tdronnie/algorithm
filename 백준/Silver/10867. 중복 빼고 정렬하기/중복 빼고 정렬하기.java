import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine()); //n개의 정수 오름차순 정렬하기

        HashMap<Integer, Integer> map = new HashMap<>(); //중복 검사하기 위한 HashMap
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            map.put(Integer.parseInt(st.nextToken()), 1); //맵 자체가 키가 중복없이 받음
        }

        List<Integer> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);
        for (int num: keys
             ) {
            sb.append(num + " ");
        }
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}