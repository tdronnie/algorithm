import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        
        // 최대 걸리는 시간 -> 가장 오래 걸리는 심사관 * n
        Arrays.sort(times);
        long min = 0;
        long max = (long)times[times.length-1] * n;
        
        while(min <= max){
            long mid = (min + max)/2;
            //현재시간의 모든 심사관이 심사하는 사람 수의 합이 n이 되도록
            long person = 0;
            for(int t : times){
                person += mid / t;
                if(person >= n){ // 현재 시간 내 기다리는 사람 이상으로 처리가능하다면 시간 down
                    max = mid - 1;
                    break;
                }
            }
            if(person < n){ // 현재 시간 내 기다리는 사람 이상으로 처리 불가능하다면 시간 up
                min = mid + 1;
            }
        }
        
        return min;
    }
}