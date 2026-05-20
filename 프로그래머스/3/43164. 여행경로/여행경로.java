import java.util.*;
class Solution {
    
    Map<String, PriorityQueue<String>> map; // 서로 갈 수 있는 공항 관계 저장 map
    List<String> route; // 경로 탐색 queue
        
    public String[] solution(String[][] tickets) {
        
        map = new HashMap<>(); 
        route = new ArrayList<>();
        for(String[] ticket : tickets){
            map.computeIfAbsent(ticket[0], p -> new PriorityQueue<String>()).add(ticket[1]); // computeIfAbsent value값 function 인터페이스, 알파벳 순서가 가장 앞서는 공항 선택
        }

        dfs("ICN");
        
        Collections.reverse(route); // dfs로 역순저장된 것 뒤집기
        return route.stream().toArray(String[]::new);
    }
    
    void dfs(String current) {

        // 출발 공항이면서 연결된 공항 모두 고려
        while (map.containsKey(current) && !map.get(current).isEmpty()) {
            String next = map.get(current).poll();
            dfs(next);
        }

        route.add(current); 
    }
    
}