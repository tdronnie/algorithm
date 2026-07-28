import java.util.*;
class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if(a[col-1] == b[col-1]){
                return b[0] - a[0];
            }
            return a[col-1] - b[col-1];
        });
        
        for(int[] d : data){
            pq.add(d);
        }
        
        int value = 0;
        int i=1;
        
        while(row_begin > i){
            int[] q = pq.poll();
            i++;
        }
        
        for(;row_begin <= i && i <= row_end; i++){
            int[] tuple = pq.poll();
            int sum = 0;
            for(int d : tuple){
                sum += d%i;  
            }
            value ^= sum;
        }
        
        return value;
    }
}