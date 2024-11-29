import java.util.*;
class Solution {
    public int[] solution(int n, String[] words) {
        
        HashMap<String, Boolean> map = new HashMap<>();
        int failNum = 0;
        int turn = 0;
        int i=1;
        map.put(words[0], true);
        for(; i<words.length; i++){
            int preLen = words[i-1].length();
            char curr = words[i].charAt(0);
            char pre = words[i-1].charAt(preLen-1);
            if(curr != pre || map.containsKey(words[i])){
                failNum = i % n + 1;
                turn = i/n + 1;
                break;
            }
            map.put(words[i], true);
        }
        
        return new int[]{failNum, turn};
    }
}