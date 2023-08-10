class Solution {
    public int solution(int n) {
        int answer = 0;
        int rslt = fibo(n);
        answer = rslt%1234567;
        return answer;
    }
    
    public int fibo(int n){
        //피보나치 수 저장 배열
        int[] f = new int[n+1];
        f[0]=0;
        f[1]=1;
        for(int i=2; i<=n; i++){
            f[i] = f[i-2]+f[i-1]; //피보 계산
            if(f[i] > 1234567){ //넘어간다면 한번 나눠주기
                f[i] %= 1234567;
            }
        }
        return f[n];
        //불가 코드
        // if(n==0)
        //     return 0;
        // if(n==1)
        //     return 1;
        // else
        //     return fibo(n-2)+fibo(n-1);
    }
}