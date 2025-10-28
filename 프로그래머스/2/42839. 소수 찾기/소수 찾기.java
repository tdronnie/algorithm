import java.util.*;
class Solution {
    
    static Set<Integer> nums = new HashSet<>();
    static boolean[] isPrime;
    public int solution(String numbers) {
        
        boolean[] visited = new boolean[numbers.length()];
        int count = 0;
        
        //순열로 나올 수 있는 숫자 찾기
        find(numbers, "", visited);
        //소수 구해두기
        sieve(Collections.max(nums));
        
        for(int n : nums){
            //소수 판별
            if(isPrime[n]){
                count++;
            }
        }
        
        return count;
        
    }
    
    static void find(String s, String current, boolean[] visited){
        if(!current.equals("")){
            nums.add(Integer.parseInt(current));
        }
        
        for(int i = 0; i<s.length(); i++){
            if(!visited[i]){
                visited[i] = true;
                find(s, current + s.charAt(i), visited);
                visited[i] = false;
            }
        }
    }
    
//     static boolean isPrime(int n){
        
//         if(n == 1) return false;
//         if(n == 2) return true;
//         if(n % 2 == 0) return false;
        
//         int sqrtN = (int)Math.sqrt(n);
        
//         for(int i=3; i<=sqrtN; i+=3){
//             if(n%i == 0){
//                 return false;
//             }
//         }
        
//         return true;
//     }
    
    void sieve(int n) {
        isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        if (n >= 0) isPrime[0] = false;
        if (n >= 1) isPrime[1] = false;

        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }
    
    
}