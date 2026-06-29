class Solution {
    public long solution(int[] sequence) {
        long maxSum = -50000000000L;
        long currSum1 = 0;
        long currSum2 = 0;
        
        // 각 부분에 펄스값을 곱하면서 최대합 찾기
        // 지금까지의 누적합에 더하는 것보다 현재 수가 더 크다면 현재 수부터 다시 더해가기
        for(int i=0; i<sequence.length; i++){
            int pulse1 = (i % 2 == 0) ? 1 : -1;
            int pulse2 = (i % 2 == 0) ? -1 : 1;
            
            currSum1 = Math.max(currSum1 + sequence[i] * pulse1, sequence[i] * pulse1);
            currSum2 = Math.max(currSum2 + sequence[i] * pulse2, sequence[i] * pulse2);
            
            maxSum = Math.max(maxSum, currSum1);
            maxSum = Math.max(maxSum, currSum2);
        }
        
        return maxSum;
        
    }
}