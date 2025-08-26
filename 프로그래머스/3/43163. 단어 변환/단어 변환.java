import java.util.*;
class Solution {
    static int min = Integer.MAX_VALUE;
    public int solution(String begin, String target, String[] words) {
        ArrayList<String>[] list = new ArrayList[words.length + 2];
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        Set<String> visited = new HashSet<>();
        boolean flag = false;
        
        map.put(begin, new ArrayList<>());
        for(int i=0; i<words.length; i++){
            map.put(words[i], new ArrayList<>());
            if(!flag && words[i].equals(target)){
                flag = true;
            }
        }
        if(!flag){
            return 0;
        }
        
        for(String word : map.keySet()){
            for(String next : map.keySet()){
                if(word.equals(next)) continue;
                if(diffCount(word, next)){
                    map.get(word).add(next);
                }
            }
        }
        
        
        
//         for(String word : map.keySet()){
            
//             Set<Character> chs = new HashSet<>();
//             for(char ch : word.toCharArray()){
//                 chs.add(ch);
//             }
            
//             for(String next : map.keySet()){
//                 Set<Character> union = new HashSet<>(chs);
//                 Set<Character> common = new HashSet<>(chs);
//                 Set<Character> nexts = new HashSet<>();
                
//                 if(word.equals(next)) continue;
//                 for(char ch : next.toCharArray()){
//                     nexts.add(ch);
//                 }
                
//                 union.addAll(nexts);
//                 common.retainAll(nexts);

//                 if(word.length() - common.size() <= 1){
//                     map.get(word).add(next);
//                 }
//             }
//         }
        
        findShortRoute(begin, target, map, visited, 0);
        
        if(min == Integer.MAX_VALUE){
            return 0;
        }
        return min;
    }
    
    void findShortRoute(String word, String target, HashMap<String, ArrayList<String>> map, Set<String> visited, int count){
        
        if(visited.contains(word)) return;
        visited.add(word);        
        
        if(word.equals(target)){
            min = Math.min(min, count);
            return;
        }
        for(String next : map.get(word)){
            findShortRoute(next, target, map, visited, count + 1);
        }
    }
    
    boolean diffCount(String s1, String s2){
        int diffCount = 0;
        for(int i=0; i<s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i)){
                diffCount++;
                if(diffCount > 1) return false;
            }
        }
        
        return true;
    }
}