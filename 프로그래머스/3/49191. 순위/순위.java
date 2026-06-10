class Solution {
    public int solution(int n, int[][] results) {
        
        int[][] order = new int[n+1][n+1];
        int count = 0;
        
        for(int[] r : results){
            order[r[0]][r[1]] = 1;
        }
        
        // 4가 2를 이기고, 2가 5를 이겼다면 4가 5를 이김
        for(int mid=1; mid<=n; mid++){
            for(int i=0; i<=n; i++){
                for(int j=0; j<=n; j++){
                    if(order[i][mid] == 1 && order[mid][j] == 1){
                        order[i][j] = 1;
                    }
                }
            }
        }
        
        // 본인 제외하고 다른 선수와의 차례 구해진 경우 순위 매기기 가능
        for(int i=1; i<=n; i++){
            int win = 0;
            int lose = 0;
            for(int j=1; j<=n; j++){
                if(order[i][j] == 1){
                    win++;
                }
                if(order[j][i] == 1){
                    lose++;
                }
            }
            if( win + lose == n-1){
                count++;
            }
        }
        return count;
    }
}