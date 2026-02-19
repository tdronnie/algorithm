import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String channelStr = br.readLine();
        int channel = Integer.parseInt(channelStr);
        int notWorkCount = Integer.parseInt(br.readLine());
        boolean[] isNotWorking = new boolean[10];
        if(notWorkCount > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<notWorkCount; i++) {
                int num = Integer.parseInt(st.nextToken());
                isNotWorking[num] = true;
            }
        }

        // 모든 버튼 고장, 채널 이동하는 방법밖에 없음
        if (notWorkCount == 10) {
            System.out.print(Math.abs(100 - channel));
            return;
        }

        // 단순 채널 이동 방법으로 초기화
        int minCount = Math.abs(100 - channel);

        // 0번부터 500000번까지 +나 -를 눌러 이동하려는 채널까지 최소로 가는 채널번호 찾기
        // 만약 500000번을 가기 위해 사용할 수 있는 버튼의 수가 한정적이라면 500000 이상의 채널을 먼저 눌러야 할 수도 있으므로 모든 자리를 9로 채우는 최대 범위 고려 999999
        for (int i = 0; i < 1000000; i++) {
            boolean isValid = true;
            int num = i;
            // 0번
            if(num == 0){
                if(isNotWorking[num]){
                    continue;
                }
            }

            while(num > 0){
                if(isNotWorking[num % 10]){
                    isValid = false;
                    break;
                }
                num /= 10;
            }

            // 채널길이와 (+나 -로 이동 횟수)를 합친 값
            if(isValid){
                // 숫자 직접 입력 카운트
                int count = Integer.toString(i).length();
                count += Math.abs(i-channel);
                minCount = Math.min(minCount, count);
            }
        }

        System.out.print(minCount);

    }
}
