import java.util.*;
class Solution {
    
    public int solution(int[][] points, int[][] routes) {
        
        int count = 0;
        // routes에 대해 각 점에서 다음 점까지 가는 경로 저장
        List<int[]>[] transport = new ArrayList[routes.length];
        
        for(int i=0; i<routes.length; i++){
            transport[i] = new ArrayList<>();            
        }
        
        for(int i=0; i<routes.length; i++){
            
            for(int j=0; j < routes[i].length - 1; j++) {
                int startIdx = routes[i][j] - 1;
                int endIdx = routes[i][j+1] - 1;
                
                int srcX = points[startIdx][0];
                int srcY = points[startIdx][1];
                int desX = points[endIdx][0];
                int desY = points[endIdx][1];
                
                // 시작점 먼저 넣기
                if(j == 0){
                    transport[i].add(new int[]{srcX, srcY});
                }
                
                while(srcX != desX){
                    if(desX > srcX){
                        srcX++;
                    } else {
                        srcX--;
                    }
                    transport[i].add(new int[]{srcX, srcY});
                }

                while(srcY != desY){
                    if(desY > srcY){
                        srcY++;
                    } else {
                        srcY--;
                    }
                    transport[i].add(new int[]{srcX, srcY});
                }
            }
        }
        
        // 충돌 탐색
        int maxMove = 0;
        for(List<int[]> path : transport){
            maxMove = Math.max(maxMove, path.size());
        }
        for(int i=0; i<maxMove; i++){
            Map<String, Integer> detect = new HashMap<>();
            
            for(int j=0; j<transport.length; j++){
                
                // 최종목적지 도착 이전인 로봇들만 고려
                int[] loc = new int[2];
                if(i < transport[j].size()){
                    loc = transport[j].get(i);
                    String key = loc[0] + "," + loc[1];
                    detect.put(key, detect.getOrDefault(key, 0) + 1);
                }
            }
            
            // 2개 이상 모이는 좌표 수만큼 카운트
            for(int l : detect.values()){
                if(l >= 2){
                    count++;
                }
            }
        }
        return count;
    }
}