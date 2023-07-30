import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder(); //결과 저장 위한 sb
        int n = Integer.parseInt(st.nextToken()); //포켓폰 개수
        int m = Integer.parseInt(st.nextToken()); //문제 개수

        //포켓몬 이름은 전부 영어, 첫글자 혹은 마지막 문자만 대문자

        HashMap<String, Integer> poketmon = new HashMap<>(); //포켓몬 저장 해시맵
        List<String> nameList = new ArrayList<>(); //이름 따로 저장

        for (int i = 1; i <= n; i++) {
            String name = br.readLine();
            poketmon.put(name, i); //포켓몬 이름과 숫자, 이름에 따른 숫자 빠르게 찾기 위해서 인덱스로 저장
            nameList.add(name);
        }
        for (int i = 0; i < m; i++) {
            String findSome = br.readLine();

            if(!checkThing(poketmon, findSome)){
                sb.append(poketmon.get(findSome)); //이름에 해당하는 포켓몬 숫자 출력
            }
            else{
                sb.append(nameList.get(Integer.parseInt(findSome)-1)); //숫자로 주어진 경우

            }
                sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    private static boolean checkThing(HashMap<String, Integer> poketmon, String findSome) {

        try {
            Integer.parseInt(findSome); //숫자인지 확인
            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }
}