import java.util.*;
class Solution {
    
    public int[] solution(int target) {
        // 1. 최소 횟수로 던지기 2. 싱글 + 불 최대 횟수로 던지기
        // kinds, 점수에 대해 인덱스0: 최소 던진횟수, 인덱스1: 싱글이나 불 카운트
        int[][] kinds = new int[100001][2];
        // 특정 단계에서 바로 이전 단계 알기위해 한번 던져서 얻을 수 있는 모든 경우 저장
        List<int[]> arr = new ArrayList<>();
        for(int i=1; i<=target; i++){
            kinds[i][0] = Integer.MAX_VALUE;
        }
        
        // 싱글, 더블, 트리플, 불
        for(int i=1; i<=target && i<=20; i++){
            kinds[i][0] = 1;
            kinds[i][1] = 1;
            arr.add(new int[]{i, 1});
        }
        
        for(int i=2; i<=target && i<=40; i+=2){
            kinds[i][0] = 1;
            kinds[i][1] = 0;
            arr.add(new int[]{i, 0});
        }
        
        for(int i=3; i<=target && i<=60; i+=3){
            kinds[i][0] = 1;
            kinds[i][1] = 0;
            arr.add(new int[]{i, 0});
        }
        
        kinds[50][0] = 1;
        kinds[50][1] = 1;
        arr.add(new int[]{50, 1});
        
        for(int i=1; i<=target; i++){
            // 현재 수 i 이전 값이 될 수 있는 후보들을 가지고 값 업데이트, 
            for(int[] pair : arr){
                if(i - pair[0] >= 0 && kinds[i-pair[0]][0] != Integer.MAX_VALUE){
                    int nextCount = kinds[i-pair[0]][0] + 1;
                    int nextKinds = kinds[i-pair[0]][1] + pair[1];
                    
                    // 현재 수에 대해서 더 적은 던진횟수이거나, 더 적은던진횟수이면서 싱글이나 불 수가 크다면 업데이트
                    if(kinds[i][0] > nextCount){
                        kinds[i][0] = nextCount;
                        kinds[i][1] = nextKinds;
                    }
                    
                    if(kinds[i][0] == nextCount && kinds[i][1] < nextKinds){
                        kinds[i][0] = nextCount;
                        kinds[i][1] = nextKinds;
                    }
                    
                } 
            }
        }
        
        return new int[]{kinds[target][0], kinds[target][1]};
        
    }
}