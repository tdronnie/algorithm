import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        if(k == 1) return 1;
        
        TreeMap<Integer, Integer> map = new TreeMap<>();
        
        for(int n : tangerine){
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>(){
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2){
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        
        int result = 0;
        for(Map.Entry<Integer, Integer> entry : list){
            // System.out.println("키: " + entry.getKey() + " 값: " + entry.getValue());
            k -= entry.getValue();
            result++;
            if(k <= 0) break;
        }
        return result;
    }
}