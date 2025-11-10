import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if(n == 1){
            System.out.println(1);
            return;
        }
        
        int[] kinds = new int[n + 1];
        kinds[1] = 1;
        kinds[2] = 2;

        for (int i = 3; i <= n; i++) {
            kinds[i] = (kinds[i-2] + kinds[i-1])%10007;
        }

        System.out.println(kinds[n]);

    }
}