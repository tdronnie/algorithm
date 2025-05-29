import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static class Shark implements Comparable<Shark>{
        int row, col, speed, dir, size;

        Shark(int row, int col, int speed, int dir, int size) {
            this.row = row;
            this.col = col;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }

        public int compareTo(Shark shark) {
            return this.row - shark.row;
        }
    }

    static int r, c;
    static ArrayList<Shark> list;
    static int sizeSum = 0;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int num = Integer.parseInt(st.nextToken());

        if(num == 0){
            System.out.println(0);
            return;
        }
        list = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            list.add(new Shark(row, col, speed, dir, size));
        }

        moveAndCatch();
        System.out.println(sizeSum);
    }

    static void moveAndCatch(){
        int currCol = 0;

        while(++currCol <= c){
            Collections.sort(list);
            for (Shark shark : list) {
                if (shark.col == currCol) {
                    sizeSum += shark.size;
                    list.remove(shark);
                    break;
                }
            }
            moveShark();
        }
    }

    static void moveShark() {
        HashMap<String, Shark> check = new HashMap<>();

        for (Shark shark : list) {
            int row = shark.row;
            int col = shark.col;
            int dir = shark.dir;
            int speed = shark.speed;
            int size = shark.size;

            int curr = speed;
            if (dir == 1 || dir == 2) {
                int cycle = (r-1) * 2;
                curr %= cycle;

                for(int i=0; i< curr; i++) {

                    if (dir == 1 && row == 1) {
                        dir = 2;
                    } else if (dir == 2 && row == r) {
                        dir = 1;
                    }
                    if(dir == 1) row--;
                    if(dir == 2) row++;
                }
            } else {
                int cycle = (c-1)*2;
                curr %= cycle;

                for(int i=0; i< curr; i++) {

                    if (dir == 3 && col == c) {
                        dir = 4;
                    } else if (dir == 4 && col == 1) {
                        dir = 3;
                    }
                    if(dir == 3) col++;
                    if(dir == 4) col--;
                }
            }

            String location = row + "," + col;
            if (!check.containsKey(location) || check.get(location).size < size) {
                check.put(location, new Shark(row, col, speed, dir, size));
            }
        }
        list = new ArrayList<>(check.values());
    }
}
