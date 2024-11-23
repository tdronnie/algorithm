import java.util.*;
class Solution {
    public int[] solution(String s) {
        int count = 1;
        int replaceZeros = 0;
        while(true){
            replaceZeros += s.length() - s.replace("0", "").length();
            if(s.length() - s.replace("1", "").length() == 1){
                break;
            }
            s = s.replace("0", "");
            s = Integer.toString(s.length(), 2);
            count++;
        }
        return new int[]{count, replaceZeros};
    }
}