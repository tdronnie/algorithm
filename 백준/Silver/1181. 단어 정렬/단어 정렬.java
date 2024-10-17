import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        HashSet<String> set = new HashSet<>();
        int N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++){
            set.add(br.readLine());
        }

        //Comparator버전
        ArrayList<String> arr = new ArrayList<>(set);

        Collections.sort(arr, new Comparator<>() {
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) {
                    return o1.compareTo(o2);
                }
                return Integer.compare(o1.length(), o2.length());
            }
        });

        for(String s : arr){
            sb.append(s).append("\n");
        }

        System.out.println(sb);
    }
}