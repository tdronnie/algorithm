import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        HashMap<String, Integer> map = new HashMap<>();
        int kinds = 1;
        
        for(int i=0; i<clothes.length; i++){
            String kind = clothes[i][1];
            map.put(kind, map.getOrDefault(kind, 0) + 1);
        }
        
        if(map.size() == 1) return clothes.length;
        
        for(int count : map.values()){
            kinds *= (count + 1);
        }
        return kinds - 1;
    }
}