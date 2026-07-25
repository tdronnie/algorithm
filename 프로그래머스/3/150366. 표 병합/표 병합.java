import java.util.*;
class Solution {
    String[][] cell;
    int[] parent;
    List<String> answer;
    public String[] solution(String[] commands) {
        // merge -> union
        // unmerge -> cancel
        // update
        // 문자열로 검색 -> 해당 문자열 가지는 좌표 모두 업데이트
        // 좌표로 검색 -> unoin
        cell = new String[51][51];
        for(int i=1; i<=50; i++){
            for(int j=1; j<=50; j++){
                cell[i][j] = "EMPTY";
            }
        }
        answer = new ArrayList<>();
        init();
        
        for(String cmd : commands){
            StringTokenizer st = new StringTokenizer(cmd);
            switch(st.nextToken()){
                case "UPDATE" :
                    if(st.countTokens() == 3){
                        int r = Integer.parseInt(st.nextToken());
                        int c = Integer.parseInt(st.nextToken());
                        String value = st.nextToken();
                        connect(r, c, value); // r c의 값을 value로 변경
                    } else {
                        String value1 = st.nextToken();
                        String value2 = st.nextToken();
                        // System.out.println(value1 + value2);
                        switchValue(value1, value2); // value1의 값을 value2로 변경
                    }
                    break;
                case "MERGE" :
                    int r1 = Integer.parseInt(st.nextToken());
                    int c1 = Integer.parseInt(st.nextToken());
                    int r2 = Integer.parseInt(st.nextToken());
                    int c2 = Integer.parseInt(st.nextToken());
                    connect(r1, c1, r2, c2);
                    break;
                case "UNMERGE" :
                    int r3 = Integer.parseInt(st.nextToken());
                    int c3 = Integer.parseInt(st.nextToken());
                    cancel(r3, c3); // r c가 포함된 모든 연결 끊기
                    break;
                case "PRINT" :
                    int r4 = Integer.parseInt(st.nextToken());
                    int c4 = Integer.parseInt(st.nextToken());
                    print(r4, c4);
                    break;
            }
        }
        
        String[] result = new String[answer.size()];
        int idx = 0;
        for(String v : answer){
            result[idx++] = v;
        }
        // return result;
        return answer.toArray(new String[0]);
        
    }
    
    public void print(int r, int c){
        System.out.println(cell[r][c]);
        answer.add(cell[r][c]);
    }
    
    // 유니온 파인드
    public void init(){
        parent = new int[51*51];
        for(int i=1; i<=50; i++){
            for(int j=1; j<=50; j++){
                parent[i*50+j] = i*50+j;                
            }
        }
    }
    
    // 병합되어 있는 부모 좌표 찾기
    public int find (int n){
        if(parent[n] == n) return n;
        return parent[n] = find(parent[n]);
    }
    
    // 두 집합 서로 합치기 a를 b에 합치기
    public void union (int a, int b){
        int u1 = find(a);
        int u2 = find(b);
        
        if(u1 != u2){
            parent[u1] = u2;
        }
    }
    
    public void switchValue(String v1, String v2){
        
        for(int i=1; i<=50; i++){
            for(int j=1; j<=50; j++){
                if(v1.equals(cell[i][j])){
                    cell[i][j] = v2;
                }
            }
        }
    }
    
    public void connect(int r, int c, String v){
        
        int target = find(r*50+c);
        for(int i=1; i<=50; i++){
            for(int j=1; j<=50; j++){
                if(find(i*50+j) == target){
                    cell[i][j] = v;
                }
            }
        }
    }
    
    public void connect(int r1, int c1, int r2, int c2){
        
        union(r1*50+c1, r2*50+c2);
        String newValue = (!cell[r1][c1].equals("EMPTY")) ? cell[r1][c1] : cell[r2][c2];
        
        int target = find(r2*50+c2);
        for(int i=1; i<=50; i++){
            for(int j=1; j<=50; j++){
                if(find(i*50+j) == target){
                    cell[i][j] = newValue;
                }
            }
        }
    }
    
    // 부모 좌표 찾고 연결 끊기
    public void cancel(int r, int c){
        
        int target = find(r*50+c);
        for(int i=1; i<=50; i++){
            for(int j=1; j<=50; j++){
                if(find(i*50+j) == target){
                    parent[i*50+j] = i*50+j;
                    if(i != r || j != c){
                        cell[i][j] = "EMPTY";
                    }
                }
            }
        }
    }
}