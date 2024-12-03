import java.util.*;
class Solution {
    public int solution(int cacheSize, String[] cities) {
        if(cacheSize == 0){
            return cities.length * 5;
        }
        
        ArrayDeque<String> deque = new ArrayDeque<>();
        int time = 0;
        
        for(int i=0; i<cities.length; i++){
            String input = cities[i].toLowerCase();
            if(deque.isEmpty()){
                deque.addFirst(input);
                time += 5;
                continue;
            }
            if(deque.contains(input)){
                time++;
                deque.remove(input);
                deque.addFirst(input);
                continue;
            }
            if(deque.size() >= cacheSize){
                deque.pollLast();
                deque.addFirst(input);
            } else {
                deque.addFirst(input);
            }
            time += 5;
        }
        return time;
    }
}