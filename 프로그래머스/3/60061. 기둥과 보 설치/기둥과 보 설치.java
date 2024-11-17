class Solution {
    static int[][][] building;
    static int count = 0;
    
    public int[][] solution(int n, int[][] build_frame) {
        building = new int[n+1][n+1][2];
        
        for(int[] frame : build_frame) {
            int x = frame[0];
            int y = frame[1];
            int type = frame[2];  // 0 기둥, 1 보
            int cmd = frame[3];   // 0 삭제, 1 설치
            
            if(cmd == 1) {  // 설치
                if(type == 0) buildPillar(n+1, x, y);
                else buildGirder(n+1, x, y);
            } else {  // 삭제
                if(type == 0) destroyPillar(n+1, x, y);
                else destroyGirder(n+1, x, y);
            }
        }
        
        return buildResult(n);
    }
    
    private void buildPillar(int n, int x, int y) {
        // 기둥은 바닥 위 || 다른 기둥 위 || 보의 한쪽 끝 부분 위
        if(y == 0 || 
           (y > 0 && building[y-1][x][0] == 1) ||  // 다른 기둥 위
           building[y][x][1] == 1 ||               // 보의 한쪽 끝
           (x > 0 && building[y][x-1][1] == 1)) {  // 보의 다른 쪽 끝
            building[y][x][0] = 1;
            count++;
        }
    }
    
    private void buildGirder(int n, int x, int y) {
        // 보는 양쪽 중 한쪽 끝 부분이 기둥 위 || 양쪽 끝 부분이 다른 보와 연결
        if((y > 0 && building[y-1][x][0] == 1) ||     // 왼쪽 끝이 기둥 위
           (y > 0 && x < n && building[y-1][x+1][0] == 1) ||  // 오른쪽 끝이 기둥 위
           (x > 0 && x < n && building[y][x-1][1] == 1 && building[y][x+1][1] == 1)) {  // 양쪽이 보와 연결
            building[y][x][1] = 1;
            count++;
        }
    }
    
    private void destroyPillar(int n, int x, int y) {
        // 일단 삭제
        building[y][x][0] = 0;
        
        // 삭제 먼저 해보고 구조물이 유효한지 확인
        if(!isValidStructure(n)) {
            building[y][x][0] = 1;  // 유효하지 않으면 되돌림
            return;
        }
        count--;
    }
    
    private void destroyGirder(int n, int x, int y) {
        building[y][x][1] = 0;
        
        if(!isValidStructure(n)) {
            building[y][x][1] = 1;
            return;
        }
        count--;
    }
    
    private boolean isValidStructure(int n) {
        for(int y = 0; y < n; y++) {
            for(int x = 0; x < n; x++) {
                // 기둥이 있는 경우
                if(building[y][x][0] == 1 && !isValidPillar(n, x, y)) return false;
                
                // 보가 있는 경우
                if(building[y][x][1] == 1 && !isValidGirder(n, x, y)) return false;
            }
        }
        return true;
    }
    
    private boolean isValidPillar(int n, int x, int y) {
        return y == 0 || 
               (y > 0 && building[y-1][x][0] == 1) ||
               building[y][x][1] == 1 ||
               (x > 0 && building[y][x-1][1] == 1);
    }
    
    private boolean isValidGirder(int n, int x, int y) {
        return (y > 0 && building[y-1][x][0] == 1) ||
               (y > 0 && x < n && building[y-1][x+1][0] == 1) ||
               (x > 0 && x < n && building[y][x-1][1] == 1 && building[y][x+1][1] == 1);
    }
    
    private int[][] buildResult(int n) {
        int[][] result = new int[count][3];
        int idx = 0;
        
        for(int x = 0; x <= n; x++) {
            for(int y = 0; y <= n; y++) {
                if(building[y][x][0] == 1) {
                    result[idx++] = new int[]{x, y, 0};
                }
                if(building[y][x][1] == 1) {
                    result[idx++] = new int[]{x, y, 1};
                }
            }
        }
        
        return result;
    }
}