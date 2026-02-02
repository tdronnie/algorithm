import java.util.Scanner;

public class Main {

    static StringBuilder sb;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sb = new StringBuilder();
        int n = sc.nextInt();

        move(n, 1, 2, 3);
        System.out.println(sb.length()/4);
        System.out.print(sb);
    }

    static void move(int n, int start, int mid, int end) {

        // 제일 아래 원판밖에 안남음, 목적 장대로 옮기기
        if (n == 1) {
            sb.append(start).append(" ").append(end).append("\n");
            return;
        }

        // 제일 아래 원판 end로 옮기기 위해 위 n-1개 원판 start에서 mid로 옮기기
        move(n - 1, start, end, mid);

        // start에 남은 가장 큰 원판 end로 옮기기
        sb.append(start).append(" ").append(end).append("\n");

        // 나머지 원판들 mid에서 end로 옮기기
        move(n - 1, mid, start, end);
    }
}
