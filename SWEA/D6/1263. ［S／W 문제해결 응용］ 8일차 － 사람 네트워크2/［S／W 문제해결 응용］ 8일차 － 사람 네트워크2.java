import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

    static int n, min;
    static int[] minDis;
    static boolean[] visited;

    static class Node implements Comparable<Node> {
        int to, dis;

        public Node(int to, int dis) {
            this.to = to;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node o) {
            return this.dis - o.dis;
        }
    }

    static ArrayList<Node>[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());

            arr = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                arr[i] = new ArrayList<>();
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (Integer.parseInt(st.nextToken()) == 1) {
                        arr[i].add(new Node(j, 1)); //이어져 있는 사람이므로 1로 초기화
                    }
                }
            }

            //임의의 노드에 대해서 모든 노드까지의 최단거리 구하기
            min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                minDis = new int[n];
                for (int j = 0; j < n; j++) {
                    minDis[j] = Integer.MAX_VALUE;
                }
                visited = new boolean[n];
                getMinDis(i);

                int cc = 0;
                for (int j = 0; j < n; j++) {
                    cc += minDis[j];
                }
                min = Math.min(min, cc);

            }

            sb.append("#").append(tc).append(" ").append(min).append("\n");
        }
        System.out.println(sb);

    }

    private static void getMinDis(int start) {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        minDis[start] = 0; //자기자신까지의 거리는 0
        visited[start] = true;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            for (int i = 0; i < arr[node.to].size(); i++) {
                Node next = arr[node.to].get(i);

                if (minDis[next.to] > node.dis + next.dis) {
                    if (!visited[next.to]) {
                        visited[next.to] = true;
                        minDis[next.to] = node.dis + next.dis;
                        pq.add(new Node(next.to, minDis[next.to]));
                    }
                }
            }

        }
    }
}