import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m;
    static char[][] map;
    static int[][] nodes;
    static List<Edge> edges;
    static int[] moveX = {-1, 0, 1, 0};
    static int[] moveY = {0, 1, 0, -1};
    static int[] parent;

    static class Edge implements Comparable<Edge> {

        int from, to, dis;

        Edge (int from, int to, int dis) {
            this.from = from;
            this.to = to;
            this.dis = dis;
        }

        public int compareTo(Edge edge) {
            return this.dis - edge.dis;
        }
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][n];
        nodes = new int[m + 1][2];
        edges = new ArrayList<>();

        int k = 0;

        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = row.charAt(j);
                if(map[i][j] == 'S'){
                    nodes[k][0] = i;
                    nodes[k][1] = j;
                    k++;
                } else if (map[i][j] == 'K') {
                    nodes[k][0] = i;
                    nodes[k][1] = j;
                    k++;
                }
            }
        }

        for (int i = 0; i <= m; i++) {
            bfs(i);
        }

        Collections.sort(edges);
        parent = new int[m + 1];
        for (int i = 0; i <= m; i++) {
            parent[i] = i;
        }

        int totalDis = 0;
        int edgeCount = 0;
        for (Edge edge : edges) {
            if (find(edge.from) != find(edge.to)) {
                union(edge.from, edge.to);
                totalDis += edge.dis;
                edgeCount++;
            }
        }

        if (edgeCount == m) { //간선 수 == 노드 수 -1
            System.out.println(totalDis);
        } else {
            System.out.println(-1);
        }
    }

    static void bfs(int start) {
        int[] node = nodes[start];
        int[][] dis = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dis[i], -1);
        }
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{node[0], node[1]});
        dis[node[0]][node[1]] = 0;

        while (!q.isEmpty()) {
            int[] value = q.poll();
            int currX = value[0];
            int currY = value[1];

            for (int k = 0; k < 4; k++) {
                int newX = currX + moveX[k];
                int newY = currY + moveY[k];

                if (newX < 0 || newY < 0 || newX >= n || newY >= n || map[newX][newY] == '1' || dis[newX][newY] != -1) continue;

                dis[newX][newY] = dis[currX][currY] + 1;
                q.add(new int[]{newX, newY});

                if(map[newX][newY] == 'K' || map[newX][newY] == 'S'){
                    for (int i = 0; i <= m; i++) {
                        if(nodes[i][0] == newX && nodes[i][1] == newY){
                            edges.add(new Edge(start, i, dis[newX][newY]));
                            break;
                        }
                    }
                }
            }
        }
    }

    static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a < b) parent[b] = a;
        else parent[a] = b;
    }
}
