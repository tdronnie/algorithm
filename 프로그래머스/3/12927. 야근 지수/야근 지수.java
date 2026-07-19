import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        // 작업들의 작업량 제곱의 합이 최소가 되도록 n만큼 빼기
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        long answer = 0;
        
        for(int work : works){
            pq.add(work);
        }
        
        while(n > 0 && !pq.isEmpty()){
            int work = pq.poll();
            if(work == 0) continue;
            pq.add(--work);
            n--;
        }
        
        while(!pq.isEmpty()){
            answer += Math.pow(pq.poll(), 2);
        }
        
        return answer;
    }
}