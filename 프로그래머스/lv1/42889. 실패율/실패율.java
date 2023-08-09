import java.util.*;
class Solution {
    public int[] solution(int N, int[] stages) {
        List<Stage> rslt = new ArrayList<>();
        
        for(int i=1; i<=N; i++){
            double fail = 0;
            double clear = 0;
            double ongoing = 0;
            for(int j=0; j<stages.length; j++){
                if(stages[j] == i) //도전했지만 아직 클리어 못한 플레이어
                    ongoing++;
                else if(stages[j]>i) //클리어 한 플레이어
                    clear++;
            }
            double ratio = 0;
            if(clear+ongoing == 0) //참여한 플레이어가 없는 경우
                ratio = 0;
            else{
                ratio = ongoing/(clear+ongoing);
            }
            rslt.add(new Stage(i, ratio));
        }
        
        Collections.sort(rslt);
        int[] ans = new int[N];
        int i=0;
        for(Stage s : rslt){
            ans[i++] = s.no;
        }
        
        return ans;
    }
    
    static class Stage implements Comparable<Stage>{
        int no;
        double ratio;
        
        public Stage(int no, double ratio){
            this.no = no;
            this.ratio = ratio;
        }
        
        @Override
        public int compareTo(Stage s){
            if(this.ratio == s.ratio)
                return this.no-s.no;
            return Double.compare(s.ratio, this.ratio);
        }
    }
}