import java.util.*;
class Solution {
    
    int[] infoA;
    List<Integer>[] arr;
    int maxCatch = -1;
    public int solution(int[] info, int[][] edges) {
        
        infoA = info;
        arr = new ArrayList[info.length];
        for(int i=0; i<info.length; i++){
            arr[i] = new ArrayList<>();
        }
        
        for(int[] edge : edges){
            arr[edge[0]].add(edge[1]); // 부모 - (자식, 양/늑대)
        }
        
        List<Integer> nexts = new ArrayList<>();
        nexts.add(0);
        
        catchAnimal(0, 0, 0, nexts);
        
        return maxCatch;
        
    }
    
    public void catchAnimal(int curr, int sheep, int wolf, List<Integer> nexts){
        
        if(infoA[curr] == 0) sheep++;
        else wolf++;
        
        if(sheep <= wolf){
            return;
        }
        maxCatch = Math.max(maxCatch, sheep);
        
        List<Integer> newNexts = new ArrayList<>(nexts);
        newNexts.remove(Integer.valueOf(curr));
        for(int child : arr[curr]){
            newNexts.add(child);
        }
        
        // 자식들 모두 방문
        for(int n : newNexts){
            catchAnimal(n, sheep, wolf, newNexts);
        }
    }
}