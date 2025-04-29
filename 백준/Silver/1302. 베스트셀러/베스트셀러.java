import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        HashMap<String, Integer> books = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String title = br.readLine();
            books.put(title, books.getOrDefault(title, 0) + 1);
        }

        int max = 0;
        String bestseller = "";
        for (String title : books.keySet()) {
            int count = books.get(title);
            if (max < count || max == count && title.compareTo(bestseller) < 0) {
                max = count;
                bestseller = title;
            }
        }

        System.out.println(bestseller);
    }
}