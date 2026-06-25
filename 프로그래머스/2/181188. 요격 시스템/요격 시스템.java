import java.util.*;
class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (a, b) -> a[1] - b[1]);
        int count = 1;
        int fire = targets[0][1];
        
        for(int i=1; i<targets.length; i++){
            // 현재 요격위치가 커버하지 못한다면 하나 더 추가, 끝에 걸쳐도 추가
            if(targets[i][0] >= fire){
                fire = targets[i][1];
                count++;
            }
        }
        
        return count;
    }
}