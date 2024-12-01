import java.util.*;
class Solution {
    public int solution(int[] elements) {
        TreeSet<Integer> ts = new TreeSet<>();
        for(int len=1; len <= elements.length; len++){
            for(int i=0; i < elements.length; i++){
                int sum = 0;
                int count = 0;
                for(int idx = i; count <= len ; idx++){
                    if(count == len) break;
                    if(idx >= elements.length){
                        idx = 0;
                    }
                    sum += elements[idx];
                    count++;
                }
                ts.add(sum);
            }
        }
        return ts.size();
    }
}