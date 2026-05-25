import java.util.*;
class Solution {
    
    List<int[]>[] arr;
    int[] minDis;
    public int solution(int N, int[][] road, int K) {
        arr = new ArrayList[N + 1];
        int count = 0;
        
        for(int i=1; i<=N; i++){
            arr[i] = new ArrayList<>();
        }
        
        for(int i=0; i<road.length; i++){
            int start = road[i][0];
            int end = road[i][1];
            int weight = road[i][2];
            arr[start].add(new int[]{end, weight});
            arr[end].add(new int[]{start, weight});
        }
        
        minDis = new int[N + 1];
        Arrays.fill(minDis, Integer.MAX_VALUE);
        minDis[1] = 0;
        
        findShortest(1);
        
        for(int i=1; i<=N; i++){
            // System.out.println(i + "번 마을 가능 :" + minDis[i] + "시간 걸림");
            if(minDis[i] <= K) count++;
        }

        return count;
        
    }
    
    public void findShortest(int start){
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{start, 0});
        
        while(!pq.isEmpty()){
            int[] current = pq.poll();
            
            if(minDis[current[0]] < current[1]) continue;
            
            for(int[] next : arr[current[0]]){
                if(minDis[next[0]] > minDis[current[0]] + next[1]){
                    minDis[next[0]] = minDis[current[0]] + next[1];
                    pq.add(new int[]{next[0], minDis[next[0]]});
                }
            }
            
        }
    }
}