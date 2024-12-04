import java.util.*;
class Solution {
    public int solution(int[] priorities, int location) {
        int[] sorts = priorities.clone();
        Arrays.sort(sorts);
        Queue<int[]> q = new ArrayDeque<>();
        
        for(int i=0; i < priorities.length; i++){
            if(i == location){
                q.add(new int[]{priorities[i], 1});
            } else {
                q.add(new int[]{priorities[i], 0});
            }
        }
        
        int idx = sorts.length-1;
        int turn = 1;
        while(!q.isEmpty()){
            if(q.peek()[0] == sorts[idx]){
                idx--;
                int[] value = q.poll();
                if(value[1] == 1){
                    return turn;
                }
                turn++;
                q.add(value);
            } else {
                q.add(q.poll());
            }
        }
        return 0;
    }
}