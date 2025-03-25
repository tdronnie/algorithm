import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        System.out.println(findK(n, k));



    }

    static int findK(int n, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int[] loc = new int[100001];
        Arrays.fill(loc, Integer.MAX_VALUE);

        loc[n] = 0;
        pq.add(new int[]{n, 0});

        while (!pq.isEmpty()) {
            int[] value = pq.poll();

            if(value[1] > loc[value[0]]) continue;

            if(value[0] == k){
                return value[1];
            }

            if(value[0] * 2 <= 100000 && value[1] < loc[value[0] * 2]){
                loc[value[0] * 2] = value[1];
                pq.add(new int[]{value[0]*2, value[1]});
            }

            if(value[0] - 1 >= 0 && value[1] + 1 < loc[value[0] - 1]){
                loc[value[0] - 1] = value[1] + 1;
                pq.add(new int[]{value[0]-1, value[1] + 1});
            }

            if(value[0] + 1 <= 100000 && value[1] + 1 < loc[value[0] + 1]){
                loc[value[0] + 1] = value[1] + 1;
                pq.add(new int[]{value[0]+1, value[1] + 1});
            }



        }

        return -1;
    }
}
