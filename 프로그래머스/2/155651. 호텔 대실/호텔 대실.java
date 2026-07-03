import java.util.*;
class Solution {
    public int solution(String[][] book_time) {
        
        // 시작시간, 퇴실시간 저장, 시작시간이 빠른 순서대로 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        
        // 운영하는 객실마다 서비스할 수 있는 빠른 시간 순 정렬
        PriorityQueue<Integer> rooms = new PriorityQueue<>();
        
        // 시간 모두 분으로 환산 및 정수화
        for(String[] time : book_time){
            //정수화
            int startH = Integer.parseInt(time[0].split(":")[0]);
            int startM = Integer.parseInt(time[0].split(":")[1]);
            int endH = Integer.parseInt(time[1].split(":")[0]);
            int endM = Integer.parseInt(time[1].split(":")[1]);
            
            // 분으로 환산 후 pq에 집어넣기
            pq.add(new int[]{startH * 60 + startM, endH * 60 + endM});
   
        }
        
        while(!pq.isEmpty()){

            int[] next = pq.poll();
            
            // 아직 객실 배정안되었거나 가장빠른 입실 가능시간보다 이른 시간에 입실해야할 때 방 추가
            if(rooms.isEmpty() || rooms.peek() > next[0]){
                rooms.add(next[1] + 10);
            } else { // 방 재사용
                rooms.poll();
                rooms.add(next[1] + 10);
            }
        }
        
        return rooms.size();
    }
}