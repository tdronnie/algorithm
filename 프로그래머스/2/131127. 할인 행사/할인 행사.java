import java.util.*;
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        
        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> m1 = new HashMap<>();
        int start = 0;
        int end = want.length-1;
        int count = 0;
        
        for(int i=0; i<want.length; i++){
            map.put(want[i], number[i]);
            m1.put(want[i], number[i]);
        }
        
        while(start + 10 <= discount.length){
            
            for(int i=0; i<10; i++){
                
                String stuff = discount[i+start];
                if(m1.containsKey(stuff) && m1.get(stuff) > 0){
                    m1.put(stuff, m1.get(stuff) - 1);
                    if(m1.get(stuff) == 0) m1.remove(stuff);
                } else { // 불가능, 다음 인덱스로
                    break;
                }
            }
            if(m1.size() == 0){
                count++;
            }
            // 하루이동, 카운트하는 맵 초기화
            m1 = new HashMap<>();
            for(Map.Entry<String, Integer> set : map.entrySet()){
                m1.put(set.getKey(), set.getValue());
            }
            start++;
            end++;
        }
        
        return count;
    }
}