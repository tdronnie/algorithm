class Solution {
    
    int count = 0;
    public int solution(int[] numbers, int target) {
        
        // 타겟까지 남은 양 구하기 위한 numbers 합 구하기
        int total = 0;
        for(int num : numbers){
            total += num;
        }
            
        makeTarget(numbers, 0, 0, total, target);
        return count;
    }
    
    public void makeTarget(int[] numbers, int idx, int current, int remain, int target){
        
        // 남은 숫자를 조합해도 target 만들지 못하는 경우
        if(Math.abs(target-current) > remain){
            return;
        }
        
        // 모든 숫자 사용했다면 target이 되었는지 확인
        if(idx == numbers.length){ 
            if(current == target){
                count++;
            }
            return;
        }
        
        // 인덱스 순으로 더하거나 빼거나
        makeTarget(numbers, idx + 1, current + numbers[idx], remain - numbers[idx], target);
        makeTarget(numbers, idx + 1, current - numbers[idx], remain - numbers[idx], target);
    }
}