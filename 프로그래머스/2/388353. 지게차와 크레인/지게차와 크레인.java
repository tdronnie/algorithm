import java.util.*;
class Solution {
    public int solution(String[] storage, String[] requests) {
    
        int r = storage.length;
        int c = storage[0].length();
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int count = 0;
        
        char[][] map = new char[r+2][c+2];
        for (int i = 0; i < r + 2; i++) {
            Arrays.fill(map[i], '-'); 
        }
        
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                map[i+1][j+1] = storage[i].charAt(j);
            }
        }
        
        for(String req : requests){
            char find = req.charAt(0);
            List<int[]> remove = new ArrayList<>();
            
            if(req.length() == 1){
                
                boolean[][] visited = new boolean[r+2][c+2];
                
                // 외부 탐색
                Queue<int[]> q = new ArrayDeque<>();
                q.add(new int[]{0, 0});
                visited[0][0] = true;
                
                while(!q.isEmpty()){
                    int[] curr = q.poll();
                    
                    for(int k=0; k<4; k++){
                        int nx = curr[0] + dx[k];
                        int ny = curr[1] + dy[k];
                        
                        if(nx < 0 || ny < 0 || nx >= r+2 || ny >= c+2) continue;
                        if(!visited[nx][ny] && map[nx][ny] == '-') {
                            visited[nx][ny] = true;
                            q.add(new int[]{nx, ny});
                        }
                    }
                }
                
                for(int i=1; i<r+1; i++){
                    for(int j=1; j<c+1; j++){
                        if(map[i][j] == find){
                            for(int k=0; k<4; k++){
                                
                                int nx = i + dx[k];
                                int ny = j + dy[k];
                                
                                if(visited[nx][ny]){
                                    remove.add(new int[]{i, j});
                                    break;
                                }
                            }
                        }
                    }
                }
            } else {
                for(int i=1; i<r+1; i++){
                    for(int j=1; j<c+1; j++){
                        if(map[i][j] == find){
                            remove.add(new int[]{i, j});
                        }
                    }
                }
            }
            
            for(int[] loc : remove){
                map[loc[0]][loc[1]] = '-';
            }
            
        }
        
        for(int i=1; i<r+1; i++){
            for(int j=1; j<c+1; j++){
                if(map[i][j] != '-'){
                    count++;
                }
            }
        }
        
        return count;
    }
}