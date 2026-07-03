import java.util.*;
class Solution {
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    public int[] solution(String[] maps) {
        
        int[][] map = new int[maps.length][maps[0].length()];
        Queue<int[]> q = new ArrayDeque<>();
        List<Integer> a = new ArrayList<>();
        
        for(int i=0; i<maps.length; i++){
            for(int j=0; j<maps[0].length(); j++){
                if(maps[i].charAt(j)>='1' && maps[i].charAt(j)<='9'){
                    map[i][j] = maps[i].charAt(j) - '0';
                } else {
                    map[i][j] = 0;
                }
            }
        }
        
        for(int i=0; i<map.length; i++){
            for(int j=0; j<map[0].length; j++){
                if(map[i][j] >= 1){
                    q.add(new int[]{i, j});
                    int food = map[i][j];
                    map[i][j] = 0;
                    while(!q.isEmpty()){
                        int[] curr = q.poll();
                        
                        for(int k=0; k<4; k++){
                            int nx = curr[0] + dx[k];
                            int ny = curr[1] + dy[k];
                            
                            if(nx < 0 || ny < 0 || nx >= map.length || ny >= map[0].length || map[nx][ny] == 0) continue;
                            food += map[nx][ny];
                            map[nx][ny] = 0;
                            q.add(new int[]{nx, ny});
                        }
                    }
                    
                    a.add(food);
                }
            }
        }
        
        if(a.size() == 0) return new int[]{-1};
        int[] answer = a.stream()
            .mapToInt(Integer::intValue)
            .toArray();
        Arrays.sort(answer);
        
        return answer;
        
    }
}