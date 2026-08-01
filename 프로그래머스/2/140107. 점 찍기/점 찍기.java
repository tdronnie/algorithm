class Solution {
    public long solution(int k, int d) {
        
        long count = 0;
        for(int i=0; i<=d; i+=k){
            count += ((long)Math.sqrt((long)d*d - (long)i*i))/k + 1;
        }
        
        return count;
    }
}