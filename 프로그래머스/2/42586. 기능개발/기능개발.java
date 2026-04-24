import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Deque<Integer> dq = new ArrayDeque<>();
        ArrayList<Integer> deploy = new ArrayList<>();
        
        // 각 작업순대로 배포까지 걸리는 날 저장
        for(int i=0; i<speeds.length; i++){
            dq.addLast((int)Math.ceil((double)(100-progresses[i])/speeds[i])); // 작업진도 + 작업속도 * ?일 = 100, 1.5일 완료라면 2일차에 배포하기 위해 올림처리
        }
        
        int maxTake = dq.peekFirst(); // 작업의 개수가 하나도 주어지지 않는 경우..?
        int done = 0;
    
        while(!dq.isEmpty()){
            if(maxTake < dq.peekFirst()){ // 더 오래 걸리는 작업 만나면 최근 배포날짜 업데이트, 이전 작업들 배포
                maxTake = dq.pollFirst();
                deploy.add(done);
                done = 1;
            } else {
                dq.pollFirst();
                done++; // 최근 배포날짜나 이전에 완료되는 작업 카운트
            }
        }
        
        deploy.add(done); // 마지막 배포 진행
        
        return deploy.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}