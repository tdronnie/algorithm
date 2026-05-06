import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        
        // 소요시간, 요청시간, 작업번호 담는 pq
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            Comparator.comparingInt((int[] a) -> a[0])
            .thenComparingInt(a -> a[1])
            .thenComparingInt(a -> a[2]));
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]); // job에 대해서 요청시간 순으로 고려할 수 있도록 정렬
        int sum = 0; // 작업들의 반환시간 합
        int time = 0; // 현재 시간
        int idx = 0; // 작업 인덱스
        
        // 현재 시간 이내 요청된 작업 대기열 넣음 or 없다면 현재시간에서 가장가까운 요청시간으로 점프 -> 작업 처리
        while(true){
        
            // 현재 시간이내 요청작업 대기열에 넣기
            while(idx < jobs.length && jobs[idx][0] <= time){
                pq.add(new int[]{jobs[idx][1], jobs[idx][0], idx});
                idx++;
            }
            
            // 없다면 시간 보내기
            if(pq.isEmpty()){
                if(idx < jobs.length){ // 모든 작업 아직 안끝난 경우
                    time = jobs[idx][0]; // 다음작업 요청시간으로 점프
                    continue;
                } else {
                    break; // 작업 모두 끝냄
                }
            }
            
            // 작업 처리
            int[] task = pq.poll();
            time += task[0]; // 작업 소요시간만큼 시간 흐름
            sum += (time - task[1]); // 작업 반환까지 걸린 시간 계산
        }
        
        return sum/jobs.length;       
        
    }
}