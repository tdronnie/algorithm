import java.util.*;
class Solution {
    
    static Map<String, Integer> wordCount = new HashMap<>();
    public String[] solution(String[] orders, int[] course) {
        
        //orders의 문자열이 사전순으로 주어지지 않을 수 도 있다.
        //먼저 각 문자열 정렬 필요
        int i=0;
        for(String str : orders){
            char[] alphabets = str.toCharArray();
            Arrays.sort(alphabets);
            orders[i++] = new String(alphabets);
        }
        
        for(int length : course){
            for(String order : orders){
                combination("", order, length, 0);
            }
        }
        
        List<String> result = new ArrayList<>();
        for(int length : course){
            int maxOrderCount = 0;
            for(String word : wordCount.keySet()){
                if(word.length() == length){
                    maxOrderCount = Math.max(maxOrderCount, wordCount.get(word));
                }
            }
            
            if(maxOrderCount >= 2){ //2번 이상 주문한 것만 가능
                for(String word : wordCount.keySet()){
                    if(word.length() == length && wordCount.get(word) == maxOrderCount){
                        result.add(word);
                    }
                }    
            }
       }
        Collections.sort(result);
        return result.toArray(new String[result.size()]);
    }
    
    public static void combination(String word, String originWord, int length, int start) {
        if (word.length() == length){
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            return;
        }
        
        for(int i=start; i < originWord.length(); i++){
            combination(word + originWord.charAt(i), originWord, length, i + 1);
        }
    }
}