import java.util.*;
class Solution {
    
    int maxInfect;
    List<int[]>[] arr;
    
    public int solution(int n, int infection, int[][] edges, int k) {
        
        maxInfect = 1;
        arr = new ArrayList[n+1];
        Set<Integer> effected = new HashSet<>();
        for(int i=1; i<=n; i++){
            arr[i] = new ArrayList<>();
        }
        
        for(int[] edge : edges){
            arr[edge[0]].add(new int[]{edge[1], edge[2]});
            arr[edge[1]].add(new int[]{edge[0], edge[2]});
        }
        
        effected.add(infection);
        
        search(effected, 0, k);
        return maxInfect;
        
    }
    
    public void search(Set<Integer> effected, int count, int k){
        
        if(count == k){
            maxInfect = Math.max(maxInfect, effected.size());
            return;
        }
        
        // 특정 파이프 열기 턴에 감염시킬 수 있는 노드 찾기
        for(int type = 1; type <= 3; type++){
            
            Set<Integer> newEffected = new HashSet<>();
            Queue<Integer> q = new ArrayDeque<>(effected);
        
            while(!q.isEmpty()){
                int curr = q.poll();

                for(int[] next : arr[curr]){
                    if(!effected.contains(next[0]) && !newEffected.contains(next[0]) && next[1] == type){
                        newEffected.add(next[0]);
                        q.add(next[0]); // 특정 파이프로 들어갈 수 있을만큼 들어가기
                    }
                }
            }
            
            Set<Integer> nextEffected = new HashSet<>(effected);
            if(!newEffected.isEmpty()){
                nextEffected.addAll(newEffected);
            }
            
            search(nextEffected, count + 1, k);
        }
    }
}