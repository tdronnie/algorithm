class Solution {
    public int solution(int n) {
        int count = 0;
        for(int i=1; i<= n; i++){
            int tmp = 0;
            for(int j=i; tmp<=n; j++){
                tmp += j;
                if(tmp == n){
                    count++;
                    break;
                }
            }
        }
        return count;
    }
}