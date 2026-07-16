class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        
        long distance = 0;
        int dnow = n-1;
        int pnow = n-1;
        
        // 먼 곳부터 시작해서 한 턴에 최대한 많은 상자 처리
        while(dnow >= 0 || pnow >= 0){
            
            while(dnow >= 0 && deliveries[dnow] == 0){
                dnow--;
            }
            while(pnow >= 0 && pickups[pnow] == 0){
                pnow--;
            }
            if(dnow < 0 && pnow < 0) break;
            
            // 배달, 수거 중 더 먼거리만큼 진행
            int reach = Math.max(dnow, pnow) + 1;
            int dCount = cap;
            int pCount = cap;
            
            // 가면서 택배 배송
            while(dnow >= 0 && dCount > 0){
                if(dCount >= deliveries[dnow]){
                    dCount -= deliveries[dnow];
                    deliveries[dnow] = 0;
                } else {
                    deliveries[dnow] -= dCount;
                    dCount = 0;
                    break;
                }
                dnow--;
            }
            
            // 오면서 택배 수거
            while(pnow >= 0 && pCount > 0){
                if(pCount >= pickups[pnow]){
                    pCount -= pickups[pnow];
                    pickups[pnow] = 0;
                } else {
                    pickups[pnow] -= pCount;
                    pCount = 0;
                    break;
                }
                pnow--;
            }
            
            distance += reach*2;         
            
        }
        
        return distance;
    }
}