import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        int time = 1;
        long amount = 0;
        Integer[] ints = new Integer[works.length];
        PriorityQueue<Integer> jobs = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i=0; i<works.length; i++){
            jobs.add(works[i]);
        }
        
        while(time <= n){           
            if(jobs.peek() != 0){
                int job = jobs.poll();
                jobs.add(job - 1);
            }
            time++;
        }
        
        for(int job : jobs){
            amount += Math.pow(job, 2);
        }
        
        return amount;
    }
}