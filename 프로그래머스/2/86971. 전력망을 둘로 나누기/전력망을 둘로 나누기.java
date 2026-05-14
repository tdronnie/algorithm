import java.util.*;
class Solution {
    
    static List<Integer>[] connection;
    public int solution(int n, int[][] wires) {
        
        connection = new ArrayList[n + 1];
        int minDiff = n; // 두 그룹의 노드 차이 최솟값
        
        // 트리 정보 저장
        for(int i=1; i<=n; i++){
            connection[i] = new ArrayList<>();
        }
        
        for(int[] wire : wires){
            connection[wire[0]].add(wire[1]);
            connection[wire[1]].add(wire[0]);
        }
        
        // 최대 99번 엣지 끊기, bfs로 끊긴 엣지 제외 노드 탐색
        for(int[] wire : wires){
            int count = bfs(wire[0], wire[1], n);
            
            // 두 그룹 노드 차이 계산
            minDiff = Math.min(minDiff, Math.abs(count - (n - count)));
            
        }
        
        return minDiff;
    }
    
    public int bfs(int start, int ex, int n){
        
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        int count = 1;
        q.add(start);
        visited[start] = true;
        
        while(!q.isEmpty()){
            int tower = q.poll();
            
            // 트리 순회, 끊긴 엣지로는 가지 않는다
            for(int next : connection[tower]){
                if(next == ex || visited[next]) continue;
                visited[next] = true;
                count++;
                q.add(next);
            }
        }
        
        return count;
    }
}