import java.util.*;
class Solution {
    public int solution(int[] topping) {
        HashMap<Integer, Integer> m1 = new HashMap<>();
        HashMap<Integer, Integer> m2 = new HashMap<>();
        int count = 0;
        
        for(int i=0; i<topping.length; i++){
            m1.put(topping[i], m1.getOrDefault(topping[i], 0) + 1);
        }
        
        for(int t : topping){
            m1.put(t, m1.get(t) - 1);
            if(m1.get(t) == 0){
                m1.remove(t);
            }
            m2.put(t, m2.getOrDefault(t, 0) + 1);
            
            if(m1.size() == m2.size()) count++;
        }
        
        return count;
    }
}