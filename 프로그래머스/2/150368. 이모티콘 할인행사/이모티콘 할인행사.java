import java.util.*;
class Solution {
    
    int[] maxPurpose;
    public int[] solution(int[][] users, int[] emoticons) {
        // 10, 20, 30, 40 조합
        
        maxPurpose = new int[]{0, 0};
        int[] rates = {10, 20, 30, 40};
        int[] kinds = new int[emoticons.length];
        findRates(emoticons, users, rates, kinds, 0);
        
        return maxPurpose;
        
    }
    
    public void findRates(int[] es, int[][] users, int[] rates, int[] kinds, int idx){
        if(idx == es.length){
            int subAmount = 0;
            int salesAmount = 0;
            
            for(int[] u : users){
                int total = 0;
                for(int i=0; i<es.length; i++){
                    if(kinds[i] >= u[0]){
                        total += es[i] * (100 - kinds[i]) / 100;
                    }
                }
                
                if(total >= u[1]){
                    subAmount++;
                } else {
                    salesAmount += total;
                }
            }
            
            if(maxPurpose[0] > subAmount) return;
            if(maxPurpose[0] == subAmount && maxPurpose[1] >= salesAmount) return;
            maxPurpose[0] = subAmount;
            maxPurpose[1] = salesAmount;
            return;
        }
        
        for(int r : rates){
            kinds[idx] = r;
            findRates(es, users, rates, kinds, idx + 1);
        }
    }
}