import java.util.*;
class Solution {
    public String solution(String number, int k) {
        
        Stack<Character> st = new Stack<>();
        StringBuilder sb = new StringBuilder();
        
        for(char ch : number.toCharArray()){
            while (!st.isEmpty() && st.peek() < ch && k > 0){
                st.pop();
                k--;                
            }
            st.push(ch);
        }
        
        while(!st.isEmpty()){
            // 같은 숫자만 연속되거나 오름차순으로 나와서 k 소진 되지 않았을 때, 먼저 작은 수 빼주기
            if(k > 0){
                st.pop();
                k--;
            }
            sb.append(st.pop());
        }
        
        return sb.reverse().toString();
        
    }
}