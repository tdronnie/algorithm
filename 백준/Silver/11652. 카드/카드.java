import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        HashMap<Long, Integer> numbers = new HashMap();

        for (int i = 0; i < n; i++) {
            long num = Long.parseLong(br.readLine());
            numbers.put(num, numbers.getOrDefault(num, 0) + 1);
        }

        long maxValue = 0;
        int maxCount = 0;

        for (Map.Entry<Long, Integer> entry  :numbers.entrySet()){
            Long value = entry.getKey();
            Integer count = entry.getValue();

            if(maxCount < count || maxCount == count && value < maxValue){
                maxValue = value;
                maxCount = count;
            }
        }
        System.out.println(maxValue);
    }
}