class Solution {
    int count=0;
    public int solution(int[] numbers, int target) {
        dfs(numbers, 0, target, 0);
        return count;
        
    }
    public void dfs(int[] numbers, int idx, int target, int sum){
        if(idx == numbers.length){
            if(sum == target) count++;
            return;
        }
        
        dfs(numbers, idx+1, target, sum - numbers[idx]);
        dfs(numbers, idx+1, target, sum + numbers[idx]);
    }
}