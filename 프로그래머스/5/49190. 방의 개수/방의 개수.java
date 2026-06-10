import java.util.*;
class Solution {
    int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    public int solution(int[] arrows) {
        
        // int[][] board = new int[arrows.length * 2 + 1][arrows.length * 2 + 1];
        // boolean[][] visited = new boolean[arrows.length * 2 + 1][arrows.length * 2 + 1];
        Set<String> visitedN = new HashSet<>();
        Set<String> visitedE = new HashSet<>();
        int count = 0;
        int x = 0;
        int y = 0;
        visitedN.add(x + "," + y);
        
        for(int dir : arrows){
            // 대각선 교차 고려해서 사이 점 하나 더두기, 2번 이동
            for(int i=0; i<2; i++){
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                
                String curr = x + "," + y;
                String next = nx + "," + ny;
                String forward = curr + "->" + next;
                String backward = next + "->" + curr;

                // 같은 점이이면서 다른 방향에서 들어오면 사이클 생성
                if(visitedN.contains(next) && !visitedE.contains(forward)) {
                    count++;
                } 
                visitedN.add(next);
                visitedE.add(forward);
                visitedE.add(backward);
                x = nx;
                y = ny;
            }
        }
        
        return count;
    }
}