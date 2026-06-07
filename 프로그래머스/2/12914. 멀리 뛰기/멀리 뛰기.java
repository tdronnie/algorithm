class Solution {
    int[] memo;
    int[] jump;
    
    public long solution(int n) {
        
        memo = new int[n+1];
        jump = new int[n+1];
        
        return jump(n);
        
    }
    
    public int jump (int n){
        if(n == 1) return 1;
        if(n == 2) return 2;
        if(memo[n] != 0) return memo[n] % 1234567;
        return memo[n] = (jump(n-2) + jump(n-1)) % 1234567;
            
    }
}