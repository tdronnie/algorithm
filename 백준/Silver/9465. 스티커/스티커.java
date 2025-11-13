import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        StringBuilder sb = new StringBuilder();

        for(int t = 0; t<T; t++){
            int n = scanner.nextInt(); scanner.nextLine();
            int[][] sticker = new int[2][n];
            int[][] sum = new int[2][n];

            for(int i=0; i<2; i++){
                String[] splits = scanner.nextLine().split(" ");
                for(int j=0; j<n; j++){
                    sticker[i][j] = Integer.parseInt(splits[j]);
                }
            }
            
            if(n == 1){
                sb.append(Math.max(sticker[0][0], sticker[1][0])).append("\n");
                continue;
            }

            sum[0][0] = sticker[0][0];
            sum[1][0] = sticker[1][0];
            sum[0][1] = sticker[1][0] + sticker[0][1];
            sum[1][1] = sticker[0][0] + sticker[1][1];

            for(int i=2; i<n; i++){
                sum[0][i] = Math.max(sum[1][i-2], sum[1][i-1]) + sticker[0][i];
                sum[1][i] = Math.max(sum[0][i-2], sum[0][i-1]) + sticker[1][i];
            }
            sb.append(Math.max(sum[0][n-1], sum[1][n-1])).append("\n");
        }
        System.out.print(sb);
    }
}
