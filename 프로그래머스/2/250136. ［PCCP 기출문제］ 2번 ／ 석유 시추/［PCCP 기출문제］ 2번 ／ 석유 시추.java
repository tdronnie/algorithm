import java.util.*;
class Solution {
    
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    
    public int solution(int[][] land) {
        
        int r = land.length;
        int c = land[0].length;
        // Set<int[]> range = new HashSet<>(); // 덩어리 땅번호, 최소 열, 최대 열 번호
        // Map<Integer, int[]> area = new HashMap<>(); // 땅번호, 덩어리 면적, 최소열 최대열
        int[] landCol = new int[c];
        int maxAmount = -1;
        
        Queue<int[]> q = new ArrayDeque<>();
        int num = 2;
        
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(land[i][j] == 1){
                    // 석유 위치 찾기, 덩어리의 열 범위 저장
                    int count = 1;
                    int min = 501;
                    int max = 0;
                    
                    q.add(new int[]{i, j});
                    land[i][j] = num;

                    while(!q.isEmpty()){
                        int[] curr = q.poll();
                        
                        min = Math.min(min, curr[1]);
                        max = Math.max(max, curr[1]);

                        for(int k=0; k<4; k++){
                            int nx = curr[0] + dx[k];
                            int ny = curr[1] + dy[k];

                            if(nx < 0 || ny < 0 || nx >= r || ny >= c || land[nx][ny] != 1) continue;

                            land[nx][ny] = num;
                            count++;
                            q.add(new int[]{nx, ny});
                        }
                    }
                    for(int l=min; l<= max; l++){
                        landCol[l] += count;   
                    }
                }
            }
        }
        
        
        // 모든 열에 대해 얻을 수 있는 시추양 구하기     
        for(int i=0; i<c; i++){
            maxAmount = Math.max(maxAmount, landCol[i]);
        }
        
        return maxAmount;
        
    }
}