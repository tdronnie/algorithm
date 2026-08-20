import java.util.*;
class Solution {
    public double[] solution(int k, int[][] ranges) {
        
        List<Integer> pin = new ArrayList<>();
        pin.add(k);
        while(k > 1){
            k = (k % 2 == 0) ? k/2 : k * 3 + 1;
            pin.add(k);
        }
        
        int n = pin.size() - 1; // 우박수열이 1이 될 때까지의 구간 수
        double[] area = new double[n];
        
        // 길이 1 씩 사다리꼴 면적구하기
        for(int i=0; i<n; i++){
            area[i] = (pin.get(i) + pin.get(i+1)) / 2.0;
        }
        
        double[] total_area = new double[n + 1]; // 누적합
        double[] answer = new double[ranges.length];
        
        for(int i=0; i<n; i++){            
            total_area[i+1] = total_area[i] + area[i];
        }
        
        for(int i=0; i<ranges.length; i++){
            int start = ranges[i][0];
            int end = ranges[i][1] + n;
            
            if(start > end){
                answer[i] = -1.0;
            } else {
                answer[i] = total_area[end] - total_area[start];
            }
        }
        
        return answer;

    }
}