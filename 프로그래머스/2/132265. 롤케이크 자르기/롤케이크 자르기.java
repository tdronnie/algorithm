import java.util.*;
class Solution {
    public int solution(int[] topping) {
        
        Map<Integer, Integer> b1 = new HashMap<>();
        Map<Integer, Integer> b2 = new HashMap<>();
        int count = 0;
        
        //첫번째 각 가진 종류 카운트
        b1.put(topping[0], 1);
        for(int i=1; i<topping.length; i++){
            b2.put(topping[i], b2.getOrDefault(topping[i], 0) + 1);
        }
        
        //공평하면 카운트
        if(b1.size() == b2.size()){
            count++;
        }
        
        // 가장 앞에부터 잘라가기
        for(int i=1; i<topping.length-1; i++){
            
            b1.put(topping[i], b1.getOrDefault(topping[i], 0));
            if(b2.containsKey(topping[i])){
                if(b2.get(topping[i]) - 1 == 0){
                    b2.remove(topping[i]);
                } else {
                    b2.put(topping[i], b2.get(topping[i]) - 1);    
                }
            }
            
            //공평하면 카운트
            if(b1.size() == b2.size()){
                count++;
            }
        }
        return count;
    }
}