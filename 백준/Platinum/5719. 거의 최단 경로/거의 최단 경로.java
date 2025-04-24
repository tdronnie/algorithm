import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Road5719 {
    int from, to, len;

    Road5719(int from, int to, int len) {
        this.from = from;
        this.to = to;
        this.len = len;
    }

    Road5719(int to, int len) {
        this.to = to;
        this.len = len;
    }
}

public class Main {

    static int n;
    static ArrayList<Road5719>[] places;
    static boolean[][] shortestPath;
    static ArrayList<Integer>[] pre;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if(n==0 && m==0) break;

            places = new ArrayList[n];
            pre = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                places[i] = new ArrayList<>();
                pre[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                places[u].add(new Road5719(u, v, p));
            }

            shortestPath = new boolean[n][n];
            
            findMinRoute(s);
            checkShortestPath(s, d);
            int almostMinRoute = findAlmostMinRoute(s, d);
            
            if(almostMinRoute == Integer.MAX_VALUE){
                sb.append(-1).append("\n");
            } else {
                sb.append(almostMinRoute).append("\n");
            }
        }
        System.out.print(sb);
    }

    static int findAlmostMinRoute(int start, int end) {
        PriorityQueue<Road5719> pq = new PriorityQueue<>((a, b) -> a.len - b.len);
        int[] dis = new int[n];

        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[start] = 0;
        pq.add(new Road5719(start, 0));

        while (!pq.isEmpty()) {
            Road5719 curr = pq.poll();
            int to = curr.to;
            int len = curr.len;

            if(len > dis[to]) continue;

            for (Road5719 place : places[to]) {
                int next = place.to;
                int nextLen = place.len;

                if(shortestPath[to][next]) continue;

                if (dis[next] > len + nextLen) {
                    dis[next] = len + nextLen;
                    pq.add(new Road5719(next, dis[next]));
                }
            }
        }

        return dis[end];
    }

    static void checkShortestPath(int start, int end) {
        if(start == end) return;

        for (int pr : pre[end]) {
            if(!shortestPath[pr][end]) {
                shortestPath[pr][end] = true;
                checkShortestPath(start, pr);
            }
        }
    }

    static void findMinRoute(int start) {
        PriorityQueue<Road5719> pq = new PriorityQueue<>((a, b) -> a.len - b.len);
        int[] dis = new int[n];

        for (int i = 0; i < n; i++) {
            pre[i].clear();
        }

        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[start] = 0;
        pq.add(new Road5719(start, 0));

        while (!pq.isEmpty()) {
            Road5719 curr = pq.poll();
            int node = curr.to;
            int len = curr.len;
            
            if(len > dis[node]) continue;
            
            for (Road5719 r : places[node]) {
                int next = r.to;
                int nextLen = r.len;

                if (dis[next] > len + nextLen) {
                    dis[next] = len + nextLen;
                    pq.add(new Road5719(next, len + nextLen));
                    
                    //최단 경로 초기화, 이전 노드 저장
                    pre[next].clear();
                    pre[next].add(node);

                } else if (dis[next] == len + nextLen) {
                    pre[next].add(node);
                }
            }
        }
    }
}
