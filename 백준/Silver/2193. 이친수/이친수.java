import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        long[][] kinds = new long[n + 1][2]; //n자리수까지, 끝의 숫자가 0 or 1

        kinds[1][1] = 1;

        for (int i = 2; i <= n; i++) {
            kinds[i][0] = kinds[i-1][0] + kinds[i-1][1];
            kinds[i][1] = kinds[i-1][0];
        }

        System.out.println(kinds[n][0] + kinds[n][1]);

    }
}
