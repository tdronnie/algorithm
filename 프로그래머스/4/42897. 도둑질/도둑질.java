class Solution {
    public int solution(int[] money) {
        
        // 인덱스 0 노드 포함
        int[] max = new int[money.length]; // 인덱스 i까지의 최댓값
        max[0] = money[0];
        max[1] = money[0];
        
        for(int i=2; i<money.length-1; i++){
            max[i] = Math.max(max[i-1], max[i-2] + money[i]);
        }
        int case1 = max[money.length-2];
        
        // 인덱스 0 노드 포함하지 않음
        max = new int[money.length];
        max[0] = 0;
        max[1] = money[1];
        
        for(int i=2; i<money.length; i++){
            max[i] = Math.max(max[i-1], max[i-2] + money[i]);
        }
        int case2 = max[money.length-1];
        
        return Math.max(case1, case2);
    }
}