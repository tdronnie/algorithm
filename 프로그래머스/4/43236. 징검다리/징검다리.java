import java.util.*;
class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        // 바위 간 거리 이분탐색
        int min = 1;
        int max = 1000000000;
        
        while(min <= max){
            int mid = (min + max) / 2;
            int removed = 0;
            int pre = 0;
            for(int r : rocks){
                if(r - pre < mid){ // 바위 간 거리가 mid보다 작은 경우 바위없애기
                    removed++;
                } else { // 바위 간 거리가 mid보다 크거나 같은 경우 다음 바위로 넘어가기
                    pre = r;
                }
                
                // n보다 많이 지워진 경우 더 좁혀야 덜 지우기 가능
                if(removed > n){
                    break;
                }
            }
            
            if(distance - pre < mid) removed++; // 마지막 바위와 도착지점 비교
            
            if(removed > n){
                max = mid - 1;    
            } else {
                min = mid + 1;
            }
        }
        
        return max;
        
    }
}