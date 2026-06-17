import java.util.*;
class Solution {
    
    List<int[]> list = new ArrayList<>();
    public int solution(int n, int[][] q, int[] ans) {
        
        // 1~n까지 모든 정수로 5개 정수 만들기
        int count = 0;

        makeNumber(1, 0, n, new int[5]);
        
        for(int[] numbers : list){
            int correct = 0;
            for(int i=0; i<q.length; i++){
                int same = 0;
                for(int j=0; j<5; j++){
                    for(int k=0; k<5; k++){
                        if(numbers[j] == q[i][k]){
                            same++;
                            break;
                        }
                    }
                }
                if(same == ans[i]){
                    correct++;
                }
            }
            if(correct == q.length){
                count++;
            }
        }
        
        return count;
        
    }
    
    public void makeNumber(int num, int count, int n, int[] numbers){
        if(count == 5){
            list.add(numbers.clone());
            return;
        }
        
        for(int i=num; i<=n; i++){
            numbers[count] = i;
            makeNumber(i + 1, count + 1, n, numbers);
        }
    }
}