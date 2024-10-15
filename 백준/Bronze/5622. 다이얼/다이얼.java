import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static String[] dialNums = {"", "", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int totalTime = 0;

        for(char ch : st.nextToken().toCharArray()){
            totalTime += calculateTimeBy(String.valueOf(ch));
        }
        System.out.println(totalTime);
    }

    static int calculateTimeBy(String dialNum) {
        for(int i=0; i< dialNums.length; i++){
            if(dialNums[i].contains(dialNum)){
                return i + 1;
            }
        }
        return 0;
    }
}