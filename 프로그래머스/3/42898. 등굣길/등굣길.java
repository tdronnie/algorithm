class Solution {
    
    int[] dx = {0, 1};
    int[] dy = {1, 0};
    public int solution(int m, int n, int[][] puddles) {
        
        int[][] map = new int[n+1][m+1];
        for(int i=0; i< puddles.length; i++){
            map[puddles[i][1]][puddles[i][0]] = 1;            
        }
        
        int[][] shortest = new int[n+1][m+1];
        shortest[1][1] = 1;
        
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(i == 1 && j == 1 || map[i][j] == 1) continue;
                
                // 두번째 행부터 윗행 최단경로 개수 더해주기
                if(i > 1){
                    shortest[i][j] += shortest[i-1][j];
                }
                
                // 두번째 열부터 왼쪽열 최단경로 개수 더해주기
                if(j > 1){
                    shortest[i][j] += shortest[i][j-1];    
                }
                
                // 나머지 구하기
                shortest[i][j] %= 1000000007;
                
            }
        }
        
        return shortest[n][m];
        
    }
}