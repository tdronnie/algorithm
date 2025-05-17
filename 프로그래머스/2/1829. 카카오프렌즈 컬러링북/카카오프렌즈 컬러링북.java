import java.util.*;
class Solution {
    static boolean[][] visited;
    static int[] moveX = {-1, 0, 1, 0};
    static int[] moveY = {0, 1, 0, -1};
    
    public int[] solution(int m, int n, int[][] picture) {
        //picture 돌면서 0이 아니면서 아직 탐색하지 않았던 숫자라면 탐색 시작
        //areaCount, maxArea를 두어 서로 다른 숫자의 모든 넓이를 구하면서 영역 개수 증가 및 영역 최댓값 갱신
        
        visited = new boolean[m][n];
        int areaCount = 0;
        int maxExtent = Integer.MIN_VALUE;
        
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++)
                if(picture[i][j] != 0 && !visited[i][j]){
                    areaCount++;
                    int extent = getAreaExtent(i, j, picture, m, n);
                    maxExtent = Math.max(maxExtent, extent);
                } 
        }
        
        return new int[]{areaCount, maxExtent};
        
    }
    
    int getAreaExtent(int x, int y, int[][] picture, int row, int col){
        Queue<int[]> q = new ArrayDeque<>();
        int area = 1;
        q.add(new int[]{x, y});
        visited[x][y] = true;
        
        while(!q.isEmpty()){
            int[] position = q.poll();
            int currentX = position[0];
            int currentY = position[1];
            
            for(int k=0; k<4; k++){
                int nx = currentX + moveX[k];
                int ny = currentY + moveY[k];
                
                if(nx < 0 || ny < 0 || nx >= row || ny >= col || visited[nx][ny]) continue;
                if(picture[nx][ny] != picture[currentX][currentY]) continue;
                
                area++;
                q.add(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }
        
        return area;
    }
}