class Solution {
    public int solution(int[][] triangle) {
        
        int lev = triangle.length;
        int maxWidth = triangle[lev-1].length;
        int[][] maxTotal = new int[lev][maxWidth];
        
        for(int i=lev-1; i>=0; i--){
            for(int j=0; j<triangle[i].length; j++){
                if(i == lev-1){
                    maxTotal[i][j] = triangle[i][j];
                } else {
                    maxTotal[i][j] = Math.max(maxTotal[i+1][j], maxTotal[i+1][j+1]) + triangle[i][j];
                }
            }
        }
        
        return maxTotal[0][0];
    }
}