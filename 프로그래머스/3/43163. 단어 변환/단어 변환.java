import java.util.*;
class Word {
    String word;
    int count = 0; // 거치는 횟수 카운트
    
    public Word(String word, int count){
        this.word = word;
        this.count = count;
    }
}
class Solution {
    
    List<String>[] arr;
    public int solution(String begin, String target, String[] words) {
        
        Map<String, ArrayList<String>> map = new HashMap<>(); // <String, ArrayList<String>> 저장 map
        Queue<Word> q = new ArrayDeque<>(); // bfs
        Set<String> visited = new HashSet<>(); // 방문처리 set
        
        
        // begin 포함 단어 관계 저장
        String[] all = Arrays.copyOf(words, words.length + 1);
        all[words.length] = begin;

        for(int i=0; i<all.length - 1; i++){
            for(int j=i+1; j <all.length; j++){
                // 한글자만 다른 경우 관계 잇기
                if(canMove(all[i], all[j])){
                    map.computeIfAbsent(all[i], k -> new ArrayList<>()).add(all[j]);
                    map.computeIfAbsent(all[j], k -> new ArrayList<>()).add(all[i]);
                }
            }
        }
        
        q.add(new Word(begin, 0));
        visited.add(begin);
        while(!q.isEmpty()){
            Word current = q.poll();
            
            // target 변환 완료
            if(current.word.equals(target)){
                return current.count;
            }
            
            for(String next : map.get(current.word)){
                if(visited.contains(next)) continue;
                visited.add(next);
                q.add(new Word(next, current.count + 1));
            }
        }
        
        // 변환 불가
        return 0;
         
    }
    // 두 문자열이 한글자만 다른 경우 true 리턴
    public boolean canMove(String s1, String s2){
        int count = 0;
        for(int i=0; i<s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i)) {
                count++;
                if(count > 1) return false;
            }
        }
        return true;
    }
}