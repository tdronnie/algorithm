import java.util.*;
class Solution {
    boolean solution(String s) {
        char[] chArray = s.toCharArray();
        
        return checkValid(chArray);
    }
    
    boolean checkValid(char[] arr){
        Stack<Character> s = new Stack<>();
        
        for(int i=0; i<arr.length; i++){
            char ch = arr[i];
            if(ch == '('){
                s.add(ch);
            } else{
                if(s.isEmpty()){
                    return false;
                }
                s.pop();
            }
        }
        if(s.isEmpty()){
            return true;    
        }
        return false;
    }
}