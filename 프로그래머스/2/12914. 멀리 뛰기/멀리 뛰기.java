class Solution {
    public long solution(int n) {
        if(n <= 2) return n;
        long prepre = 1;
        long pre = 2;
        long next = 0;
        
        for(int i=3; i<= n; i++){
            next = (prepre + pre) % 1234567;
            prepre = pre;
            pre = next;
        }
        return pre;
    }
}