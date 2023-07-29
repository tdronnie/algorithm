import java.util.Arrays;
class Solution {
    public int[] solution(int num, int total) {
        int[] rslt = new int[num];
        int val = total / num;

        int start;
        if(num % 2 != 0) //홀수개 연속일 경우
            start = val - (num/2); //val에 중간값보다 작은숫자의 개수만큼 빼주기
        else//짝수개 연속일 경우
            start = val-(num/2-1); //val에 중간값보다 작은숫자의 개수만큼 빼주기

        for(int i=0; i <num; i++){
            rslt[i] = start++;
        }
        return rslt;
    }
}