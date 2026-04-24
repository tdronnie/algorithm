import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        Deque<Integer> dq = new ArrayDeque<>(); // arr 원소 순서를 유지하며 원소를 추가하기 위한 큐
    
        for(int num : arr){
            if(dq.isEmpty() || dq.peekLast() != num){
                dq.addLast(num);
            }
        }
        
        int uniqueSize = dq.size(); // 중복 제거 후 남은 원소 개수
        int[] result = new int[uniqueSize];
        for(int i=0; i < uniqueSize; i++){
            int s = dq.pollFirst();
            result[i] = s;
        }
        
        return result;
    }
}