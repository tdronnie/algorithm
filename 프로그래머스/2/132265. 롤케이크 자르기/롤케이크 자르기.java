import java.util.*;
class Solution {
    public int solution(int[] topping) {
        
        int[] part1 = new int[10001];
        int part1Kinds = 0;
        Set<Integer> part2 = new HashSet<>();
        int count = 0;
        
        for(int t : topping){
            if(part1[t] == 0) part1Kinds++; // 종류 수만 체크
            part1[t]++;
        }
        
        // 앞에서 하나씩 파니셔닝
        for(int t : topping){
            part2.add(t);
            part1[t]--;            
            if(part1[t] == 0) part1Kinds--;
            
            if(part1Kinds == part2.size()){
                count++;
            }
        }
        
        return count;
        
    }
}