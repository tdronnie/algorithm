import java.util.*;
class Solution {
    
    public int solution(int n, int[][] costs) {
        
        int[][] relation = new int[n][n];
        for(int[] cost : costs){
            relation[cost[0]][cost[1]] = cost[2];
            relation[cost[1]][cost[0]] = cost[2];
        }
        
        boolean[] visited = new boolean[n];
        int[] minCost = new int[n]; // 각 섬이 연결될 때 최소 건설 비용
        Arrays.fill(minCost, Integer.MAX_VALUE);
        
        int costSum = 0;
        minCost[0] = 0;
        
        for(int i=0; i<n; i++){
            int min = Integer.MAX_VALUE;
            int next = -1;
            
            for(int j=0; j<n; j++){
                if(!visited[j] && minCost[j] < min){
                    min = minCost[j];
                    next = j;
                }    
            }
            
            visited[next] = true;
            costSum += min;
            
            // 그 다음 인접한 섬들과의 최소거리 갱신
            for(int island = 0; island < n; island++){
                if(relation[next][island] != 0 && !visited[island] && relation[next][island] < minCost[island]){
                    minCost[island] = relation[next][island];
                }
            }
            
        }
        
        
        return costSum;
        
    }
}