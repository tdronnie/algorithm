class Solution {
    long[] memo;
    public long solution(int n) {
        memo = new long[n+1];
        
        return value(n);
    }
    
    public long value(int n){
        if(n == 1) return 1;
        if(n == 2) return 2;
        if(memo[n] != 0) return memo[n];
        
        return memo[n] = (value(n-1) + value(n-2)) % 1234567;
    }
}