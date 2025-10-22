import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int count = 0;
        int up = 0;
        for(int i=0; i<scoville.length; i++){
            if(scoville[i] >= K){
                up++;
            }
            pq.add(scoville[i]);
        }
        
        if(up == scoville.length){
            return 0;
        }
        
        while(!pq.isEmpty()){
            
            int l1 = pq.poll();
            if(l1 >= K){
                break;
            }
            if(pq.isEmpty()){
                return -1;
            }
            
            int l2 = pq.poll();
            int mix = l1 + (l2*2);
            pq.add(mix);
            count++;
        }
        
        return count;
    }
}