import java.util.*;
class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        // 바위간 최소 간격 중 최댓값
        int left = 1;
        int right = 1000000000;
        
        while(left <= right){
            int mid = (left+right) / 2;
            if(isPossible(mid, rocks, n, distance)){
                left = mid + 1;
            } else {
                right = mid -1;
            }
        }
        
        return right; //가능한 최대 간격
    }
    
    boolean isPossible(int mid, int[] rocks, int n, int distance){
        int pre = 0;
        int removed = 0;
        
        for(int pos : rocks){
            if(pos - pre < mid){ // 돌 사이 간격 mid보다 작으면
                removed++;
            } else {
                pre = pos;
            }
            
            if(removed > n) return false;
        }
        
        //마지막 바위와의 간격 확인
        if(distance - pre < mid) removed++;
        
        return removed <= n;
    }
}