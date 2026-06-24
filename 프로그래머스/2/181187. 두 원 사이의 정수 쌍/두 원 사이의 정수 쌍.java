class Solution {
    public long solution(int r1, int r2) {
        
        long count = 0;
        long bigY = 0;
        long smallY = 0;
        
        // x좌표에 대해 큰원 y최대, 작은원 y최소 찾기
        for(int x = -r2; x<=r2; x++){
            bigY = (long)Math.sqrt((long)r2*r2 - (long)x*x);
            
            smallY = 0;
            if(Math.abs(x) < r1){
                smallY = (long)Math.ceil(Math.sqrt((long)r1*r1 - (long)x*x));    
            }
            
            // -bigY ~ -smallY, smallY ~ bigY, 도넛의 위아래
            if(bigY >= smallY){
                count += (bigY - smallY + 1) * 2;
            }
            
            // 작은원 y값 0이면 두번 카운트, 하나 빼기
            if(smallY == 0){
                count--;
            }
        }
        
        return count;
        
    }
}