import java.util.*;
class Solution {
    public int solution(int k, int[][] dungeons) {
        List<Dungeon> ds = new ArrayList<>();
        
        for(int[] d : dungeons){
            ds.add(new Dungeon(d[0], d[1]));    
        }
    
        boolean[] visited = new boolean[dungeons.length];
        return dfs(visited, ds, 0, k);
    }
    
    public int dfs(boolean[] visited, List<Dungeon> d, int count, int hp){
        int max = count;
        
        for(int i=0; i<d.size(); i++){
            if(visited[i]) continue;
            if(hp >= d.get(i).need){
                visited[i] = true;
                int calCount = dfs(visited, d, count + 1, hp-d.get(i).minus);
                max = Math.max(max, calCount);
                visited[i] = false;
            }
        }
        return max;
    }
}
class Dungeon {
    int need;
    int minus;
    
    public Dungeon(int need, int minus){
        this.need = need;
        this.minus = minus;
    }
    
    public int compareTo(Dungeon d){
        if(this.need == d.need){
            return this.minus - d.minus;
        }
        return d.need - this.need;
    }
}