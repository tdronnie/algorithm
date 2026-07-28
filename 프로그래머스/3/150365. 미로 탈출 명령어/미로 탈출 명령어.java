import java.util.*;
class Solution {
    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, -1, 1, 0};
    char[] dChar = {'d', 'l', 'r', 'u'};
    String answer = "impossible";
    boolean done;
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        
        // 애초에 이동거리가 끝점까지의 거리보다 짧거나
        // 최단거리(dis)에 대해서 남는 여유 거리 (k-dis)에 대해 어딘가를 찍고 같은거리를 와야함, 따라서 여유거리가 정확히 2등분되지 않으면 불가
        int dis = Math.abs(x - r) + Math.abs(y - c);
        if(k < dis || (k - dis) % 2 != 0) return "impossible"; 
        dfs(n, m, x, y, r, c, 0, k, new StringBuilder());
        return answer;
        
    }
    
    public void dfs(int n, int m, int cx, int cy, int ex, int ey, int count, int k, StringBuilder move){
        if(done) return;
        
        if(count == k){
            if(cx == ex && cy == ey){
                answer = move.toString();
                done = true;
            }
            return;
        }
        
        int dis = Math.abs(cx - ex) + Math.abs(cy - ey);
        if(k-count < dis || ((k-count) - dis) % 2 != 0) return;
        
        for(int d=0; d < 4; d++){
            int nx = cx + dx[d];
            int ny = cy + dy[d];
            
            if(nx <= 0 || ny <= 0 || nx > n || ny > m) continue;
            
            move.append(dChar[d]);
            dfs(n, m, nx, ny, ex, ey, count+1, k, move);
            // 백트래킹
            move.deleteCharAt(move.length() - 1);
            if(done) return;
            
        }
    }
}