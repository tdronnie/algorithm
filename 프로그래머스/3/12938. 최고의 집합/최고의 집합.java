import java.util.*;
class Solution {
    public int[] solution(int n, int s) {
        if(s/n < 1){
            return new int[]{-1};
        }
        
        int[] result = new int[n];
        if(s%n == 0){
            Arrays.fill(result, s/n);
        } else {
            Arrays.fill(result, s/n);
            int remain = s%n;
            for(int i=n-1; i>=0 && remain>0; i--){
                if(i < 0){
                    i = n-1;
                }
                result[i]++;
                remain--;
            }
        }
        return result;
    }
}