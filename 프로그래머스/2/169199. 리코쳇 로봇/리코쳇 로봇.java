import java.util.*;
class Solution {
    public int solution(String[] board) {
        
        int r = board.length;
        int c = board[0].length();
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int startX = -1;
        int startY = -1;
        int endX = -1;
        int endY = -1;
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited= new boolean[r][c];
        
        // 시작과 끝 탐색
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(board[i].charAt(j) == 'G'){
                    endX = i;
                    endY = j;
                } else if(board[i].charAt(j) == 'R'){
                    startX = i;
                    startY = j;
                }
            }
        }
        
        q.add(new int[]{startX, startY, 0});
        visited[startX][startY] = true;
        
        while(!q.isEmpty()){
            int[] curr = q.poll();
            if(curr[0] == endX && curr[1] == endY){
                return curr[2];
            }
            
            for(int k=0; k<4; k++){
                
                int nx = curr[0];
                int ny = curr[1];
                
                // 한 턴에 한 방향으로 끝까지
                nx += dx[k];
                ny += dy[k];
                
                while(nx >= 0 && ny >= 0 && nx < r && ny < c && board[nx].charAt(ny) != 'D'){
                    nx += dx[k];
                    ny += dy[k];
                    
                }
                nx -= dx[k];
                ny -= dy[k];
                
                if(!visited[nx][ny]){
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny, curr[2] + 1});
                }
            }
        }
        
        return -1;
        
    }
}