import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        
        // 단순히 큰 수를 먼저 쓰면 x, 이어 붙였을 때 큰 수가 되도록 정렬
        // int형은 Comparator 사용 불가, 문자열로 변환
        String[] numStr = new String[numbers.length];
        for(int i=0; i<numbers.length; i++){
            numStr[i] = String.valueOf(numbers[i]);
        }
        
        // 두 수를 이어붙였을 때 큰 순서로 정렬
        Arrays.sort(numStr, (a, b) -> (b + a).compareTo(a + b));
        
        // 0만 여러개 들어온경우, "000" -> "0" 처리
        if(numStr[0].equals("0")){
            return "0";
        }
        
        StringBuilder sb = new StringBuilder();
        for(String number : numStr){
            sb.append(number);
        }
        
        return sb.toString();
    }
}