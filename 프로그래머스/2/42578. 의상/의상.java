import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> kinds = new HashMap<>(); // 중복 의상 없으므로 단순 개수로 세기
        int value = 1; // 종류 경우의 수를 위한 변수
        
        // 종류에 따른 의상 개수 저장
        for(String[] pair  : clothes){
            kinds.put(pair[1], kinds.getOrDefault(pair[1], 0) + 1);
        }
        
        // 종류가 한가지인 경우
        if(kinds.size() == 1) {
            return clothes.length; // 각 의상 수
        }
        
        for(int count : kinds.values()){
            value *= (count + 1); // 종류 의상 입지 않는 경우까지 + 1해서 모든 경우의 수 구해주기
        }
        return value - 1; // 모든 의상 안입는 1가지 제외
    }
}