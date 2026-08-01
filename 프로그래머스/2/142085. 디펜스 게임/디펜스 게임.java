import java.util.*;
class Solution {
    public int solution(int n, int k, int[] enemy) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int answer = -1;
        int i=0;
        for(;i < enemy.length; i++){
            
            pq.add(enemy[i]);
            
            if(n < enemy[i]){
                
                if(pq.isEmpty() || k <= 0){
                    return i;
                }
                n += pq.poll();
                k--;
            }
            n -= enemy[i];
        }
        
        if(i == enemy.length){
            return enemy.length;
        }
        
        return i + 1;
    }
}