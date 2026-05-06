import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        
        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(Comparator.reverseOrder());
        
        for(String op : operations){
            String[] chs = op.split(" ");
            if(chs[0].equals("I")){
                int num = Integer.parseInt(chs[1]);
                minQ.add(num);
                maxQ.add(num);
            } else {
                if(minQ.isEmpty()) continue; // 큐가 빈 경우 넘어가기
                
                if(chs[1].equals("1")){
                    minQ.remove(maxQ.poll());
                } else {
                    maxQ.remove(minQ.poll());
                }
            }
        }
        
        if(minQ.isEmpty()) {
            return new int[]{0, 0};
        } else {
            return new int[]{maxQ.poll(), minQ.poll()};
        }
    }
}