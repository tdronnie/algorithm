import java.util.*;
class Solution {
    public String[] solution(String[] players, String[] callings) {
        
        HashMap<String, Integer> map = new HashMap<>();
        for(int i=0; i<players.length; i++){
            map.put(players[i], i); //이름과 등수저장
        }
        for(int i=0; i<callings.length; i++){
            int current = map.get(callings[i]); //현재 주목 플레이어 등수
            String front = players[current-1]; //현재 주목 플레이어 등수보다 하나 앞인 플레이어
            //등수로 플레이어 찾기 위해서 player배열도 이용 따라서 갱신도 필요
            players[current] = front; //뒤에 등수로
            players[current-1] = callings[i]; //앞의 등수로
            
            // System.out.println(front+"을 제치고 "+callings[i]+"이 "+(map.get(callings[i])-2)+"등으로");
            map.replace(callings[i], map.get(callings[i])-1); //등수 하나 앞으로
            map.replace(front, map.get(callings[i])+1); //원래 앞서 있던 플레이어 등수 하나 뒤로
            
            
            // System.out.println(map.get(callings[i]));
            // System.out.println(map.get(front));
        }
        return players;
    }
}