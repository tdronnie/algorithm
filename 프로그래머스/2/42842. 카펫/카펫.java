class Solution {
    public int[] solution(int brown, int yellow) {
        int area = brown + yellow;
        
        //가로 세로 임의로 구하기
        for(int i=3; i<=area; i++){
            int width = area/i;
            //노랑 면적은 (width-2) * (i-2)
            if((width-2)*(i-2) == yellow){
                return new int[]{width, i};
            }
        }
        return new int[]{0,0};
    }
}