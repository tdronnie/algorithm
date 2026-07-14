import java.util.*;
class Solution {
    public long solution(int[] weights) {

        long count = 0;
        Map<Integer, Long> ws = new HashMap<>();
        
        for(int weight : weights){
            ws.put(weight, ws.getOrDefault(weight, 0L) + 1);
        }
        
        for(int w : ws.keySet()){
            
            long c = ws.get(w);
            
            // 같은 몸무게
            if (c > 1) {
                count += (c * (c - 1)) / 2;
            }
            if(w*2 % 3 == 0 && ws.containsKey(w*2/3)){
                count += ws.get(w) * ws.get(w*2/3);
            }
            if(w*3 % 4 == 0 && ws.containsKey(w*3/4)){
                count += ws.get(w) * ws.get(w*3/4);
            }
            if(w*2 % 4 == 0 && ws.containsKey(w*2/4)){
                count += ws.get(w) * ws.get(w*2/4);
            }
        }
        
        return count;
        
    }
}