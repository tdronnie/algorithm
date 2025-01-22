import java.util.*;

class Solution {
    public int solution(int n, int k) {
        String chNum = Integer.toString(n, k);
        StringTokenizer st = new StringTokenizer(chNum, "0");
        int primeCount = 0;
        
        while(st.hasMoreTokens()){
            long part = Long.parseLong(st.nextToken());
            if(isPrime(part)){
                primeCount++;        
            }
        }
        return primeCount;
    }
    
    boolean isPrime(long num){
        if(num == 1) return false;
        
        for(long i=2; i<= Math.sqrt(num); i++){
            if(num%i == 0) return false;
        }
        return true;
    }
}