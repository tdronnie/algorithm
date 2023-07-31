import java.util.*;
class Solution {
    public String[] solution(String my_str, int n) {
        
        List<String> list = new ArrayList<>();//부분문자열 저장하기 위한 리스트
        String sub =""; //부분 문자열 초기화
        for(int i=0; i<my_str.length(); i++){
            if(i+n<my_str.length()){ //길이 n의 부분문자열 길이가 my_str의 길이에 넘어가지 않는경우
                sub=my_str.substring(i, i+n); //부분문자열 생성
                list.add(sub); //리스트에 더하기
                i=i+n-1; //인덱스 옮겨주기
            }
            else{//길이 n의 부분문자열 길이가 my_str의 길이에 넘어가는 경우, 마지막 실행
                sub=my_str.substring(i, my_str.length()); //부분문자열 생성
                list.add(sub); //리스트에 더하기
                break;
            }
        }
        String[] rslt = list.toArray(new String[list.size()]);
        return rslt;
        
    }
        
}