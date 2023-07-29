import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        HashMap<String, String> map = new HashMap<>(); //사람에 따른 출입기록 저장하기 위한 해시맵
        StringBuilder sb = new StringBuilder(); //결과 출력 위한 sb
        int n = Integer.parseInt(br.readLine());// 출입기록 수


        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String person = st.nextToken();
            String goInOut = st.nextToken();
            if (goInOut.equals("leave")) { //퇴근할 경우 해시맵에서 삭제
                map.remove(person); //key를 person으로 가지는 맵 항목 삭제
            } else
                map.put(person, goInOut); //사람과 enter 저장
        }

        List<String> collect = map.keySet().stream() //keySet을 stream으로 변환
                .sorted(Comparator.comparing(String::toString).reversed())//Comaparator의 메서드 comparing메서드참조를 통해 key의 String으로 받아 역순 정렬
                .collect(Collectors.toList()); //리스트로 추출
        for (String name :collect) { //이름들 사이 개행
            sb.append(name+"\n");
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}