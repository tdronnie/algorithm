import java.util.*;
class Solution {
    static int height;
    static int[][] maxValue;
    public int solution(int[][] triangle) {
        
        
        int height = triangle.length;
        for(int i=1; i<height; i++){
            for(int j=0; j<=i; j++){
                if(j == 0){
                    triangle[i][j] += triangle[i-1][0];
                } else if(j == i){
                    triangle[i][j] += triangle[i-1][j-1];
                } else{
                    triangle[i][j] += Math.max(triangle[i-1][j-1], triangle[i-1][j]);    
                }
                
            }
        }
        
        int max = -1;
        for(int i=0; i<height; i++){
            max = Math.max(max, triangle[height-1][i]);
        }
        
        return max;
        
    }
}