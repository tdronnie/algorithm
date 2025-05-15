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

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Stuff> stuffs = new ArrayList<>();
        List<Integer> Bs = new ArrayList<>();
        List<Integer> Rs = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int BPackTime = Integer.parseInt(st.nextToken());
        int RPackTime = Integer.parseInt(st.nextToken());
        int customers = Integer.parseInt(st.nextToken());

        for (int i = 0; i < customers; i++) {
            st = new StringTokenizer(br.readLine());
            int arriveTime = Integer.parseInt(st.nextToken());
            String color = st.nextToken();
            int num = Integer.parseInt(st.nextToken());

            if(color.equals("B")){
                for (int j = 0; j < num; j++) {
                    stuffs.add(new Stuff("B", arriveTime + (j * BPackTime)));
                }
            }

            if (color.equals("R")) {
                for (int j = 0; j < num; j++) {
                    stuffs.add(new Stuff("R", arriveTime + (j * RPackTime)));
                }
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
