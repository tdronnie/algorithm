import java.util.*;
class Solution {
    public String solution(String s) {    
        char[] chars = s.toLowerCase().toCharArray();
        if(chars[0] >= 'a' && chars[0] <= 'z'){
          chars[0] = Character.toUpperCase(chars[0]);
        }
        for(int i=1; i<chars.length; i++){
            if(chars[i-1] == ' ' && chars[i] >= 'a' && chars[i] <= 'z'){
                chars[i] = Character.toUpperCase(chars[i]);
            }
        }
        return String.valueOf(chars);
    }
}