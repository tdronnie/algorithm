import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static ArrayList<Queue<String>> list;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            Queue<String> q = new ArrayDeque<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                q.add(st.nextToken());
            }
            list.add(q); //i+1번 앵무새의 문장 큐리스트에 넣기
        }

        String[] L = br.readLine().split(" "); //가능 여부 확인할 문장

        System.out.println(check(list, L) ? "Possible" : "Impossible");


    }

    private static boolean check(ArrayList<Queue<String>> list, String[] l) {
        int idx = 0;
        while (true) {
            boolean find = false;
            for (Queue<String> currQ : list) { //큐 리스트 순회
                //큐의 가장 앞 요소와 문장 l가장 앞 요소와 같은동안 둘다 한칸 씩 뒤로
                while (!currQ.isEmpty() && idx < l.length && currQ.peek().equals(l[idx])) {
                    currQ.poll();
                    idx++;
                    find = true;
                }
            }
            if (!find) //큐리스트 한번 돌릴 때까지 맞는 단어를 찾지 못했다면 불가능
                return false;
            if (idx == l.length) //l문장 모두 순회했다면 큐 모두 비어있는지 확인
                break;
        }
        //비지 않은 큐가 있을 경우 불가능
        for (Queue<String> q: list) {
            if(!q.isEmpty())
                return false;
        }
        return true;

    }
}
