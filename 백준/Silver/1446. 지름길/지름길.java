import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Shortcut {
    int start, end, length;

    Shortcut(int start, int end, int length) {
        this.start = start;
        this.end = end;
        this.length = length;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getLength() {
        return length;
    }

}

public class Main {

    static int shortestLen = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        Shortcut[] routes = new Shortcut[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            routes[i] = new Shortcut(s, e, l);
        }

        findShortRoute(routes, n, d);
        System.out.println(shortestLen);

    }

    static void findShortRoute(Shortcut[] routes, int n, int dest) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]); //길이가 짧은 지름길을 우선적으로
        boolean[] visited = new boolean[dest + 1]; // 도착위치 방문처리
        pq.add(new int[]{0, 0, 0}); //시작 위치, 지름길 끝 위치, 총 길이

        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int start = poll[0];
            int end = poll[1];
            int len = poll[2];

            if(visited[end] || end > dest) continue;
            visited[end] = true;

            if (end == dest) {
                shortestLen = Math.min(shortestLen, len);
                continue;
            }

            pq.add(new int[]{end, dest, len + dest - end});

            for (int i = 0; i < n; i++) {

                int newStart = routes[i].getStart();
                int newEnd = routes[i].getEnd();
                int newLength = routes[i].getLength();

                if (newStart >= end && dest >= newEnd) {

                    int walk = newStart - end;
                    int newLen = len + walk + newLength;

                    pq.add(new int[]{newStart, newEnd, newLen});
                }
            }
        }
    }
}