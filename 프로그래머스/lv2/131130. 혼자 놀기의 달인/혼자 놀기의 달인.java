import java.util.*;
class Solution {
    static boolean[] visited; //카드 번호에 대한 방문 처리
    static ArrayList<Integer> rslt;
    public int solution(int[] cards) {
        int answer = 0;
        int n = cards.length;
        visited = new boolean[n+1];
        rslt = new ArrayList<>();
        for(int pick=0; pick<n; pick++){ //첫번째 카드부터 n-1번 카드들 인덱스
             if(!visited[pick]){ //열지 않은 카드
                 game(0, pick, cards); //게임 세트 수, 카드 번호, 카드 배열
            }
        }
        
        if(rslt.size() == 1) return 0; //그룹이 하나밖에 생성 안됨 
        Collections.sort(rslt);
        return rslt.get(rslt.size()-1) * rslt.get(rslt.size()-2); //2개 이상의 그룹 생성
       
    }
    //이미 연 상자가 나오기 전까지 계속 방문처리해주면서 열기
    //카드 번호에 해당하는 상자를 연다, cnt ->연 상자 수
    public static void game(int cnt, int pick, int[] cards){
        if(visited[pick]){ //열려있는 상자 만나면
            rslt.add(cnt); //상자 수 정답 리스트 저장
            return;
        }
        visited[pick]  = true; //해당 카드 방문처리
        //카드 pick번에 해당하는 상자 열어주기, cards[i]는 i+1번 상자에 담긴숫자
        game(cnt+1, cards[pick]-1, cards);
    }
}