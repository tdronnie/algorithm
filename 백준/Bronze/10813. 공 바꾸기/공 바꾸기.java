import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb;
    static int[] basket;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        basket = new int[n];
        for(int i=0; i<n; i++){
            basket[i] = i + 1;
        }

        for (int idx = 0; idx < m; idx++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            swap(i-1, j-1);
        }

        printBasket();

    }

    public static void printBasket(){
        for(int ball : basket){
            sb.append(ball).append(" ");
        }
        System.out.println(sb);
    }

    public static void swap(int i, int j) {
        int tmp = basket[i];
        basket[i] = basket[j];
        basket[j] = tmp;
    }
}