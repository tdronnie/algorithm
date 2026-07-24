class Solution {
    public int[] solution(long[] numbers) {
        // 마지막 레벨까지 꽉 차있는게 포화 이진트리
        // 포화이진트리애서 더미면 0추가 더미 아니라면 1추가
        int[] answer = new int[numbers.length];
        
        for(int i=0; i<numbers.length; i++){
            String str = Long.toBinaryString(numbers[i]);
            
            // 포화 이진트리의 노드 수 맞춰주기
            int n = 2;
            while(str.length() > n-1){
                n*=2;
            }
            int plus = n - 1 - str.length();
            for(int c=0; c<plus; c++){
                str = '0' + str;
            }
            // System.out.println(str);
            if(isPossible(str, 0, str.length())){
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }
        
        return answer;
        
    }
    
    public boolean isPossible(String part, int start, int end){
        // 각 서브트리가 더미포함 포화이진트리를 만족하는지 확인
        if(start >= end) return true;
        
        int mid = (start + end) / 2;

        // 부모가 더미노드인데 자식 중 더미노드 아닌 노드 있으면 표현 불가
        if(part.charAt(mid) == '0'){
            if(part.substring(start, mid).contains("1") ||
               part.substring(mid+1, end).contains("1")){
                return false;
            }
        }
        
        if(!isPossible(part, start, mid)) return false;
        if(!isPossible(part, mid+1, end)) return false;
        return true;
    }
}