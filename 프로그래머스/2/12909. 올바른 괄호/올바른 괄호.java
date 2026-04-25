import java.util.*;
class Solution {
    boolean solution(String s) {
        
        Stack<Character> st = new Stack<>();
        
        for(char c : s.toCharArray()){
            if(c == '('){ // '('의 경우 push
                st.push(c);
            } else {
                if(st.isEmpty()){ // 비어있는 경우 false
                    return false;
                } else { // '('가 있는 경우 pop
                    st.pop();
                }
            }
        }
        
        if(!st.isEmpty()){ // 괄호 처리 끝나지 않은 경우 false
            return false;
        }
        
        return true;
        
    }
}