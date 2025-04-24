import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Pair9370 {
    int to, len;

    Pair9370(int to, int len) {
        this.to = to;
        this.len = len;
    }

    public int getTo() {
        return to;
    }

    public int getLen() {
        return len;
    }
}

public class Main {

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int test = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < test; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            ArrayList<Pair9370>[] arr = new ArrayList[n+1];

            for (int i = 1; i <= n; i++) {
                arr[i] = new ArrayList();
            }

            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int lenGH = 0;

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                arr[a].add(new Pair9370(b, d));
                arr[b].add(new Pair9370(a, d));
                if (a == g && b == h || a == h && b == g) {
                    lenGH = d;
                }
            }

            int[] S = new int[t];
            int[] G = new int[t];
            int[] H = new int[t];

            S = checkShortRoute(arr, s, n);
            G = checkShortRoute(arr, g, n);
            H = checkShortRoute(arr, h, n);

            ArrayList<Integer> answer = new ArrayList<>();
            for (int i = 0; i < t; i++) {
                int candidate = Integer.parseInt(br.readLine());
                if (S[candidate] == S[g] + lenGH + H[candidate] || S[candidate] == S[h] + lenGH + G[candidate]) {
                    answer.add(candidate);
                }
            }
            Collections.sort(answer);
            for (int value : answer) {
                sb.append(value).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int[] checkShortRoute(ArrayList<Pair9370>[] arr, int start, int n) {
        PriorityQueue<Pair9370> pq = new PriorityQueue<>((a, b) -> a.getLen() - b.getLen());
        boolean[] visited = new boolean[n + 1];
        int[] dis = new int[n+1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        pq.add(new Pair9370(start, 0));
        dis[start] = 0;

        while (!pq.isEmpty()) {

            Pair9370 curr = pq.poll();
            int currNode = curr.getTo();
            int currLen = curr.getLen();

            if(visited[currNode]) continue;
            visited[currNode] = true;

            for (Pair9370 next : arr[currNode]) {
                int nextNode = next.getTo();
                int nextLen = next.getLen();

                if (dis[nextNode] > currLen + nextLen) {
                    dis[nextNode] = currLen + nextLen;
                    pq.add(new Pair9370(nextNode, dis[nextNode]));
                }
            }
        }
        return dis;
    }
}