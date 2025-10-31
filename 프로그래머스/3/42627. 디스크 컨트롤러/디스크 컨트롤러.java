import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        //소요시간 짧은 것, 요청시간 빠른 것, 번호가 작은 것 순
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing((int[] a) -> a[0])
                                                      .thenComparing((int[] a) -> a[1])
                                                      .thenComparing((int[] a) -> a[2]));
        int time = 0;
        int idx = 0;
        int sum = 0;
        
        while(true){
            
            while(idx < jobs.length && jobs[idx][0] <= time){
                pq.add(new int[]{jobs[idx][1], jobs[idx][0], idx});
                idx++;
            }
            
            if(pq.isEmpty()){
                if(idx < jobs.length){
                    time = jobs[idx][0];
                    continue; 
                } else {
                    break;
                } 
            }
            
            int[] task = pq.poll(); 
            time += task[0];
            sum += (time - task[1]); 
            
        }
        
        return sum/jobs.length;
        
    }
}