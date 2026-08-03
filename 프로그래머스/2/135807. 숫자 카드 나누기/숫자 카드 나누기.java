class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        
        // 각 묶음 최대공약수, 서로 요소 나눌 수 있는지 확인, max값 찾기
        
        int a = arrayA[0];
        for(int i=1; i<arrayA.length; i++){
            int b = arrayA[i];
            
            while(b != 0){
                int n = a % b;
                a = b;
                b = n;
            }
        }
        
        int resultA = a;
        
        a = arrayB[0];
        for(int i=1; i<arrayB.length; i++){
            int b = arrayB[i];
            
            while(b != 0){
                int n = a % b;
                a = b;
                b = n;
            }
        }
        
        int resultB = a;
        
        boolean bPossible = true;
        boolean aPossible = true;
        
        if(resultB != 1){
            for(int i = 0; i <arrayA.length; i++){
                if(arrayA[i] % resultB == 0) aPossible = false;
            }
        } else {
            aPossible = false;
        }
        
        if(resultA != 1){
            for(int i = 0; i <arrayB.length; i++){
                if(arrayB[i] % resultA == 0) bPossible = false;
            }
        } else {
            bPossible = false;
        }
        
        if(aPossible && bPossible) return Math.max(resultA, resultB);
        if(aPossible) return resultB;
        if(bPossible) return resultA;
        return 0;
        
    }
}