import java.util.*;
class Solution {
    public int solution(int[] cards) {
        
        boolean[] visited = new boolean[cards.length + 1];
        ArrayList<Integer> kinds = new ArrayList<>();

        for(int i=0; i<cards.length; i++){
            // if(visited[i]) continue;
            int idx = i;
            int count = 0;
            // 남은 상자 중 게임 진행
            while(!visited[cards[idx]]){
                visited[cards[idx]] = true;
                idx = cards[idx]-1; // 상자 번호 업데이트
                count++;
            }
            kinds.add(count);
        }
        
        if(kinds.size() == 1) return 0;
        Collections.sort(kinds);
        return kinds.get(kinds.size() - 1) * kinds.get(kinds.size() - 2);
    }
}