import java.util.*;
class Solution {
    public int solution(int[] citations) {        
        if(citations.length == 1) return 0;
        
        Arrays.sort(citations);
        
        int left = 0;
        int right = citations.length;
        
        while(left <= right) {
            int mid = (left + right) / 2;
            int count = 0;
            
            for(int citation : citations){
                if(citation >= mid) count++;
            }
            if(count >= mid){
                left = mid + 1;
            } else {
                right = mid-1;
            }
        }
        return right;
    }
}