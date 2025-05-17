class Solution {
    //0~3인덱스까지는 실제 도형 자리, 4와 5인덱스는 검은 블록이 채워지면 직사각형이 되는 자리
    static int[][] moveX = {{0, 1, 1, 1, 0, 0}, {0, 1, 2, 2, 0, 1}, {0, 1, 2, 2, 0, 1}, {0, 1, 1, 1, 0, 0}, {0, 1, 1, 1, 0, 0}};
    static int[][] moveY = {{0, 0, 1, 2, 1, 2}, {0, 0, 0, -1, -1, -1}, {0, 0, 0, 1, 1, 1}, {0, 0, -1, -2, -2, -1}, {0, -1, 0, 1, -1, 1}};
    
    public int solution(int[][] board) {
        //지울 수 있는 모양 체크
        //블록 떨어 뜨려서 없앨 수 있는 모양인지 체크
        //없애기
        int len = board.length;
        int count = 0;
        
        for(int i=0; i<len; i++){
            for(int j=0; j<len; j++){
                if(board[i][j] == 0) continue;
                int idx = canMake(i, j, board, len);
                if(idx != -1){
                    remove(i, j, idx, board);
                    count++;
                    j = -1; //해당 행 다시 탐색
                    
                }
            }
        }
        return count;
        
    }
    
    void remove (int x, int y, int idx, int[][] board){
        for(int i=0; i<4; i++){
            int nx = x + moveX[idx][i];
            int ny = y + moveY[idx][i];
            board[nx][ny] = 0;
        }
    }
    
    int canMake(int x, int y, int[][] board, int len){

        int value = board[x][y];
        
        for(int i=0; i<5; i++){
            boolean isOk = true;
            for(int j=0; j<4; j++){
                int nx = x + moveX[i][j];
                int ny = y + moveY[i][j];
                
                if(nx < 0 || ny <0 || nx >= len || ny >= len || board[nx][ny] != value){
                    isOk = false;
                    break;
                }
            }
            //현재 도형이 가능하다면
            if(isOk){
                boolean canMakeRectangle = true;
                //블록 떨어뜨리기
                for(int j=4; j<6; j++){
                    int nx = x + moveX[i][j];
                    int ny = y + moveY[i][j];
                    
                    if(nx < 0 || ny < 0 || nx >= len || ny >= len || board[nx][ny] != 0){
                        canMakeRectangle = false;
                        break;
                    }
                    //위치 상 문제없다면 해당 칸에 검은 블록이 떨어질 수 있나 확인
                    for(int k = nx; k>=0 ; k--){
                        if(board[k][ny] != 0){
                            canMakeRectangle = false;
                            break;
                        }
                    }
                    //검은 블록이 못떨어짐
                    if(!canMakeRectangle) break;
                }
                //직사각형 만들기 가능하고 만들기 위해서 검은블록도 모두 잘 떨어질 수 있는 경우
                if(canMakeRectangle) return i; //현재 도형의 인덱스 리턴
            }
        }
        return -1; //지울 수 있는 도형 없음
    }
}