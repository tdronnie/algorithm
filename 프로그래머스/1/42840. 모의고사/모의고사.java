import java.util.*;
class Solution {
    public int[] solution(int[] answers) {
        
        List<Integer> arr = new ArrayList<>();
        // 가장 높은 점수와 각 수포자 점수
        int maxCount = -1;
        int no1Count = 0;
        int no2Count = 0;
        int no3Count = 0;
        int[] no1 = {1, 2, 3, 4, 5}; // 5개 반복
        int[] no2 = {2, 1, 2, 3, 2, 4, 2, 5}; // 8개 반복
        int[] no3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}; // 10개 반복
        
        for(int i=0; i<answers.length; i++){
            // 각 수포자에 대해 몯듈러 연산
            if(answers[i] == no1[i%5]) {
                no1Count++;
            }
            if(answers[i] == no2[i%8]) {
                no2Count++;
            }
            if(answers[i] == no3[i%10]) {
                no3Count++;
            }
        }
        
        // 가장 높은 점수 계산
        maxCount = Math.max(no1Count, Math.max(no2Count, Math.max(maxCount, no3Count)));
        if(no1Count == maxCount){
            arr.add(1);
        }
        if(no2Count == maxCount){
            arr.add(2);
        }
        if(no3Count == maxCount){
            arr.add(3);
        }
        
        return arr.stream().mapToInt(Integer::intValue).toArray();
        
        
    }
}