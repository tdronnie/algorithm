import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, step = 1;
    static ArrayList<Integer>[] arr;
    static boolean[] visited;
    static int[] ans;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); //정점의 수
        int m = Integer.parseInt(st.nextToken()); //간선의 수
        int r = Integer.parseInt(st.nextToken()); //시작 정점

        arr = new ArrayList[n+1];
        visited = new boolean[n+1]; //방문배열

        for(int i=1; i<=n; i++){
            arr[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            arr[from].add(to);
            arr[to].add(from);
        }
        
        //오름차순 탐색 위해 각 이어진 노드들 정렬
        for(int i=1; i<=n; i++){
            Collections.sort(arr[i]);
        }
        visited[r] = true;
        ans = new int[n+1]; //출력할 정점 수만큼 정답 배열 만들기
        dfs(r);

        for(int i=1; i<=n; i++){
            System.out.println(ans[i]);
        }

    }

    public static void dfs(int now){

        //i번째 줄에 i번의 방문 순서 출력
        ans[now] = step++;
        for(int i=0; i<arr[now].size(); i++){
            int val = arr[now].get(i);
            if(!visited[val]){
                visited[val] = true;
                dfs(val);
            }
        }
    }
}
