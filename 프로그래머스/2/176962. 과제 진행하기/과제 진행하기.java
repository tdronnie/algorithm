import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        
        // sortPlan -> [index][startMinute][playtime], startMinute 오름차순 정렬
        // stack -> [Plans에서 index][남은 시간]
        int[][] sortPlan = new int[plans.length][3];
        Stack<int[]> st = new Stack<>();
        String[] answer = new String[plans.length];
        int idx = 0;
        int currIdx = 0;
        int time = 0;
        
        for(int i=0; i<plans.length; i++){
            String[] s = plans[i][1].split(":");
            int hour = Integer.parseInt(s[0]);
            int minute = Integer.parseInt(s[1]);
            int playtime = Integer.parseInt(plans[i][2]);
            sortPlan[i][0] = i;
            sortPlan[i][1] = hour*60+minute;
            sortPlan[i][2] = playtime;
        }
        
        Arrays.sort(sortPlan, (a, b) -> a[1] - b[1]);
        
        while(currIdx < sortPlan.length) {
            
            // 현재 할 작업 시간으로 이동
            time = sortPlan[currIdx][1];
            
            // 다음 작업이 있을 떄
            if(currIdx + 1 < sortPlan.length){
                int nextStart = sortPlan[currIdx + 1][1];
                int currRemain = sortPlan[currIdx][2];
                
                // 다음 작업시작까지 끝낼 수 없다면, 다음 새로운 작업 시작전까지만 작업진행 후 스택에 넣기
                if(time + currRemain > nextStart){
                    currRemain -= nextStart - time;
                    int orignIdx = sortPlan[currIdx][0];
                    st.push(new int[]{orignIdx, currRemain});
                    currIdx++;
                    continue; // 작업 중단되는 동안은 계속 다음 작업으로
                } else { // 다음 작업시작까지 끝낼 수 있다면, 다음 새로운 작업 시작전까지 작업 끝내기 및 중단 작업 남은 시간만큼 재개
                    answer[idx++] = plans[sortPlan[currIdx][0]][0];
                    int middle = nextStart - (time + currRemain); // 다음 작업 시작전까지 시간
                    while(middle > 0 && !st.isEmpty()){
                        int[] pause = st.pop();
                        // 작업 남은 시간에 따라 차감해주고 다시 중단 or 끝내기
                        if(pause[1] > middle){
                            pause[1] -= middle;
                            st.push(pause);
                            middle = 0;
                        } else {
                            answer[idx++] = plans[pause[0]][0];
                            middle -= pause[1];
                        }
                    }
                    currIdx++;
                }
            } else { // 마지막 새로운 작업
                answer[idx++] = plans[sortPlan[currIdx++][0]][0];
            }
        }
        // 남아있는 중단 작업들 모두 재개
        while(!st.isEmpty()){
            answer[idx++] = plans[st.pop()[0]][0];
        }
            
        return answer;
        
    }
}