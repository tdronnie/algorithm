import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Set<String> names1 = new HashSet<>();
        Set<String> names2 = new HashSet<>();

        for (int i = 0; i < n; i++) {
            names1.add(br.readLine());
        }
        for (int i = 0; i < m; i++) {
            names2.add(br.readLine());
        }
        Set<String> intersection = new HashSet<>(names1);
        intersection.retainAll(names2);

        ArrayList<String> totalNames = new ArrayList<>(intersection);
        Collections.sort(totalNames);

        System.out.println(totalNames.size());
        for (String name : totalNames) {
            System.out.println(name);
        }
    }
}