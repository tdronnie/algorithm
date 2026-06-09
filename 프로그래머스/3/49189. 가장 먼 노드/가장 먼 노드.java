import java.util.*;
class Solution {
    public int solution(int n, int[][] edge) {
        
        // int[][] arr = new int[n+1][n+1];
        // for(int i=0; i<edge.length; i++){
        //     arr[edge[i][0]][edge[i][1]] = 1;
        //     arr[edge[i][1]][edge[i][0]] = 1;
        // }
        ArrayList<Integer>[] arr = new ArrayList[n+1];
        for(int i=1; i<=n; i++){
            arr[i] = new ArrayList<>();
        }
        
        for(int i=0; i<edge.length; i++){
            arr[edge[i][0]].add(edge[i][1]);
            arr[edge[i][1]].add(edge[i][0]);
        }
        
        int[] dis = new int[n + 1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        dis[1] = 0;
        
        while(!q.isEmpty()){
            int curr = q.poll();
            
            for(int next : arr[curr]){
                if(dis[next] > dis[curr] + 1){
                    dis[next] = dis[curr] + 1;
                    q.add(next);
                }
            }
        }
        
        int count = 0;
        int max = 0;
        for(int d : dis){
            if(d != Integer.MAX_VALUE){
                max = Math.max(max, d);    
            }
        }
        for(int d : dis){
            if(d == max){
                count++;
            }
        }
        
        return count;
        
    }
}