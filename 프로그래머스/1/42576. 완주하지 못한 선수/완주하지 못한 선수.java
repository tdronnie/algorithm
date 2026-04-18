import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> list = new HashMap<>(); // 이름 중복 가능하므로 map으로 이름 카운트
        for(String name : participant){
            list.put(name, list.getOrDefault(name, 0) + 1);
        }
        
        for(String name : completion){
            list.put(name, list.get(name)-1);
        }
        
        for(String name : list.keySet()){
            if(list.get(name) > 0) return name;
        }
        return null;
    }
}