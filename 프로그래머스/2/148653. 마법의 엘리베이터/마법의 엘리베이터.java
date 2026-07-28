class Solution {
    int minUsed = Integer.MAX_VALUE;
    public int solution(int storey) {
        
        useStone(storey, 0);
        
        return minUsed;
    }
    
    public void useStone(int remain, int count){
        
        if(count > minUsed){
            return;
        }
        
        if(remain == 0){
            minUsed = Math.min(minUsed, count);
            return;
        }
        
        int now = remain % 10;
        int next = remain / 10;
        
        useStone(next, count + now); // 현재 자릿수만큼 돌 사용
        useStone(next + 1, count + (10 - now)); // 현재 자릿수 다음에서 돌 하나 더 사용
    }
}