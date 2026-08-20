class Solution {
    int minChange = Integer.MAX_VALUE;
    int r, c;
    public int solution(int[][] beginning, int[][] target) {
        // 행과 열 모두 뒤집기 가능
        // 행 뒤집는 모든 경우 -> 열 뒤집는 모든 경우
        r = target.length;
        c = target[0].length;
        
        changeRow(0, 0, beginning, target);
        
        if(minChange == Integer.MAX_VALUE){
            return -1;
        }
        
        return minChange;
        
    }
    
    public void changeRow(int idx, int count, int[][] current, int[][] target){
        if(idx == r){
            // 현재 행 방법으로 모든 열의 방법 구하기
            changeCol(0, count, current, target);
            return;
        }
        
        // 그대로
        changeRow(idx + 1, count, current, target);
        
        // 바꾸기
        for(int i=0; i<c; i++){
            current[idx][i] = (current[idx][i] == 1) ? 0 : 1;
        }
        changeRow(idx + 1, count + 1, current, target);
        
        // 원복
        for(int i=0; i<c; i++){
            current[idx][i] = (current[idx][i] == 1) ? 0 : 1;
        }
    }
    
    // 행 처리 끝난 상황에서 열 처리만으로 타겟 만족하는지 확인
    public void changeCol(int idx, int count, int[][] current, int[][] target){
        if(idx == c){
            minChange = Math.min(minChange, count);
            return;
        }
        
        // 다 다르거나, 다 같아야 함
        int diff = 0;
        for(int i=0; i<r; i++){
            if(current[i][idx] != target[i][idx]){
                diff++;
            }
        }
        
        if(diff == r){
            count++;
        } else if (diff != 0){
            return;
        }
        
        changeCol(idx + 1, count, current, target);
    }
}