import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        
        Map<String, Integer> counts = new HashMap<>(); // 장르에 대한 통 재생횟수 저장 Map
        PriorityQueue<int[]> pq = new PriorityQueue<>( // 총 재생횟수 내림차순, 장르 내 재생횟수 내림차순, 고유번호 오름차순
            Comparator.comparingInt((int[] a) -> a[0]).reversed()
            .thenComparing(Comparator.comparingInt((int[] a) -> a[1]).reversed())
            .thenComparing((int[] a) -> a[2]));
        ArrayList<Integer> result = new ArrayList<>();
        
        for(int i=0; i<genres.length; i++){
            counts.put(genres[i], counts.getOrDefault(genres[i], 0) + plays[i]);
        }
        
        for(int i=0; i<plays.length; i++){
            pq.add(new int[]{counts.get(genres[i]), plays[i], i}); // 속한 장르 총 재생횟수, 노래 재생횟수, 고유 번호 저장
        }
        
        while(!pq.isEmpty()){
            
            // 한 장르에 대해 상위 2개 곡 베스트 앨범 추기
            int genreCount = pq.peek()[0];
            for(int i=0; !pq.isEmpty() && genreCount == pq.peek()[0] && i < 2; i++){
                int[] song = pq.poll();
                result.add(song[2]);
            }
            
            // 2개 베스트앨범에 추가한 후 같은 장르에 대한 나머지 곡 삭제
            while(!pq.isEmpty() && pq.peek()[0] == genreCount){
                pq.poll();
            }
        }
        
        int[] answer = new int[result.size()];
        for(int i=0; i<result.size(); i++){
            answer[i] = result.get(i);
        }
        return answer;
        
    }
}