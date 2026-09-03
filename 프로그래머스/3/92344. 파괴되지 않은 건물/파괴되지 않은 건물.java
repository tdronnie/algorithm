class Solution {
    public int solution(int[][] board, int[][] skill) {
        
        int[][] cal = new int[board.length + 1][board[0].length + 1];
        for(int[] s : skill){
            int op = 1;
            if(s[0] == 1){
                op = -1;
            }
            
            // board 한번만 순회하도록, 계산할 식들 적용한 배열 생성
            // 3 0 0 -3
            // 0 0 0 0
            // -3 0 0 3
            cal[s[1]][s[2]] += op*s[5];
            cal[s[1]][s[4] + 1] -= op*s[5];
            cal[s[3] + 1][s[2]] -= op*s[5];
            cal[s[3] + 1][s[4] + 1] += op*s[5];
            
        }
        
        // 좌 -> 우
        for(int r=0; r<board.length; r++){
            for(int c=1; c < board[0].length; c++){
                cal[r][c] += cal[r][c-1];
            }
        }
        
        // 상 -> 하
        for(int c = 0; c < board[0].length; c++){
            for(int r = 1; r < board.length; r++){
                cal[r][c] += cal[r-1][c];
            }
        }
        
        int count = 0;
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                board[i][j] += cal[i][j];
                if(board[i][j] > 0){
                    count++;
                }
            }
        }
        
        return count;
    }
}