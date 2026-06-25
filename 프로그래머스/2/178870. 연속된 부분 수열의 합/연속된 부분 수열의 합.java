class Solution {
    public int[] solution(int[] sequence, int k) {
        int p1 = 0;
        int p2 = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        int sum = 0;
        
        // 투포인터
        while(p2 < sequence.length){
            
            // 현재 것 더하고 다음 가리키기 (end+1)
            sum += sequence[p2++]; 
            
            // k보다 커졌다면 작아질 때까지 옮기면서 빼기 (start)
            while (sum > k && p1 <= p2){
                sum -= sequence[p1++]; 
            }
            
            if(sum == k){
                // 여러개라면 짧은 것 찾기, 같으면 자동 앞쪽 수열 선택
                int len = p2 - p1 + 1;
                if(minLen > len){
                    minLen = len;
                    start = p1;
                    end = p2-1;
                }
            }
        }
        return new int[]{start, end};
    }
}