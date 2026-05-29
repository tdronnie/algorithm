import java.util.*;
class Solution {
    
    List<Set<Integer>> used;
    public int solution(int N, int number) {
               
        if(N == number) return 1;
        used = new ArrayList<>(); // 인덱싀: 사용횟수, set: 연산결과
        
        for(int i=0; i<=8; i++){
            used.add(new HashSet<>());
        }
        used.get(1).add(N); // 1개 사용했을 때 N 초기화
        
        for(int i=2; i<=8; i++){
            
            Set<Integer> curr = used.get(i);
            
            // N 나열
            StringBuilder sb = new StringBuilder();
            for(int j=0; j<i; j++){
                sb.append(N);
            }
            curr.add(Integer.parseInt(sb.toString()));
            
            // 이전 사용 set들 조합 -> 4번째 = 1-3, 2-2, 3-1
            for(int p1 = 1; p1 < i; p1++){
                int p2 = i-p1;
                
                Set<Integer> part1 = used.get(p1);
                Set<Integer> part2 = used.get(p2);

                for(int num1 : part1){
                    for(int num2 : part2){
                        
                        curr.add(num1 + num2);    
                        curr.add(num1 - num2);    
                        curr.add(num1 - num2);    
                        if(num2 > 0){
                            curr.add(num1 / num2);    
                        }
                        curr.add(num1 * num2);    
                    }
                }
            
                // 한 조합 끝날 때마다 number나왔는지 확인
                if(curr.contains(number)){
                    return i;
                }
            }
        }
        
        return -1;
               
    }
}