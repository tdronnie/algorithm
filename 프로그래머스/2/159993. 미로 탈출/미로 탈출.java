import java.util.*;
class Solution {
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    
    public int solution(String[] maps) {
        char[][] map = new char[maps.length][maps[0].length()];
        Queue<int[]> q = new ArrayDeque<>();
        int sX = -1;
        int sY = -1;
        int lX = -1;
        int lY = -1;
        int eX = -1;
        int eY = -1;
        
        for(int i=0; i<maps.length; i++){
            for(int j=0; j<maps[0].length(); j++){
                map[i][j] = maps[i].charAt(j);
                if(map[i][j] == 'S'){
                    sX = i;
                    sY = j;
                } else if (map[i][j] == 'L'){
                    lX = i;
                    lY = j;
                    
                } else if (map[i][j] == 'E'){
                    eX = i;
                    eY = j;
                }
            }
        }
        int time = 0;
        int t1 = bfs(sX, sY, lX, lY, map);
        if(t1 == -1) return -1;
        time += t1;
        int t2 = bfs(lX, lY, eX, eY, map);
        if(t2 == -1) return -1;
        time += t2;
        
        return time;
        
    }
    
    public int bfs(int x, int y, int toX, int toY, char[][] maps){
        
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited= new boolean[maps.length][maps[0].length];
        q.add(new int[]{x, y, 0});
        visited[x][y] = true;
            
        while(!q.isEmpty()){
            int[] curr = q.poll();
            
            if(curr[0] == toX && curr[1] == toY){
                return curr[2];
            }
            
            for(int k=0; k<4; k++){
                int nx = curr[0] + dx[k];
                int ny = curr[1] + dy[k];
                
                if(nx < 0 || ny < 0 || nx >= maps.length || ny >= maps[0].length || maps[nx][ny] == 'X' || visited[nx][ny]) continue;
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny, curr[2] + 1});
                
            }
            
        }
        
        return -1;
    }
}