import java.util.*;
class Solution {
    
    public int solution(int[] queue1, int[] queue2) {
        
        long sum = 0;
        long sum1 = 0;
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        
        for(int value : queue1){
            sum += value;
            sum1 += value;
            q1.add(value);
        }
        
        for(int value : queue2){
            sum += value;
            q2.add(value);
        }
        
        if(sum % 2 != 0){
            return -1;
        }
        
        // 한쪽만 생각
        int max_move = queue1.length*3;
        int move = 0;
        
        while(move <= max_move){
            if(sum1 == sum/2){
                return move;
            }
            
            if(sum1 < sum/2){
                int value = q2.poll();
                q1.add(value);
                sum1 += value;
            } else {
                int value = q1.poll();
                q2.add(value);
                sum1 -= value;
            }
            
            move++;
        }
        
        return -1;
        
    }
}