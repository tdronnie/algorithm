import java.util.*;
class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        
        int AIndex = 0;
        int BIndex = 0;
        int count = 0;
        while(BIndex < B.length){
            while(BIndex < B.length && A[AIndex] >= B[BIndex]){
                BIndex++;
            }
            if(BIndex < B.length && A[AIndex] < B[BIndex]){
                count++;
            }
            AIndex++;
            BIndex++;
        }
        
        return count;
        
    }
}