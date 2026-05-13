import java.util.*;
class Solution {
    public int solution(int[] citations) {
        
        // 논문 수가 1인 경우, 해당 논문의 인용 횟수가 H-index
        int len = citations.length;
        if(len == 1) return citations[0];
        
        Arrays.sort(citations); // 이분탐색 위한 정렬
        
        int left = 0;
        int right = len - 1;
        
        while(left <= right){
            
            int mid = (left + right)/2;
            
            // len-mid -> citations[mid]번 이상 인용된 논문 수
            if(len - mid > citations[mid]){ // 이상 인용된 논문 수가 많다면
                left = mid + 1; // H 더 키워보기, 오른쪽 파트 탐색
            } else {
                right = mid - 1; // 인용된 논문 수가 적거나 같다면, H 낮추기, 왼쪽 파트 탐색
            }
        }
        
        return len-left; // 마지막 턴 종료 후 left이하의 논문 수는 H-Index 불가 개수, left기준 오른쪽에 있는 논문 수 카운트
        
    }
}