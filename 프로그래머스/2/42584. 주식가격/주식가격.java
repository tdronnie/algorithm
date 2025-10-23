import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        Stack<Integer> st = new Stack<>();
        int[] answer = new int[prices.length];
        
        for(int i=0; i<prices.length; i++){
            while(!st.isEmpty() && prices[st.peek()] > prices[i]){
                int lowStart = st.pop();
                answer[lowStart] = i - lowStart; 
            }
            st.push(i);
        }
        
        //뒤에 더 낮은 가격이 나오지 않았던 인덱스 처리
        for(int i=0; i<prices.length; i++){
            if(answer[i] == 0){
                answer[i] = prices.length - i - 1;
            }
        }
        
        return answer;
        
    }
}