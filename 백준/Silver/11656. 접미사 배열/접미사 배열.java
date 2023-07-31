import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();
        List<String> list = new ArrayList<>(); //부분문자열 저장 위한 list
        StringBuilder sb = new StringBuilder(); //부분문자열 출력을 위한 sb

        for(int i=0; i<str.length(); i++){
            list.add(str.substring(i)); //시작 인덱스를 하나씩 더해가면서 부분문자열 구하기
        }
        
        //사전 순 정렬
        Collections.sort(list, (String::compareTo)); //(o1, o2) -> o1.compareTo(o2)

        for (String s: list
             ) {
            sb.append(s+"\n");
        }
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
