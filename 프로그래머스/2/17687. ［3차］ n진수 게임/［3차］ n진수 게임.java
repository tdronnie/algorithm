class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        String numberStr = "";
        int num = 0;
        
        while(numberStr.length() < t * m){ //최소 튜브 턴에서 t개 확보되어야 하므로 인원 * 턴만큼은 확보해놔야 함
            numberStr += (Integer.toString(num, n).toUpperCase());
            num++;
        }
        
        for(int i = p-1; i<t * m; i+=m){
            answer += numberStr.charAt(i);
        }
        
        return answer;
    }
}