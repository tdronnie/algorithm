import java.util.*;
class Solution {
    public int solution(int[] players, int m, int k) {
        
        int[] servers = new int[24];
        int add = 0;
        
        for(int i=0; i<players.length; i++){
            if(servers[i] < players[i]/m){ // 3명 1개필요, 6명 2개 필요 ...
                int need = players[i]/m - servers[i];
                add += need;
                for(int j=i; j<i+k && j < 24; j++){
                    servers[j] += need;
                }
            }
        }
        
        return add;

    }
}