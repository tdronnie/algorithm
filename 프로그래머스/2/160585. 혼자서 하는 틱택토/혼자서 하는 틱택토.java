class Solution {
    public int solution(String[] board) {
        // 이겼으면 끝나야함, O개수 = X개수, O개수 = X개수 + 1
        
        int OCount = 0;
        int XCount = 0;
        boolean OWin = false;
        boolean XWin = false;
        
        // 행 열 판별
        for(int i=0; i<3; i++){
            if(board[i].charAt(0) != '.' && board[i].charAt(0) == board[i].charAt(1) && board[i].charAt(1) == board[i].charAt(2)){
                if(board[i].charAt(0) == 'O') OWin = true;
                else XWin = true;
            }
            if(board[0].charAt(i) != '.' && board[0].charAt(i) == board[1].charAt(i) && board[1].charAt(i) == board[2].charAt(i)){
                if(board[0].charAt(i) == 'O') OWin = true;
                else XWin = true;
            }
        }
        
        // 대각선 판별
        if(board[0].charAt(0) != '.' && board[0].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(2)) {
            if(board[0].charAt(0) == 'O') OWin = true;
            else XWin = true;
        }
        if(board[2].charAt(0) != '.' && board[2].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[0].charAt(2)) {
            if(board[2].charAt(0) == 'O') OWin = true;
            else XWin = true;
        }
        
        for(String c : board){
            for(int i=0; i<3; i++){
                if(c.charAt(i) == 'O'){
                    OCount++;
                } else if (c.charAt(i) == 'X') {
                    XCount++;
                }
            }
        }
        
        if(OCount != XCount && OCount != XCount + 1) return 0; // 게임 표시 확인
        if(OWin && OCount != XCount + 1) return 0; // O가 이겼을 때 개수 확인
        if(XWin && OCount != XCount) return 0; // X가 이겼을 때 개수 확인
        if(OWin && XWin) return 0; // 동시 이길 수 없음
        
        return 1;
        
    }
}