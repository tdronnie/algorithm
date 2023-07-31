class Solution {
    public int solution(String t, String p) {
        int cnt = 0; //등장 횟수
        //인덱스 0부터 t길이-p까지 탐색
        //숫자 문자열 길이가 최대 10000이 가능하므로 int로 치환해서 비교하는 것은 불가
        int pLen = p.length();
        for(int i=0; i<=t.length()-pLen; i++){
            String subStr = t.substring(i, i+pLen); //p와 길이가 같은 부분문자열 subStr, t의 인덱스 0부터 차례로 생성
            int j=0;
            for(; j<pLen; j++){
                //subStr이 p와의 같은 자릿수가 작다면
               if(p.charAt(j)-'0' > subStr.charAt(j)-'0'){
                   //카운트 해주고 바로 끝내기
                   cnt++;
                   break;
               }
                //subStr이 p와의 같은 자릿수가 크다면
              else if(p.charAt(j)-'0' < subStr.charAt(j)-'0')
                  break; //끝내고 다음 부분문자열로 넘어가기
            }
            if(j==pLen){ // 모든 자리수가 같은 경우
                cnt++;
            }
        }
        return cnt;
    }
}