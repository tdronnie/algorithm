import java.util.*;
class Solution {
    public String solution(String s) {    
        char[] chars = s.toLowerCase().toCharArray();
        char[] result = Arrays.copyOf(chars, chars.length);
        if(result[0] >= 'a' && result[0] <= 'z'){
          result[0] = Character.toUpperCase(result[0]);
        }
        for(int i=1; i<result.length; i++){
            if(result[i-1] == ' ' && result[i] >= 'a' && result[i] <= 'z'){
                result[i] = Character.toUpperCase(result[i]);
            }
        }
        return String.valueOf(result);
    }
}