/*
임의의 사람이 모든 사람에게 이어지는 최단거리의 합이 가장 작은 사람 찾기
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Node implements Comparable<Node>{
        int to, dis;

        public Node(int to, int dis) {
            this.to = to;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node o) {
            return this.dis-o.dis;
        }

    }
    static ArrayList<Node>[] arr;
    static int[] minDis;
    static boolean[] visited;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        arr = new ArrayList[n+1];

        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            arr[from].add(new Node(to, 1));
            arr[to].add(new Node(from, 1));
        }

        //n명의 사람 모두 자신을 제외한 나머지 사람들까지 가는 최솟값 구하기
        int min = Integer.MAX_VALUE;
        int minP = -1;
        for (int i = 1; i <= n; i++) {
            minDis = new int[n+1];
            for (int j = 1; j <= n; j++) {
                minDis[j] = Integer.MAX_VALUE;

            }
            visited = new boolean[n + 1];
            getMinDis(i);

            int sum = 0;
            for (int j = 1; j <= n; j++) {
                sum += minDis[j];
            }
            if(min > sum){ //최솟값이 같은 경우 숫자가 작은 것 출력해야하기 때문에 작은 경우만 갱신
                min = sum;
                minP = i;
            }
        }
        System.out.println(minP);

    }

    private static void getMinDis(int i) {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        minDis[i] = 0;
        visited[i] = true;
        pq.add(new Node(i, 0));

        while (!pq.isEmpty()) {

            Node node = pq.poll();

            for (int j = 0; j < arr[node.to].size(); j++) {
                Node next = arr[node.to].get(j);

                if (!visited[next.to] && minDis[next.to] > node.dis + next.dis) { //node까지의 최소거리 + 그 다음 사람까지의 거리(1)
                    minDis[next.to] = node.dis + next.dis;
                    visited[next.to] = true;
                    pq.add(new Node(next.to, minDis[next.to]));
                }

            }

}
    }
}