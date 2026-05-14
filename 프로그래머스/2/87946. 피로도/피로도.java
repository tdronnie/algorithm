import java.util.*;
class Dungeon {
    int need;
    int minus;
    
    public Dungeon(int need, int minus){
        this.need = need;
        this.minus = minus;
    }
    
    // 최소 필요 피로도 내림차순, 소모 필요도 오름차순 정렬
    public int compareTo(Dungeon d){
        if(this.need == d.need){
            return this.minus - d.minus;
        }
        return d.need - this.need;
    }
}

class Solution {
    public int solution(int k, int[][] dungeons) {
        List<Dungeon> ds = new ArrayList<>();
        
        // 던전 정보 저장
        for(int[] d : dungeons){
            ds.add(new Dungeon(d[0], d[1]));    
        }
    
        boolean[] visited = new boolean[dungeons.length];
        return dfs(visited, ds, 0, k); // 던전 탐험
    }
    
    public int dfs(boolean[] visited, List<Dungeon> d, int count, int hp){
        int max = count;
        
        for(int i=0; i<d.size(); i++){
            if(visited[i]) continue;
            if(hp >= d.get(i).need){ // 현재 남은 피로도가 던전의 최소 필요 피로도보다 높거나 같으면 탐험
                visited[i] = true;
                int calCount = dfs(visited, d, count + 1, hp-d.get(i).minus); // 그 다음 탐험으로
                max = Math.max(max, calCount); // 탐험 가능한 최대 던전 수 업데이트
                visited[i] = false; // 백트레킹
            }
        }
        return max;
    }
}
