import java.util.*;
class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        
        String[] ans = new String[n];
        
        //10진수 -> 2진수 -> #배열 변환, 한 행 씩 연산
        for(int i=0; i<n; i++){
            
            int[] t1 = transform(arr1[i], n);
            int[] t2 = transform(arr2[i], n);
            
            StringBuilder sb = new StringBuilder();
            for(int j=0; j<n; j++){
                if((t1[j]|t2[j]) == 1) //or연산
                    sb.append("#");
                else{
                    sb.append(" ");
                }
            }
            
            ans[i] = sb.toString();
        }
        return ans;
        
    }
                   
    public int[] transform(int n, int len){
        int[] rslt = new int[len];
        int i=len-1;
        StringBuilder sb = new StringBuilder();
        //이진수 거꾸로 저장
        while(n!=0){//0 아닌 동안 2로 나누기
            rslt[i--] = n%2;
            n/=2;
        }
        return rslt;
    }
} 
                  
                   