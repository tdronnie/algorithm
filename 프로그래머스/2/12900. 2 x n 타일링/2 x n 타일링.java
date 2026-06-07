class Solution {
    public int solution(int n) {
        if(n == 1) return 1;
        if(n == 2) return 2;
        if(n == 2) return 3;
        int[] shape = new int[n+1];
        shape[1] = 1;
        shape[2] = 2;
        shape[3] = 3;
        
        for(int i=4; i<=n; i++){
            shape[i] = (shape[i-2] + shape[i-1]) % 1000000007;
        }
        
        return shape[n];
    }
}