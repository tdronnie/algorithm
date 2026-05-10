class Solution {
    static int count = 0; // 방법의 수
    public int solution(int[] numbers, int target) {
        
        // 주어진 숫자를 넣거나 빼거나 해서 타겟 숫자 만들기
        makeNumber(0, 0, numbers, target);
        return count;
    }
    
    public void makeNumber(int sum, int idx, int[] numbers, int target){
        if(idx == numbers.length){
            if(sum == target){
                count++;
            }
            return;
        }
        
        makeNumber(sum + numbers[idx], idx + 1, numbers, target);
        makeNumber(sum - numbers[idx], idx + 1, numbers, target);
    }
}