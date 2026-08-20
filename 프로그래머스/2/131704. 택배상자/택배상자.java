import java.util.*;
class Solution {
    public int solution(int[] order) {
        int count = 0;
        
        Stack<Integer> st = new Stack<>();
        int idx = 0;
        
        for(int no=1; no <= order.length; no++){
            
            st.push(no);
            while (!st.isEmpty() && st.peek() == order[idx]) {
                st.pop();
                idx++;
            }
                   
        }
        
        // if(idx < order.length){
        //     while(!st.isEmpty()){
        //         if(st.peek() == order[idx]){
        //             st.pop();
        //             count++;
        //             idx++;
        //         } else {
        //             break;
        //         }
        //     }
        // }
        
        return idx;
        
    }
}