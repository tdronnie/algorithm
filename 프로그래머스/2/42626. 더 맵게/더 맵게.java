import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 스코빌 지수기준 오름차순 정렬
        int count = 0;
        
        // 음식 정렬
        for(int food : scoville){
            pq.add(food);
        }
        
        // 섞을 수 있는 두 음식이 존재하고 가장 낮은 음식의 스코빌 지수가 K미만인 동안 음식 섞기
        while(pq.size() > 1 && pq.peek() < K){
            int l1 = pq.poll(); // 가장 맵지 않은 음식의 스코빌 지수
            int l2 = pq.poll(); // 두번째로 맵지 않은 음식의 스코빌 지수
            
            pq.add(l1 + (l2*2)); // 음식 섞기
            count++; // 섞기 카운트
            
        }
        
        // 섞기 후 가장 맵지 않은 음식의 스코빌 지수가 K 미만이라면 실패
        if(pq.peek() < K){
            return -1;
        } else {
            return count;
        }
        
    }
}