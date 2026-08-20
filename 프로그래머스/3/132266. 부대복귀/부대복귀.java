import java.util.*;
class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        
        ArrayList<Integer>[] arr = new ArrayList[n + 1];
        int[] answer = new int[sources.length];
        int idx = 0;
        
        for(int i=1; i<=n; i++){
            arr[i] = new ArrayList<>();
        }
        
        for(int[] road : roads){
            arr[road[0]].add(road[1]);
            arr[road[1]].add(road[0]);
        }
        
        // 부대에서 각 부대원 최단거리 찾기
        int[] minDis = new int[n+1];
        Arrays.fill(minDis, Integer.MAX_VALUE);
        minDis[destination] = 0;
        
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(destination);
        visited[destination] = true;

        while(!q.isEmpty()){
            int curr = q.poll();

            for(int next : arr[curr]){
                if(visited[next]) continue;
                visited[next] = true;
                minDis[next] = minDis[curr] + 1;
                q.add(next);
            }
        }
        
        for(int s : sources){
            answer[idx++] = (minDis[s] == Integer.MAX_VALUE) ? -1 : minDis[s];
        }
        
        return answer;
    }
}