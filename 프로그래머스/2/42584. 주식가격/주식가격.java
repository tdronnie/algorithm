import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        
        Deque<Integer> dq = new ArrayDeque<>();
        int[] answer = new int[prices.length];
        
        for(int i=0; i<prices.length; i++){
            
            // 가격이 떨어지는 상황의 경우, 지금까지 저장된 이전 초 떨어지지 않은 기간 계산
            while(!dq.isEmpty() && prices[dq.peekFirst()] > prices[i]){
                int pre = dq.pollFirst();
                answer[pre] = i - pre;
            }
            
            // 가격이 떨어지지 않는 동안 잠시 저장
            dq.addFirst(i);
        }
        
        // 뒤에 낮은 가격 나오지 않았던 부분 처리
        for(int i=0; i<prices.length; i++){
            if(answer[i] == 0){
                answer[i] = prices.length - 1 - i;
            }
        }
        
        return answer;
    }
}