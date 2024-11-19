class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        
        int maxLv = 0;
        for(int i=0; i<diffs.length; i++){
            maxLv = Math.max(maxLv, diffs[i]);
        }
        
        int left = 1;
        int right = maxLv;
        
        while(left < right){
            int mid = (left+right)/2;
            
            if(canSolve(mid, diffs, times, limit)){
                right = mid;
            } else{
                left = mid + 1;
            }
        }
        return right;
    }
    
    public boolean canSolve(int level, int[] diffs, int[] times, long limit){
        long time = times[0];
        for(int i=1; i<diffs.length; i++){
            if(level >= diffs[i]){
                time += times[i];
            } else{
                time = time + (long)((diffs[i] - level) * (times[i] + times[i-1]) + times[i]);
            }
            if(time > limit){
                return false;
            }
        }
        return true;
    }
}