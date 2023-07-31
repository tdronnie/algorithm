class Solution {
    static int cnt=0;
    static boolean fail = false;
    public int solution(int num) {
        cal(num); //최대 500번 반복
        if(fail)
            return -1;
        return cnt;
        
        
    }
    //num을 1로 만들기 위한 연산을 하면서 연산횟수를 세는 함수
    //num이 8백만까지 가능하므로 연산을 적용하면 int형을 넘어설 수 있음
    public static void cal(long n){
        if(cnt > 500){ //500번 반복했지만 1 되지 않았을때
            fail = true;
            return;
        }
       if(n==1){
           return;
       }
        cnt++;
        if(n%2==0){ //짝수인 경우
            cal(n/2);
        }
        else{ //홀수인 경우
            cal(n*3+1);
        }
    }
}