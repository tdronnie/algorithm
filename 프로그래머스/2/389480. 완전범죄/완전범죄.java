import java.util.*;
class Solution {
    public int solution(int[][] info, int n, int m) {
        
        int[][] dp = new int[info.length + 1][m]; // dp[턴][b의 흔적누적값] = a의 최소 흔적누적값
        for(int[] value : dp){
            Arrays.fill(value, Integer.MAX_VALUE);    
        }
        
        dp[0][0] = 0; // 0번째 b가 0개 훔친 경우 a의 최솟값 0
        
        for(int i=0; i<info.length; i++){
            for(int j=0; j<m; j++){
                
                if(dp[i][j] == Integer.MAX_VALUE){ // 현재 경우에 올 수 없는 경우 넘어가기
                    continue;
                }
                
                // a가 훔쳐도 붙잡히지 않는경우 업데이트
                if(dp[i][j] + info[i][0] < n){
                    dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j] + info[i][0]);    
                }
                
                // b가 훔쳐도 붙잡히지 않는경우 업데이트
                if(j + info[i][1] < m){
                    dp[i+1][j + info[i][1]] = Math.min(dp[i+1][j + info[i][1]], dp[i][j]);    
                }
                
            }
        }
        
        // 모든 b누적값에 대해 마지막 턴의 a누적 최솟값 구하기
        int min = Integer.MAX_VALUE;
        for(int i=0; i<m; i++){
            min = Math.min(min, dp[info.length][i]);
        }
        
        return min == Integer.MAX_VALUE ? -1 : min;
        
    }
}