import java.util.*;
class Solution {
    public int solution(int[] priorities, int location) {
        
        Deque<int[]> q = new ArrayDeque<>();
        int[] sortPri = priorities.clone(); // 우선순위대로 처리 위해 정렬 배열
        Arrays.sort(sortPri);
        int idx = sortPri.length - 1; // 오름차순 정렬로 뒷순서부터 중요도 높음
        int count = 0; // 처리 순서 카운트
        
        for(int i=0; i < priorities.length; i++){
            q.addLast(new int[]{priorities[i], i}); // 각 프로세스 별 (우선순위, 초기 인덱스) 저장
        }
        
        while(!q.isEmpty()){
            
            int qSize = q.size(); // 현재 존재하는 큐 요소 개수만큼 프로세스 실행
            
            for(int i=0; i<q.size(); i++){
                int[] process = q.pollFirst();
                if(process[0] == sortPri[idx]){ // 현재 처리 우선순위라면 처리 수행
                    idx--; // 다음 높거나 같은 우선순위 실행
                    count++;
                    if(process[1] == location) { // 찾는 프로세스 위치라면 바로 리턴
                        return count;
                    }
                } else { // 현재 처리 우선순위 아니라면 대기 큐 넣기
                    q.addLast(process);
                }
            }
        }
        
        return 0;

    }
}