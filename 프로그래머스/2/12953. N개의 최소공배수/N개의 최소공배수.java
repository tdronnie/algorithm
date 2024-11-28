import java.util.*;
class Solution {
    public int solution(int[] arr) {
        Arrays.sort(arr);
        int[] newArr = new int[arr.length];
        for(int i =0; i<arr.length; i++){
            newArr[i] = arr[arr.length-1-i];
        }
        int result = newArr[0];
        for(int i=1; i<newArr.length; i++){
            result = lcm(result, newArr[i]);
        }
        return result;       
    }
    
    public int lcm(int a, int b){
        return a * b / gcd(a, b);
    }
    
    public int gcd(int a, int b){
        if(b == 0) return a;
        return gcd(b, a%b);
    }
}