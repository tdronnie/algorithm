import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        int answer = 0;

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<a; i++){
            set1.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<b; i++){
            set2.add(Integer.parseInt(st.nextToken()));
        }

        Set<Integer> setTmp = new HashSet<>(set1);

        set1.removeAll(set2);
        answer += set1.size();
        set2.removeAll(setTmp);
        answer += set2.size();

        System.out.println(answer);
    }
}