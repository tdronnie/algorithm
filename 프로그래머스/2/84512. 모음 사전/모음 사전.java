import java.util.*;
class Solution {
    
    private final String VOWELS = "AEIOU";
    static List<String> words;
    
    public int solution(String word) {
        words = new ArrayList<>();
        
        dfs(0, ""); // 사전 만들기
        
        return words.indexOf(word) + 1;
    }
    
    public void dfs(int wordCount, String tempWord){
        if(wordCount == 5){
            return;
        }
        
        // 사전순으로 알파벳 하나씩 더하면서 모든 경우의 수 저장
        for(int i=0; i<5; i++){
            String newTempWord = tempWord + VOWELS.charAt(i);
            words.add(newTempWord);
            dfs(wordCount+1, newTempWord);
        }
    }
}