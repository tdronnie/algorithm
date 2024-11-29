import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int num = 0;
        int left = 0;
        int right = people.length-1;
        
        while(left <= right){
            if(left == right){
                num++;
                break;
            }
            if(people[left] + people[right] <= limit){
                left++;
                right--;
                num++;
            } else{
                right--;
                num++;
            }
        }
        
        return num;
    }
}