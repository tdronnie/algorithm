import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        HashMap<String, String[]> team = new HashMap<>(); // key: team, value : List<member>
        HashMap<String, String> member = new HashMap<>(); //key: member, value : team
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 팀 수
        int m = Integer.parseInt(st.nextToken()); // 문제 수

        // 팀과 팀에 속하는 멤버들 저장
        for (int i = 0; i < n; i++) {
            String teamName = br.readLine();
            int memberNum = Integer.parseInt(br.readLine());

            // 멤버 이름 저장하기 위한 배열, 끝 인덱스에 teams에 저장된 팀이름 인덱스 넣기 위해 멤버수+1로 크기설정
            String[] strList = new String[memberNum];
            for (int j = 0; j < memberNum; j++) {
                strList[j] = br.readLine(); // 팀을 키로하는 맵에 저장
                member.put(strList[j], teamName); //멤버를 키로하는 맵에 저장
            }
            team.put(teamName, strList); // 팀이름을 key로 멤버배열을 value로 저장
        }

        for (int i = 0; i < m; i++) {
            StringBuilder sb = new StringBuilder();
            String find = br.readLine(); // 멤버 or 그룹이름
            // 퀴즈 종류 받아서 퀴즈 풀기
            switch (br.readLine()) {
                case "0":
                    // 팀 이름 주어짐
                    String[] strArr = team.get(find); // 팀의 멤버
                    Arrays.sort(strArr); // 멤버 이름으로 사전순 정렬
                    for (String string : strArr) { //저장
                        sb.append(string).append("\n");
                    }
                    break;
                case "1":
                    // 멤버이름 주어짐
                    sb.append(member.get(find)).append("\n"); //멤버이름을 키로하는 값인 팀이름 저장
                    break;
            }
            bw.write(sb.toString());
        }
        bw.flush();
        br.close();
        bw.close();
    }

}