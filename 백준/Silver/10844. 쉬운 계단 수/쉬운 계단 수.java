import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        long[][] kinds = new long[n + 1][10];

        for (int i = 1; i < 10; i++) {
            kinds[1][i] = 1;
        }

        for(int i=2; i<=n; i++){
            for(int j=0; j < 10; j++){
                if (j == 0){
                    kinds[i][j] = kinds[i-1][1];
                } else if(j == 9){
                    kinds[i][j] = kinds[i-1][8];
                } else {
                    kinds[i][j] = kinds[i-1][j-1] + kinds[i-1][j+1];
                }
                kinds[i][j] %= 1000000000;
            }
        }

        long count = 0;
        for (int i = 0; i < 10; i++) {
            count += kinds[n][i] % 1000000000;
        }
        System.out.println(count % 1000000000);
    }
}
