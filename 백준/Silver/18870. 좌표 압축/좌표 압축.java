import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] board = new int[N];

        for(int i=0; i<N; i++) {
            board[i] = Integer.parseInt(st.nextToken());
        }

        int[] copyBoard = Arrays.copyOf(board, board.length);

        TreeSet<Integer> set = new TreeSet<>();
        for(int i=0; i<N; i++) {
            set.add(copyBoard[i]);
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int value = 0;
        for(int i=0; i<N; i++) {
            map.put(set.pollFirst(), value++);
        }

        for(int i=0; i<N; i++){
            sb.append(map.get(board[i])).append(" ");
        }

        System.out.println(sb);
    }
}