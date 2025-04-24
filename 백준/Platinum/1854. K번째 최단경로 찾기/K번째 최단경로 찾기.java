import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class City1854{
    int to, time;

    City1854(int to, int time) {
        this.to = to;
        this.time = time;
    }
}

public class Main {

    static int n;
    static ArrayList<City1854>[] cities;
    static PriorityQueue<Integer>[] kDis;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        cities = new ArrayList[n + 1];
        kDis = new PriorityQueue[n + 1];
        for(int i=1; i<=n; i++){
            cities[i] = new ArrayList<>();
            kDis[i] = new PriorityQueue<>(k, Collections.reverseOrder());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            cities[a].add(new City1854(b, c));
        }

            findKMinRoute(k);

        for (int i = 1; i <= n; i++) {
            if(kDis[i].size() != k){
                sb.append(-1).append("\n");
            } else{
                sb.append(kDis[i].peek()).append("\n");
            }
        }

        System.out.println(sb);

    }

    static void findKMinRoute(int k) {
        PriorityQueue<City1854> pq = new PriorityQueue<>((a, b) -> a.time - b.time);
        pq.add(new City1854(1, 0));
        kDis[1].add(0);

        while (!pq.isEmpty()) {
            City1854 city = pq.poll();
            int curr = city.to;
            int time = city.time;

            for (City1854 next : cities[curr]) {
                int nextCity = next.to;
                int nextTime = next.time;

                if (kDis[nextCity].size() == k) {
                    if (kDis[nextCity].peek() > time + nextTime) {
                        kDis[nextCity].poll();
                        kDis[nextCity].add(time + nextTime);
                        pq.add(new City1854(nextCity, time + nextTime));
                    }
                } else{
                    kDis[nextCity].add(time + nextTime);
                    pq.add(new City1854(nextCity, time + nextTime));
                }
            }
        }
    }
}