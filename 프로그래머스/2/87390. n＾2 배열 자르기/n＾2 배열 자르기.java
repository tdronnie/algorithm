class Solution {
    public long[] solution(int n, long left, long right) {
        
        int size = (int)(right - left + 1);
        long[] result = new long[size];
        for(long i = left; i <= right; i++){
            int idx = (int)(i-left);
            result[idx] = Math.max(i/n+1, i%n+1);
        }
        return result;
    }
}