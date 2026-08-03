import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        
        Map<Integer, Integer> map = new TreeMap<>();
        int kinds = 0;
        int idx = 0;
        
        for(int t : tangerine){
            map.put(t, map.getOrDefault(t, 0) + 1);
        }
        
        int[] counts = new int[map.size()];
        
        for(int t : map.values()){
            counts[idx++] = t;
        }
        
        Arrays.sort(counts);
        
        for(int i=counts.length - 1; i>=0; i--){
            k -= counts[i];
            kinds++;
            if(k <= 0) break;
        }
        
        return kinds;
    }
}