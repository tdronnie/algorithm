import java.util.*;
class Solution {
    public int solution(String str1, String str2) {
        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();
        
        str1 = str1.toLowerCase().replaceAll("[^a-zA-Z]", " ");
        str2 = str2.toLowerCase().replaceAll("[^a-zA-Z]", " ");
    
        
        for(int i=0; i<str1.length()-1; i++){
            String part = str1.substring(i,i+2);
            if(part.contains(" ")) continue;
            map1.put(part, map1.getOrDefault(part, 0) + 1);
        }
        
       
        for(int i=0; i<str2.length()-1; i++){
            String part = str2.substring(i,i+2);
            if(part.contains(" ")) continue;
            map2.put(part, map2.getOrDefault(part, 0) + 1);
        }     
        
        double inter = 0;
        double union = 0;
        for(String str : map1.keySet()){
            if(map2.get(str) != null){
                union += Math.max(map1.get(str), map2.get(str));
                inter += Math.min(map1.get(str), map2.get(str));
            } else {
                union += map1.get(str);
            }
        }
        for(String str : map2.keySet()){
            if(map1.get(str) == null){
                union += map2.get(str);
            }
        }
        
        if(inter > 0 || union > 0){
            return (int)((inter/union) * 65536);    
        }
        return 65536;
    }
}