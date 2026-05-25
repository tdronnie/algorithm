import java.util.*;
class Solution {
    public int solution(int n, int k, int[] enemy) {
        
        // 갔다가ㅏ 불가능한 라운드 만나면 이전 라운드 중 적 수 가장 많은 라운드에 무적권 사용 (최대한 많은 병사 확보)
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i=0; i<enemy.length; i++){
            n -= enemy[i];
            pq.add(enemy[i]);
            
            // 현재 라운드 통과 불가
            if(n < 0){
                // 무적권 사용할 수 있으면
                if (k > 0){
                    n += pq.poll();
                    k--;
                } else { // 무적권 없다면 현재 라운드에서 게임종료
                    return i;
                }
            }
        }
        
        return enemy.length; // 모든 라운드 막아냄
        
    }
}