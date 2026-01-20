import java.io.*;
import java.util.*;
public class Main {

    static int ax, ay, bx, by, cx, cy, dx, dy;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        ax = Integer.parseInt(st.nextToken());
        ay = Integer.parseInt(st.nextToken());
        bx = Integer.parseInt(st.nextToken());
        by = Integer.parseInt(st.nextToken());
        cx = Integer.parseInt(st.nextToken());
        cy = Integer.parseInt(st.nextToken());
        dx = Integer.parseInt(st.nextToken());
        dy = Integer.parseInt(st.nextToken());

        // 임의의 두 시점의 거리를 비교해서 거리가 더 먼 시점보다 그 밖의 시점은 제외하면서 짧은 거리 찾아가기
        // 임의로 시점을 100개로 나누고 두 사람의 거리 구하기
        // 대략 3등분 필요

        double low = 0;
        double high = 100;
        double minDis = 20000; // 가능한 최대 거리 20000 (좌표값 1이상 10000이하)

        while (low < high) {
            double time1 = low + (high - low) /3;
            double time2 = low + (high - low) * 2/3;

            double[] minho1 = getMinhoLoc(time1);
            double[] minho2 = getMinhoLoc(time2);
            double[] gangho1 = getGanghoLoc(time1);
            double[] gangho2 = getGanghoLoc(time2);

            double part1 = getDistance(minho1, gangho1);
            double part2 = getDistance(minho2, gangho2);


            if(part1 > part2){ // part2 부분이 더 가까워지고 있음, time1을 기준으로 바깥 시점 버리기
                low = time1;
                // 거리 더 짧은 쪽 기록
                minDis = part2;
            } else { // part1 부분이 더 가까워지고 있음, time2를 기준으로 바깥 시점 버리기
                high = time2;
                // 거리 더 짧은 쪽 기록
                minDis = part1;
            }

            if(Math.abs(part1 - part2) < 1e-6) break; // 오차 10^-6보다 작아지면 끝내기

        }

        System.out.print(minDis);

    }

    static double getDistance(double[] a, double[] b) {
        return Math.sqrt(Math.pow(b[0] - a[0], 2) + Math.pow(b[1] - a[1], 2));
    }

    // 특정 시점에 점의 위치 (x, y)
    static double[] getMinhoLoc(double time) {
        return new double[] {(ax + (bx - ax) * time / 100), (ay + (by - ay) * time / 100)};

    }

    static double[] getGanghoLoc(double time) {
        return new double[] {(cx + (dx - cx) * time / 100), (cy + (dy - cy) * time / 100)};

    }
}
