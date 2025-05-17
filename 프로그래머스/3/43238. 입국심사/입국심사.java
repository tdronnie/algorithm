import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        
        // n = 1;
        // times = new int[]{2, 3, 4};
        Arrays.sort(times);
        long lowTime = 1;
        long maxTime = times[times.length-1] * (long)n;
        
        while(lowTime <= maxTime){
            long midTime = (lowTime + maxTime) / 2;
            
            if(isPossible(midTime, times, n)){
                maxTime = midTime-1;
            } else {
                lowTime = midTime + 1;
            }
        }
        
        return lowTime;
    }
    
    boolean isPossible(long midTime, int[] times, int n){
        long totalPeople = 0;
        for(int time : times){
            totalPeople += midTime / time;
            if(totalPeople >= n){
                return true;
            }
        }
        return totalPeople >= n;
    }
}