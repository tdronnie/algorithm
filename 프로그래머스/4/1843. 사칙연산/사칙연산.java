import java.util.*;
class Solution {
    Integer[][][] memo;
    public int solution(String arr[]) {
        
        memo = new Integer[arr.length][arr.length][2];

        return makeMax(arr, 0, arr.length-1, true);
        
    }
    
    public int makeMax(String[] arr, int start, int end, boolean isMaxTurn){
        
        if(start == end){
            return Integer.parseInt(arr[start]);
        }
        
        int partSum = isMaxTurn ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int turnIdx = isMaxTurn ? 1 : 0;
        if(memo[start][end][turnIdx] != null) {
            return memo[start][end][turnIdx];
        }
        
        // 각 연산자 마다 양쪽 수식의 최댓값, 최솟값 찾기
        for(int i=start+1; i<=end-1; i+=2){
            
            // 왼쪽
            int lMax = makeMax(arr, start, i-1, true);
            int lMin = makeMax(arr, start, i-1, false);
            
            // 오른쪽
            int rMax = makeMax(arr, i+1, end, true);
            int rMin = makeMax(arr, i+1, end, false);
            
            // 현재 연산자 기준으로 결과가 최대가 될 수 있도록 조작
            if(isMaxTurn){
                if(arr[i].equals("+")){
                    partSum = Math.max(partSum, lMax + rMax);
                } else {
                    partSum = Math.max(partSum, lMax - rMin);
                }
            } else {
                if (arr[i].equals("+")){
                    partSum = Math.min(partSum, lMin + rMin);
                } else {
                    partSum = Math.min(partSum, lMin - rMax);
                }
            }            
        }
        return memo[start][end][turnIdx] = partSum;
        
    }
}