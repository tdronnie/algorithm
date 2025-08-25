import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        
        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i=0; i<operations.length; i++){
            StringTokenizer st = new StringTokenizer(operations[i]);
            String op = st.nextToken();
            int num = Integer.parseInt(st.nextToken());
            
            if(op.equals("I")){
                minQ.add(num);
                maxQ.add(num);
            } else if(op.equals("D")){
                if(minQ.isEmpty()) continue;

                if(num == -1){
                    int min = minQ.poll();
                    maxQ.remove(min);
                } else {
                    int max = maxQ.poll();
                    minQ.remove(max);
                }
            }            
            
        }

        if(minQ.isEmpty()){
            return new int[]{0, 0};
        }
        return new int[]{maxQ.peek(), minQ.peek()};
        
    }
}