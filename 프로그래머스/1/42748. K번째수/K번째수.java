import java.util.*;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        
        int[] answer = new int[commands.length]; // 정답 배열은 명령 횟수
        
        for(int i=0; i<commands.length; i++){
            int start = commands[i][0];
            int end = commands[i][1];
            int target = commands[i][2];
            int[] part = Arrays.copyOfRange(array, start-1, end); // 부분 배열 생성
            Arrays.sort(part); // 부분 배열 정렬
            answer[i] = part[target-1]; // 타겟 숫자 저장
        }
        
        return answer;
    }
}