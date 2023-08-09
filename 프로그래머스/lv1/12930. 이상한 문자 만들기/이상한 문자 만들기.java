class Solution {
    public String solution(String s) {
        //공백기준으로 자르기, 각 단어 인덱스 별로 변환
        String[] str = s.split(" ", -1); //끝 공백포함
        StringBuilder sb = new StringBuilder(); //정답 문자열 저장위한 sb
        
        for(String ss : str){
            System.out.println("->"+ ss);
        }
        for(int i=0; i<str.length; i++){
            for(int j=0; j<str[i].length(); j++){
                String sub = str[i].substring(j,j+1);//한글자
                if(j%2==0){ //짝수인 경우
                    sb.append(sub.toUpperCase());
                }
                else{ //홀수인 경우
                    sb.append(sub.toLowerCase());
                }
                
            }
            if(i < str.length-1) //마지막 인덱스 이전까지만 공백문자 넣기
              sb.append(" ");  
        }
        return sb.toString();
    }
}