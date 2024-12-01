import java.util.*;
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        TreeMap<String, Integer> map = new TreeMap<>();
        TreeMap<String, Integer> curr = new TreeMap<>();
        int left = 0;
        int right = 9;
        int count = 0;
        
        for(int i=0; i<want.length; i++){
            map.put(want[i], number[i]);
        }
        
        for(int i=0; i<=9; i++){
            curr.put(discount[i], curr.getOrDefault(discount[i], 0) + 1);
        }
        
        while(true){
            if(check(map, curr)){
                count++;    
            }
            curr.put(discount[left], curr.getOrDefault(discount[left], 0) - 1);
            left++;
            right++;
            if(right == discount.length) break;
            curr.put(discount[right], curr.getOrDefault(discount[right], 0) + 1);
        }
        return count;
    }
    
    public boolean check(Map<String, Integer> map, Map<String, Integer> curr){
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            if(curr.get(entry.getKey()) == null || curr.get(entry.getKey()) < entry.getValue()) return false;
        }
        return true;
    }
}