import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        HashMap<Integer, String> parking = new HashMap<>();
        HashMap<Integer, Integer> parkingTime = new HashMap<>();
        HashMap<Integer, Integer> totalFees = new HashMap<>();
        
        for(int i=0; i<records.length; i++){
            StringTokenizer st = new StringTokenizer(records[i], " ");
            String currentTime = st.nextToken();
            int number = Integer.parseInt(st.nextToken());
            String way = st.nextToken();
            
            if(way.equals("IN")){
                parking.put(number, currentTime);
            } else {
                String inTime = parking.get(number);
                parking.remove(number);
                st = new StringTokenizer(inTime, ":");
                int inHour = Integer.parseInt(st.nextToken());
                int inMinute = Integer.parseInt(st.nextToken());
                
                st = new StringTokenizer(currentTime, ":");
                int outHour = Integer.parseInt(st.nextToken());
                int outMinute = Integer.parseInt(st.nextToken());
                
                int stayHours = outHour - inHour;
                int stayMinutes = outMinute - inMinute;
                if(stayMinutes < 0) {
                    if(stayHours > 0) stayHours -= 1;
                    stayMinutes += 60;
                }
                stayHours *= 60;

                parkingTime.put(number, parkingTime.getOrDefault(number, 0) + stayHours + stayMinutes);
                
            }
        }
        
        //출차 내역 없는 것 처리
        if(!parking.isEmpty()){
            for(int number : parking.keySet()){
                String inTime = parking.get(number);
                StringTokenizer st = new StringTokenizer(inTime, ":");
                int inHour = Integer.parseInt(st.nextToken());
                int inMinute = Integer.parseInt(st.nextToken());

                int stayHours = (23 - inHour)*60;
                int stayMinutes = 59 - inMinute;

                parkingTime.put(number, parkingTime.getOrDefault(number, 0) + stayHours + stayMinutes);
            }
            
        }
        
        //주차비 계산
        for(int number : parkingTime.keySet()){
            int time = parkingTime.get(number);
            int minimumTime = fees[0];
            int minimumFee = fees[1];
            int perTime = fees[2];
            int perFee = fees[3];
            
            if(time < minimumTime){
                totalFees.put(number, minimumFee);
            } else {
                int per = (time - minimumTime) / perTime;
                if((time - minimumTime) % perTime != 0) per++;

                int total = minimumFee + per * perFee;

                totalFees.put(number, total);
            }
  
        }
        
        List<Integer> arrange = new ArrayList<>(totalFees.keySet());
        
        Collections.sort(arrange);
        
        int[] answer = new int[arrange.size()];
        for(int i=0; i<arrange.size(); i++){
            answer[i] = totalFees.get(arrange.get(i));
        }
        
        return answer;
        
    }
}