class Solution {
    public int[] solution(int brown, int yellow) {
        int area = brown + yellow; // 카펫의 면적
        
        //세로 = i(최소 3), 가로 area/i로 두고 작은 면적부터 대입
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