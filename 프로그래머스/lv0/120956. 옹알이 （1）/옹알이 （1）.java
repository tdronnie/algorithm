class Solution {
    public int solution(String[] babbling) throws NumberFormatException {
        int rslt=0;
        for(int i=0; i<babbling.length; i++){
            //옹알이 배열의 각요소에 포함되는 발음가능 옹알이를 "0"으로 대체
            String word = babbling[i].replace("aya", "0")
                .replace("ye", "0")
                .replace("woo","0")
                .replace("ma","0");
            try{
                Integer.parseInt(word); //대체 결과값이 숫자로만 이루어진다면 단어의 구성이 발음가능한 옹알이 밖에 없음
                rslt++;
            }catch(NumberFormatException e){
                continue; //발음 불가한 것이 있는 경우
            }
        }
        return rslt;
    }
}