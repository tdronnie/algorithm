import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String nStr = br.readLine();
        int len = nStr.length();
        ArrayList<Integer> arr = new ArrayList<>();

        for(char num : nStr.toCharArray()){
            arr.add(num - '0');
        }

        Collections.sort(arr, Collections.reverseOrder());

        for(int i=0; i<len; i++){
            sb.append(arr.get(i));
        }

        System.out.println(sb);

    }
}