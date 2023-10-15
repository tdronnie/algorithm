import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int n;

    static class Room{
        int to;
        long w;

        public Room(int to, long w) {
            this.to = to;
            this.w = w;
        }
    }
    static ArrayList<Room>[] arr;
    static long[] maxDis;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new ArrayList[n+1];
        maxDis = new long[n+1];

        for (int i = 1; i < n+1; i++) {
            arr[i] = new ArrayList<>();
            maxDis[i] = Long.MAX_VALUE;
        }

        for (int i = 0; i < n-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            arr[from].add(new Room(to, w));
            arr[to].add(new Room(from, w));
        }
        
        findMaxDis(); //입구에서 가장 먼 방 찾기

        long ans = 0;
        for (int i = 1; i < n+1; i++) {
            if(maxDis[i] == Long.MAX_VALUE)
                continue;
            ans = Math.max(ans, maxDis[i]);
        }
        System.out.println(ans);

    }

    public static void findMaxDis(){
        PriorityQueue<Room> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o2.w, o1.w));
        pq.add(new Room(1, 0));
        maxDis[1] = 0; //입구에서 입구까지의 최대거리는 0

        while(!pq.isEmpty()){
            Room r = pq.poll();
            int to = r.to;
            long w = r.w;

            for (int i = 0; i < arr[to].size(); i++) {
                int next = arr[to].get(i).to;
                long newW = arr[to].get(i).w;
                if(maxDis[next] > w + newW){
                    maxDis[next] = w + newW;
                    pq.add(new Room(next, w + newW));
                }
            }
        }

    }
}