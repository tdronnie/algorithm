import java.util.*;
class Solution {
    public int solution(int x, int y, int n) {
        
        return transform(x, y, n);
        
    }
    
    int transform(int x, int y, int n){
        
        Queue<int[]> q = new ArrayDeque<>();
        boolean[] visited = new boolean[y+1];
        q.add(new int[]{x, 0});
        visited[x] = true;
        
        while(!q.isEmpty()){
            int[] value = q.poll();
            
            if(value[0] == y){
                return value[1];
            }
            
            int value1 = value[0] + n;
            int value2 = value[0] * 2;
            int value3 = value[0] * 3;
            
            if(value1 <= y && !visited[value1]){
                visited[value1] = true;
                q.add(new int[]{value1, value[1]+1});
            }
            
            if(value2 <= y && !visited[value2]){
                visited[value2] = true;
                q.add(new int[]{value2, value[1]+1});
            }
            
            if(value3 <= y && !visited[value3]){
                visited[value3] = true;
                q.add(new int[]{value3, value[1]+1});
            }
            
  
        }
        
        return -1;
        
        
    }
}