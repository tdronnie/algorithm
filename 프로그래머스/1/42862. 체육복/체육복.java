import java.util.*;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
    
        int[] students = new int[n+1];
        int count = 0;
        Arrays.fill(students, 1);
        
        for(int i=0; i<lost.length; i++){
            students[lost[i]]--;
        }
        
        for(int i=0; i<reserve.length; i++){
            students[reserve[i]]++;
        }
        
        // 여벌 옷 가지는 학생 앞 or 뒤 나눠주기
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n;j++){
                System.out.print(students[j] + " ");    
            }
            System.out.println();
            
            if(students[i] == 2){
                if(i == 1){
                    if(students[i+1] == 0){
                        students[i]--;
                        students[i+1]++;
                        continue;
                    }
                }
                if (i == n){
                    if(students[i-1] == 0){
                        students[i]--;
                        students[i-1]++;
                        continue;
                    }
                }
                else {
                   if(students[i-1] == 0){
                        students[i]--;
                        students[i-1]++;
                        continue;
                    }
                    if(students[i+1] == 0){
                        students[i]--;
                        students[i+1]++;
                    } 
                }
                
            }
        }
        
        for(int i=1; i<=n; i++){
            if(students[i] != 0){
                count++;    
            }
        }
        
        return count;
    }
}