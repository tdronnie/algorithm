import java.util.*;
class Solution {
    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];
        int count = 0;
        
        for(int i=0; i<n; i++){
            if(visited[i]) continue;
            Queue<Integer> q = new ArrayDeque<>();
            q.add(i);
            visited[i] = true;
            
            while(!q.isEmpty()){
                int next = q.poll();
                
                for(int j=0; j<n; j++){
                    if(visited[j] || computers[next][j] == 0) continue;
                    
                    visited[j] = true;
                    q.add(j);
                }
            }
            
            count++;     
        }
        return count;
    }
}