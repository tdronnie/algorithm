import java.util.*;
class Solution {
    int count = 0;
    boolean[] visited;
    public int solution(int n, int[][] computers) {
        
        // 모든 노드에 대해서 네트워크 탐색
        visited = new boolean[n];
        for(int i=0; i<n; i++){
            if(visited[i]) continue; // 이전 네트워크에 속하지 않은 방문하지 않은 컴퓨터라면 탐색 시작
            visited[i] = true;
            findNetwork(i, n, computers, visited);
            count++; // 네트워크 개수++
        }
        
        return count;
        
    }
    
    public void findNetwork(int start, int n, int[][] computers, boolean[] visited){
        
        for(int i=0; i<n; i++){
            // 같은 컴퓨터가 아니고, 연결된 컴퓨터, 방문하지 않았다면 탐색
            if(i != start && computers[start][i] == 1 && !visited[i]){ 
                visited[i] = true;
                findNetwork(i, n, computers, visited);    
            }
        }
    }
}