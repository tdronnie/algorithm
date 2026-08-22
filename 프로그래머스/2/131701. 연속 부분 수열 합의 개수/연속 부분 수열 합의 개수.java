import java.util.*;
class Solution {
    public int solution(int[] elements) {
        
        Set<Integer> sums = new HashSet<>();
        
        for(int i=1; i<=elements.length; i++){
            for(int start = 0; start < elements.length; start++){
                int sum = 0;
                for(int idx = start; idx < start + i; idx++){
                    int curr = idx % elements.length;
                    sum += elements[curr];
                }
                sums.add(sum);
            }
        }
        
        return sums.size();
    }
}