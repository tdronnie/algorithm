import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        HashMap<String, Integer> map = new HashMap<>();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        //n개의 문자열 공백없이 붙여주기
        for(int i=0; i<n; i++){
            map.put(br.readLine(), 0); //문자열을 key로 카운트는 0으로 초기화
        }

        int cnt=0;
        //m개의 문자열들이 해시맵에 저장된 문자열에 포함되는지 탐색
        for(int i=0; i<m; i++){
            if(map.containsKey(br.readLine()))
                cnt++;
        }
        bw.write(String.valueOf(cnt));
        bw.flush();
        br.close();
        bw.close();
    }
}