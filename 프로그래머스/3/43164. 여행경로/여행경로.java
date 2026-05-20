import java.util.*;
class Solution {
    
    Map<String, PriorityQueue<String>> map;
    List<String> route;
        
    public String[] solution(String[][] tickets) {
        
        map = new HashMap<>(); // 서로 갈 수 있는 공항 관계 저장 map
        Queue<String> q = new ArrayDeque<>(); // 경로 탐색 queue
        // Set<String> visited = new HashSet<>(); // 방문 처리 set
        route = new ArrayList<>(); // 정답 리스트
        
        for(String[] ticket : tickets){
            map.computeIfAbsent(ticket[0], p -> new PriorityQueue<String>()).add(ticket[1]); // computeIfAbsent value값 function 인터페이스
        }
        
        // visited.add("ICN");
        // q.add("ICN");
        dfs("ICN");
//         while(!q.isEmpty()){
//             String current = q.poll();
            
//             // 현재 공항과 연결된 공항들 중 방문하지 않았고 알파벳 순서가 가장 앞서는 공항 선택
//             if(map.containsKey(current) && !map.get(current).isEmpty()){
                
//                 String next = map.get(current).poll();
//                 q.add(next);
//                 route.add(next);
//             }      
//         }
        
        Collections.reverse(route);
        return route.stream().toArray(String[]::new);
    }
    
    private void dfs(String current) {
        PriorityQueue<String> pq = map.get(current);

        while (map.containsKey(current) && !pq.isEmpty()) {
            String next = pq.poll();
            dfs(next);
        }

        route.add(current); 
    }
    
}