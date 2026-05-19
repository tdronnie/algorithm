import java.util.*;
class Solution {
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    int minCount = 10000;
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        
        // 이동 가능 경로 구하기, 모든 사각형의 테두리 1, 내부 2처리
        int[][] map = new int[101][101]; // 모든 좌표값 1이상 50이하
        
        // 테두리 그리기
        for(int[] rect : rectangle){
            for(int i=rect[0]*2; i<= rect[2]*2; i++){
                for(int j=rect[1]*2; j<= rect[3]*2; j++){
                    map[i][j] = 1;
                }
            }
        }
        
        
        // 내부 채우기
        for(int[] rect : rectangle){
            for(int i=rect[0]*2+1; i<rect[2]*2; i++){
                for(int j=rect[1]*2+1; j<rect[3]*2; j++){
                    map[i][j] = 2;
                }
            }
        }
        
        // for(int i=0; i<11; i++){
        //     for(int j=0; j<11; j++){
        //         System.out.print(map[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        
        // 아이템 찾을 때까지 탐색
        bfs(characterX*2, characterY*2, itemX*2, itemY*2, map);
        return minCount/2; // 2배 이동 처리
        
    }
    
    public void bfs(int x, int y, int itemX, int itemY, int[][] map){
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[101][101];
        q.add(new int[]{x, y, 0});
        visited[x][y] = true;
        
        while(!q.isEmpty()){
            int[] current = q.poll();
            
            if(current[0] == itemX && current[1] == itemY){
                minCount = Math.min(minCount, current[2]);
                return;
            }
            
            for(int k=0; k<4; k++){
                int nx = current[0] + dx[k];
                int ny = current[1] + dy[k];
            
                if(nx < 0 || ny < 0 || nx >= 101 || ny >= 101 || visited[nx][ny] || map[nx][ny] != 1) continue;
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny, current[2] + 1});

                }
        }
        
    }
    
    public void dfs(int x, int y, int itemX, int itemY, int[][] map, boolean[][] visited, int count){
        
        if(count >= minCount){
            return;
        }
        if(x == itemX && y == itemY){
            minCount = Math.min(count, minCount);
            return;
        }
        
        for(int k=0; k<4; k++){
            int nx = x + dx[k];
            int ny = y + dy[k];
            
            if(nx < 0 || ny < 0 || nx >= 101 || ny >= 101 || visited[nx][ny] || map[nx][ny] != 1) continue;
            visited[nx][ny] = true;
            map[nx][ny] = 9;
            dfs(nx, ny, itemX, itemY, map, visited, count+1);
            visited[nx][ny] = false; // 백트레킹, 다른 경로 다시 탐색
        }
    }
}