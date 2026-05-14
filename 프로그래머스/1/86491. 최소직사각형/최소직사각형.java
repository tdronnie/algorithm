class Solution {
    public int solution(int[][] sizes) {
        
        int maxWidth = 0;
        int maxHeight = 0;
        
        // 명함의 긴 길이가 땅에 닿도록 ㅡ, 짧은 길이가 세워지도록 ㅣ
        for(int[] card : sizes){
            if(card[0] >= card[1]){
                maxWidth = Math.max(maxWidth, card[0]);
                maxHeight = Math.max(maxHeight, card[1]);
            } else {
                maxWidth = Math.max(maxWidth, card[1]);
                maxHeight = Math.max(maxHeight, card[0]);
            }
        }
        
        return maxWidth * maxHeight; // 효율적인 지갑 크기 리턴
        
        
    }
}