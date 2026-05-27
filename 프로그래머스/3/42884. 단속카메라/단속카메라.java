import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        
        // 나간지점 오름차순 정렬, 나 다음 순서들 중 진입지점이 내 나간지점보다 앞선 경우 같은 구간 포함 가능
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int count = 0;
        
        for(int[] route : routes){
            pq.add(route);
        }
        
        while(!pq.isEmpty()){
            int[] r = pq.poll();
            count++;
            
            while(!pq.isEmpty() && pq.peek()[0] <= r[1]){
                pq.poll();
            }
        }
        
        return count;
        
    }
}