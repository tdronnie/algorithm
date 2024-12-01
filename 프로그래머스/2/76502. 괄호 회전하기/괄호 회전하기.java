import java.util.*;
class Solution {
    public int solution(String s) {
        int count = 0;
        for(int i=0; i<s.length(); i++){
            if(check(s.substring(i) + s.substring(0, i))){
                count++;
            }
        }
        return count;
    }
    public boolean check(String str){
        char[] chs = str.toCharArray();
        Stack<Character> st = new Stack<>();
        
        for(int i=0; i<chs.length; i++){
            if(st.isEmpty()){
                st.add(chs[i]);
                continue;
            }
            switch(chs[i]){
                case ')':
                    if(st.peek() == '(') st.pop();
                    break;
                case '}':
                    if(st.peek() == '{') st.pop();
                    break;
                case ']':
                    if(st.peek() == '[') st.pop();
                    break;
                default:
                    st.push(chs[i]);
                    break;
            }
        }
        if(st.isEmpty()) return true;
        return false;
    }
}