import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Pillar2304 implements Comparable<Pillar2304> {
    int x, y;

    Pillar2304(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Pillar2304 o) {
        return o.y - this.y;
    }
}
public class Main {

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        Pillar2304[] pillars = new Pillar2304[n];

        int maxX = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            pillars[i] = new Pillar2304(l, h);
            maxX = Math.max(maxX, l);
        }

        boolean[] visited = new boolean[maxX + 1];

        Arrays.sort(pillars);

        int area = 0;
        int left = 0;
        int right = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                area += pillars[i].y;
                visited[pillars[i].x] = true;
                left = pillars[i].x;
                right = pillars[i].x + 1;
            } else if(!visited[pillars[i].x]) {
                int count = 0;
                int idx = 0;
                if(pillars[i].x >= right){
                    for (idx = right; idx <= pillars[i].x; idx++) {
                        visited[idx] = true;
                        count++;
                    }
                    right = idx;
                } else if(pillars[i].x <= left) {
                    for (idx = left-1; idx >= pillars[i].x; idx--) {
                        visited[idx] = true;
                        count++;
                    }
                    left = idx+1;
                }
                area += count * pillars[i].y;
            }
        }
        System.out.println(area);
    }
}
