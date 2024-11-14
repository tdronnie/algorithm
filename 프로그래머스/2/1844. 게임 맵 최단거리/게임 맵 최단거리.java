import java.util.*;
class Solution {
    
    static int row, col;
    public int solution(int[][] maps) {
        row = maps.length;
        col = maps[0].length;
        
        return findShortRoute(maps);
        
    }
    
    static int[] moveX = {-1, 0, 1, 0};
    static int[] moveY = {0, 1, 0, -1};

    public static int findShortRoute(int[][] maps){
        boolean[][] visited = new boolean[row][col];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0, 1});
        visited[0][0] = true;
        
        while(!q.isEmpty()){
            int[] loc = q.poll();
            
            if(loc[0] == row-1 && loc[1] == col-1){
                return loc[2];
            }
            
            for(int i=0; i<4; i++){
                int newX = loc[0] + moveX[i];
                int newY = loc[1] + moveY[i];
                int step = loc[2];
                
                if(newX < 0 || newY < 0 || newX >= row || newY >= col || maps[newX][newY] == 0 || visited[newX][newY]) continue;
                
                visited[newX][newY] = true;
                q.add(new int[]{newX, newY, step+1});
            }
            
        }
        return -1;
    }
}