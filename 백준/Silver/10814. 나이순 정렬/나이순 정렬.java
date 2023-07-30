import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine()); //회원 수

        HashMap<Integer, List<String>> map = new HashMap<>(); //같은 나이를 가지는 사람은 나이를 키로 가지고 리스트에 여러 이름 저장
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            
            //만약에 같은 나이를 가지는 사람이 여러명 있다면 리스트로 만들어서 해시맵에 저장
            if(map.containsKey(age)){
                List<String> values = map.get(age); //같은 나이를 가지는 사람들
                values.add(name);
                map.put(age, values);
            }

            else{
                List<String> values = new ArrayList<>();
                values.add(name);
                map.put(age, values);
            }
        }

        for (Integer age: map.keySet()){
            for (String name : map.get(age)) {
                sb.append(age+" "+name+"\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}