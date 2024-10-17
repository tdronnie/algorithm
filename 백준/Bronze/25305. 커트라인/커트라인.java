import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] scores = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            scores[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(scores);

        sb.append(scores[N-k]);
        System.out.println(sb);

    }
}