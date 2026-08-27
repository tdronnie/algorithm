import java.util.*;
class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        // 최소 intensity와 같다면 산봉우리 번호 낮은 것 택
        // 출입구에서 산봉우리까지 한번만 탐색
        // 경로 탐색 시 최대 가중치 갱신, 노드 탐색 시 최소 가중치일때만 갱신
        
        List<int[]>[] arr = new ArrayList[n+1];
        for(int i=1; i<=n; i++){
            arr[i] = new ArrayList<>();            
        }
        
        for(int[] p : paths){
            int s = p[0];
            int e = p[1];
            int w = p[2];
            arr[s].add(new int[]{e, w});
            arr[e].add(new int[]{s, w});
        }
        
        Set<Integer> g = new HashSet<>();
        Set<Integer> s = new HashSet<>();
        
        for(int gate : gates){
            g.add(gate);
        }
        
        for(int summit : summits){
            s.add(summit);
        }
        
        int[] minWeight = new int[n+1];
        Arrays.fill(minWeight, Integer.MAX_VALUE);
        
        Queue<int[]> q= new ArrayDeque<>();
        
        // 출입구부터 시작
        for(int gate : gates){
            q.add(new int[]{gate, 0});
            minWeight[gate] = 0;
        }
        
        while(!q.isEmpty()){
            int[] curr = q.poll();
            
            // 첫번째 산봉우리 방문 후 끝내기
            if(s.contains(curr[0])) continue;
            
            
            for(int[] next : arr[curr[0]]){
                int totalMaxWeight = Math.max(curr[1], next[1]); // 현재 경로의 가중치 최댓값이 방문하는 노드의 최소가중치인지
                if(minWeight[next[0]] > totalMaxWeight && !g.contains(next[0])){
                    minWeight[next[0]] = totalMaxWeight;
                    q.add(new int[]{next[0], minWeight[next[0]]});
                }
            }
        }
        
        List<int[]> answer = new ArrayList<>();
        for(int summit : summits){
            answer.add(new int[]{summit, minWeight[summit]});
        }
        
        Collections.sort(answer, (a, b) -> {
            if(a[1] == b[1]){
                return a[0] - b[0];
            }
            
            return a[1] - b[1];
        });
        
        return answer.get(0);
    }
}