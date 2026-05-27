import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        
        Arrays.sort(people);
        
        int min = 0;
        int max = people.length - 1;
        int count = 0;
        
        while(min <= max){
            
            // 한 사람만 남았을 경우
            if(min == max){
                count++;
                break;
            }
            
            // 현재 최소무게와 최대무게의 합이 제한 초과
            if(people[min] + people[max] > limit){
                count++; // 최대무게인 사람 구명보트 혼자 타기
                max--;
            } else { // 합이 제한 이하
                count++; // 최소무게 사람, 최대무게 사람 같이 타기
                min++;
                max--;
            }
        }
        
        return count;
        
    }
}