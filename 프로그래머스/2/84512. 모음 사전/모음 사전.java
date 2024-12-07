import java.util.*;
class Solution {
    
    private final String VOWELS = "AEIOU";
    
    public int solution(String word) {
        List<String> words = new ArrayList<>();
        
        dfs(0, "", words);
        
        return words.indexOf(word) + 1;
    }
    
    public void dfs(int wordCount, String tempWord, List<String> words){
        if(wordCount == 5){
            return;
        }
        for(int i=0; i<5; i++){
            String newTempWord = tempWord + VOWELS.charAt(i);
            words.add(newTempWord);
            dfs(wordCount+1, newTempWord, words);
        }
    }
}