import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Stuff implements Comparable<Stuff>{
        String color;
        int packingTime;

        Stuff(String color, int packingTime) {
            this.color = color;
            this.packingTime = packingTime;
        }

        public int compareTo(Stuff stuff) {
            if (this.packingTime == stuff.packingTime) {
                return this.color.compareTo(stuff.color);
            }
            return this.packingTime - stuff.packingTime;
        }
    }

    static class Order {
        int arriveTime;
        String color;
        int num;

        Order(int arriveTime, String color, int num) {
            this.arriveTime = arriveTime;
            this.color = color;
            this.num = num;
        }
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Stuff> stuffs = new ArrayList<>();
        List<Order> orders = new ArrayList<>();
        List<Integer> Bs = new ArrayList<>();
        List<Integer> Rs = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int BPackTime = Integer.parseInt(st.nextToken());
        int RPackTime = Integer.parseInt(st.nextToken());
        int customers = Integer.parseInt(st.nextToken());

        int BEndTime = 0;
        int REndTime = 0;

        for (int i = 0; i < customers; i++) {
            st = new StringTokenizer(br.readLine());
            int arriveTime = Integer.parseInt(st.nextToken());
            String color = st.nextToken();
            int num = Integer.parseInt(st.nextToken());

            orders.add(new Order(arriveTime, color, num));
        }

        for (Order order : orders) {
            int arriveTime = order.arriveTime;
            String color = order.color;
            int num = order.num;
            int start;

            if (color.equals("B")) {
                start = Math.max(arriveTime, BEndTime);

                for (int j = 0; j < num; j++) {
                    stuffs.add(new Stuff("B", start));
                    start += BPackTime;
                }
                BEndTime = start;
            } else {
                start = Math.max(arriveTime, REndTime);

                for (int j = 0; j < num; j++) {
                    stuffs.add(new Stuff("R", start));
                    start += RPackTime;
                }
                REndTime = start;
            }
        }

        Collections.sort(stuffs);
        int num = 1;

        for (Stuff s : stuffs) {
            if (s.color.equals("B")) {
                Bs.add(num++);
            } else {
                Rs.add(num++);
            }
        }

        System.out.println(Bs.size());
        for (int n : Bs) {
            System.out.print(n + " ");
        }
        System.out.println();

        System.out.println(Rs.size());
        for (int n : Rs) {
            System.out.print(n + " ");
        }
    }
}
