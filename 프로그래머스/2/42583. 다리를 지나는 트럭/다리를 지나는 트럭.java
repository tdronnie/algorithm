import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        
        Deque<int[]> dq = new ArrayDeque<>();
        int no = 0;
        int currWeight = 0;
        int time = 0;
        
        while(true){
            time++;
            
            // 지나간 트럭 처리 및 무게 변경
            while(!dq.isEmpty() && time - dq.peekLast()[1] == bridge_length){
                currWeight -= dq.pollLast()[0];
            }
            
            
            //건널 트럭 처리
            if(no < truck_weights.length){ // 아직 건널 트럭 남았다면
                // 무게, 길이 체크
                if(dq.size() == bridge_length) continue;
                if(currWeight + truck_weights[no] > weight) continue;

                //트럭 다리 올리기
                dq.addFirst(new int[]{truck_weights[no], time});
                currWeight += truck_weights[no];
                no++;
            } else { // 모든 트럭 다리에 올렸다면
                if(dq.isEmpty()) break; // 모두 지나도록 처리했다면 종료
            }
        }
        
        return time;
    }
}