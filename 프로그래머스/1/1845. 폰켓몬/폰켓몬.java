import java.util.*;
class Solution {
    public int solution(int[] nums) {
        Set set = new HashSet<>();
        for(int num : nums){
            set.add(num);
        }
        
        if(nums.length/2 > set.size()){
            return set.size();
        } else {
            return nums.length/2;
        }
    }
}