import java.util.*;
class Solution {
    
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public int solution(int[][] maps) {
        
        // BFS로 상대 팀 진영까지의 최단거리 구하기
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[maps.length][maps[0].length];
        q.add(new int[]{0, 0, 1});
        visited[0][0] = true;
        
        while(!q.isEmpty()){
            int[] loc = q.poll();
            
            // 적 진영인 경우 지금까지 지나온 칸의 개수 리턴
            if(loc[0] == (maps.length -1) && loc[1] == (maps[0].length -1)){
                return loc[2];
            }
            
            for(int k=0; k<4; k++){
                int nx = loc[0] + dx[k];
                int ny = loc[1] + dy[k];
                
                if(nx < 0 || ny < 0 || nx >= maps.length || ny >= maps[0].length || visited[nx][ny] || maps[nx][ny] == 0) continue;
                visited[nx][ny] = true;
                
                q.add(new int[]{nx, ny, loc[2] + 1});
            }
        }
        
        // 상대 팀 진영에 도착할 수 없는 경우 -1
        return -1;

    }
}