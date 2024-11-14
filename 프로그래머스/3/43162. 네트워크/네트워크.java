import java.util.*;
class Solution {
    public int solution(int n, int[][] computers) {
        
        boolean[] visited = new boolean[n];
        int count = 0;
        
        for(int i=0; i<n; i++){
            if(!visited[i]){
                count++;
                detectArea(visited, n, computers, i);
            }
                
        }
        return count;
    }
    
    public void detectArea(boolean[] visited, int n, int[][] computers, int start){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        visited[start] = true;
        
        while(!q.isEmpty()){
            int com = q.poll();
            
            for(int i=0; i<n; i++){
                if(computers[com][i] == 0 || visited[i]) continue;
                
                visited[i] = true;
                q.add(i);
            }
        }
    }
}