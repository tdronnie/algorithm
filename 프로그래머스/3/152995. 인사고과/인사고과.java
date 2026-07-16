import java.util.*;
class Solution {
    public int solution(int[][] scores) {
        // 인덱스 0 내림차순, 인덱스 1 오름차순
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] != b[0] ? b[0] - a[0] : a[1] - b[1]);
        
        for(int i=0; i<scores.length; i++){
            pq.add(new int[]{scores[i][0], scores[i][1]});
        }
        
        int wan0 = scores[0][0];
        int wan1 = scores[0][1];
        int wansSum = scores[0][0] + scores[0][1];
        int max1 = 0;
        int order = 1;
        
        while(!pq.isEmpty()){
            int[] value = pq.poll();
            
            // 지금까지의 최솟값보다 작으면
            if(max1 > value[1]){
                if(wan0 == value[0] && wan1 == value[1]) return -1;
                continue;  
            }
            
            max1 = Math.max(max1, value[1]);
            
            if(value[0] + value[1] > wansSum){
                order++;
            }
        }
        
        return order;
        
    }
}