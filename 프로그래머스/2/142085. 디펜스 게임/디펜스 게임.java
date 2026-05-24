import java.util.*;
class Solution {
    
    int maxRound;
    public int solution(int n, int k, int[] enemy) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        // 라운드 진행하다가 적이 많은 순서대로 진행하다가 끝난 경우 다시 돌아가서 무적권 처리로 세이브
        for(int i=0; i< enemy.length; i++){
            n -= enemy[i];
            pq.add(enemy[i]); // 나중에 돌아와서 무적권 쓸 수도 있으니 세이브
            
            // 현재 라운드 진행 불가
            if(n < 0){
                // 저장해놓았던 턴 중 적이 가장 많은 라운드에 무적권 사용
                if(k > 0){
                    n += pq.poll(); // 병사 살리기
                    k--;
                } else { // 사용할 무적권 없다면 라운드 종료
                    return i;
                }          
            }
        }
        
        return enemy.length;
        
        
        
        
        
        
//         maxRound = -1;
        
//         playGame(n, k, 0, enemy);
        
        // return maxRound;
        
    }
    
    public void playGame(int remain, int card, int round, int[] enemy){
        
        // 모든 라운드 완료
        if(round == enemy.length){
            maxRound = round;
            return;
        }
        
        // 무적권 사용
        if(card > 0){
            playGame(remain, card - 1, round + 1, enemy);    
        }
        
        // 막을 수 있는 경우
        if(remain >= enemy[round]){
            playGame(remain - enemy[round], card, round+1, enemy);    
        } else { // 막을 수 없는 경우, 이전 라운드까지 가능
            maxRound = Math.max(maxRound, round);
            return;
        }
        
    }
}