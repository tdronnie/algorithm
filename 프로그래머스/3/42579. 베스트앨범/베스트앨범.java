import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> totalStream = new HashMap<>();
        PriorityQueue<int[]> best = new PriorityQueue<>(Comparator.comparingInt((int[] a) -> a[0]).reversed()
          .thenComparing(Comparator.comparing((int[] a) -> a[1]).reversed())
            .thenComparingInt(a -> a[2]));
        
        //장르 총 횟수 내림차순 -> 노래 횟수 내림차순 -> 고유번호 오름차순 정렬
        
        for(int i=0; i<genres.length; i++){
            totalStream.merge(genres[i], plays[i], Integer::sum);
        }
        
        ArrayList<Integer> answer = new ArrayList<>();
        
        for(int i=0; i<genres.length; i++){
            best.add(new int[]{totalStream.get(genres[i]), plays[i], i});
        }
        
        while(!best.isEmpty()){
                        
            int stream = best.peek()[0];
            for(int i=0; !best.isEmpty() && best.peek()[0] == stream && i<2; i++){
                int[] value = best.poll();
                answer.add(value[2]);
            }
            
            while(!best.isEmpty() && best.peek()[0] == stream){
                best.poll();
            }
 
        }
        
        int[] result = new int[answer.size()];
        for(int i=0; i<result.length; i++){
            result[i] = answer.get(i);
        }
        
        return result;
                    
    }
}