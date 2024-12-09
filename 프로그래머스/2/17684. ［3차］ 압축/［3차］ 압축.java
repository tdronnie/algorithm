import java.util.*;
class Solution {
    public int[] solution(String msg) {
        ArrayList<Integer> list = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for(int i=0; i<26; i++){
            map.put(String.valueOf((char)('A' + i)), i + 1);
        }
        
        StringBuilder sb = new StringBuilder();
        int idx = map.size();
        
        for(int i=0; i<msg.length(); i++){
            sb.append(msg.charAt(i));
            
            if(i == msg.length()-1 || !map.containsKey(sb.toString() + msg.charAt(i+1))){
                if(map.containsKey(sb.toString())) {
                    // System.out.println("이미 있음 : " + sb.toString());
                    list.add(map.get(sb.toString()));
                }
                if(i < msg.length()-1){
                    System.out.println("저장 : " + sb.toString() + msg.charAt(i+1));
                    map.put(sb.toString() + msg.charAt(i+1), ++idx);
                }
                sb.setLength(0);              
            }
        }
        
        int[] result = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            result[i] = list.get(i);
        }
        return result;
        
    }
}