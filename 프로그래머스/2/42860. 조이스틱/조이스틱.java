class Solution {
    public int solution(String name) {
        
        int move = 0;
        int minMove = name.length() - 1;
        
        for(int i=0; i<name.length(); i++){
            
            // 알파벳 조작
            move += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);

            // 다음으로 커서 조작, A아닌 다음 인덱스 찾기
            int next = i+1;
            while(next < name.length() && name.charAt(next) == 'A'){
                next++;
            }
            
            // 인덱스 i와 next로 이동하는 최소거리 구하기
            // 인덱스 순 이동 vs (오른쪽으로 i까지 + 돌아오고 왼쪽으로 next까지) vs (왼쪽으로 next까지 + 돌아와서 오른쪽으로 i까지)
            minMove = Math.min(minMove, (i * 2) + name.length() - next);
            minMove = Math.min(minMove, (name.length() - next) * 2 + i);
            
        }
        
        return move + minMove;
        
    }
}