class Solution {
    
    int count = 0;
    public int solution(int[] numbers, int target) {
        
        makeTarget(numbers, 0, 0, target);
        return count;
    }
    
    public void makeTarget(int[] numbers, int idx, int current, int target){
        
        if(idx == numbers.length){ // 모든 숫자 사용했다면 target이 되었는지 확인
            if(current == target){
                count++;
            }
            return;
        }
        
        makeTarget(numbers, idx + 1, current + numbers[idx], target);
        makeTarget(numbers, idx + 1, current - numbers[idx], target);
    }
}