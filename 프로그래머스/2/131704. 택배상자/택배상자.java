import java.util.*;
class Solution {
    public int solution(int[] order) {
        Stack<Integer> st = new Stack<>();
        int count = 0;
        int num = 1;
        
        for(int i=0; i<order.length; i++){
            int target = order[i];
            
            
            while(num <= order.length && num < target){
                st.push(num++);
            }
            
            //트럭에 있음
            if(num == target){
                count++;
                num++;
            }
            
            //보조 컨베이어에 있음
            else if(!st.isEmpty() && target == st.peek()){
                st.pop();
                count++;
            }
            
            //현재 차례에 찾을 수 없음, 종료
            else {
                break;
            }
            
        }
        
        return count;
        
    }
}