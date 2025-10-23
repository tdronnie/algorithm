class Solution {
    int solution(int[][] land) {
        int row = land.length;
        int[][] max = new int[row][4];
        int maxScore = 0;
        
        for(int i=0; i<4; i++){
            max[0][i] = land[0][i];
            // gameStart(0, i, land, max);    
        }
        
        for(int r = 1; r < row; r++){
            
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    if(i == j) continue;
                    max[r][i] = Math.max(max[r][i], max[r-1][j] + land[r][i]);
                }
            }
        }
        
        for(int i=0; i<4; i++){
            maxScore = Math.max(maxScore, max[row-1][i]);
        }
        
        return maxScore;
        
    }
    
    //DFS + 메모이제이션도 시간초과..
//     void gameStart(int row, int col, int[][] land, int[][] max){
//         if(row >= land.length -1){
//             return;
//         }
        
//         for(int i=0; i<4; i++){
//             if(i == col) continue;
//             if(max[row+1][i] < max[row][col] + land[row+1][i]){
//                 max[row+1][i] = max[row][col] + land[row+1][i];
//                 gameStart(row+1, i, land, max);
//             }
//         }
//     }
}