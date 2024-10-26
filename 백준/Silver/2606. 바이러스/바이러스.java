import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static ArrayList<Integer>[] graph;
    static int victimCount = 0;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];

        for(int i=1; i<=N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        findVictim(1);
        System.out.print(victimCount-1);
    }

    public static void findVictim(int virusCom) {
        boolean[] visited = new boolean[N+1];
        Arrays.fill(visited, false);
        Queue<Integer> q = new ArrayDeque<>();

        q.add(virusCom);
        visited[virusCom] = true;

        while(!q.isEmpty()) {
            int com = q.poll();
            victimCount++;
            for(int newCom : graph[com]) {
                if(visited[newCom]) continue;
                visited[newCom] = true;
                q.add(newCom);
            }
        }
    }
}