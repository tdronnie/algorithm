import java.util.*;
class Solution {
    public int solution(int n) {
        String binaryValue = Integer.toBinaryString(n);
        int oneCount = binaryValue.length() - binaryValue.replace("1", "").length();
        
        for(int num=n+1; num<= 1000000; num++){
            int numOneCount = Integer.toBinaryString(num).length() - Integer.toBinaryString(num).replace("1", "").length();
            if(oneCount == numOneCount){
                return num;
            }
        }
        return -1;
    }
}