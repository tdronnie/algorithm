class Solution {
    public int solution(int m, int n, int[][] puddles) {
        
        int[][] map = new int[n][m];
        int[][] move = new int[n][m];
        move[0][0] = 1;
        
        for(int i=0; i<puddles.length; i++){
            map[puddles[i][1]-1][puddles[i][0]-1] = 1;
        }
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(map[i][j] == 1) continue;
                if(i > 0) move[i][j] = (move[i][j] + move[i-1][j]) % 1000000007;
                if(j > 0) move[i][j] = (move[i][j] + move[i][j-1]) % 1000000007;
            }
        }
        
        return move[n-1][m-1];
        
    }
}