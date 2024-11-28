import java.util.*;
class Solution
{
    public int solution(String s)
    {
        Stack<Character> st = new Stack<>();
        char[] chs = s.toCharArray();

        st.add(chs[0]);
        for(int i=1; i<chs.length; i++){
            if(st.isEmpty() || st.peek() != chs[i]){
                st.add(chs[i]);
            } else{
                st.pop();
            }
        }
        if(st.isEmpty()){
            return 1;
        } else {
            return 0;
        }
    }
}