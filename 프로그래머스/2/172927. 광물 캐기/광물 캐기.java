import java.util.*;
class Solution {
    public int solution(int[] picks, String[] minerals) {
        // 하나의 곡괭이로 5개 캐기, 높은 광물들 있는 곳은 높은 곡괭이로
        List<int[]> sortM = new ArrayList<>();
        int canMiningCount = (picks[0] + picks[1] + picks[2])*5;
        
        // 광물 5개 묶음마다 각 광물 개수 구하기
        for(int i=0; i<minerals.length && i<canMiningCount; i+=5){
            int[] unit = new int[3];
            for(int j=i; j<i+5 && j<minerals.length && j<canMiningCount; j++){
                if(minerals[j].equals("diamond")){
                    unit[0]++;
                } else if (minerals[j].equals("iron")){
                    unit[1]++;
                } else {
                    unit[2]++;
                }
            }
            sortM.add(unit);
        }
        
        // 높은 광물이 많은 묶음 내림차순 정렬
        sortM.sort((a, b) -> {
            if(a[0] == b[0]){
                if(a[1] == b[1]){
                    return b[2] - a[2];
                }
                return b[1] - a[1];
            }
            return b[0] - a[0];
        });
        
        // 작업
        int idx = 0;
        int hp = 0;
        for(int[] unit : sortM){
            while(idx < 3 && picks[idx] == 0) idx++; // 소진되지 않은 곡괭이 찾기
            if(idx == 3) break; // 곡괭이 모두 소진
            
            if(idx == 0){
                hp += unit[0] + unit[1] + unit[2]; // 다이아 곡괭이로 광물 캐기
            } else if (idx == 1) {
                hp += unit[0] * 5 + unit[1] + unit[2]; // 철 곡괭이
            } else {
                hp += unit[0] * 25 + unit[1] * 5 + unit[2];
            }
            picks[idx]--;
        }
        
        return hp;
        
        
    }
}