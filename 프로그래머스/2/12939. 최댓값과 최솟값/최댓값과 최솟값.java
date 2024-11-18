import java.util.*;
class Solution {
    public String solution(String s) {
        StringTokenizer st = new StringTokenizer(s);
        long minValue = Long.MAX_VALUE;
        long maxValue = Long.MIN_VALUE;
        
        while(st.hasMoreTokens()){
            long value = Long.parseLong(st.nextToken());
            minValue = Math.min(minValue, value);
            maxValue = Math.max(maxValue, value);
        }
        return String.valueOf(minValue + " " + maxValue);
    }
}