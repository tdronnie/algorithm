import java.io.*;
import java.util.*;
public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n + m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = n; i < n+m; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // 정렬된 배열이므로 한번 merge 수행
        merge(arr, 0, n-1, n+m-1);

        System.out.print(sb);
    }

    // 두 부분 적절히 합치기
    static void merge(int[] arr, int start, int mid, int end) {
        int index1 = start;
        int index2 = mid + 1;
        int current = start;

        while (index1 <= mid && index2 <= end) {
            if (arr[index1] <= arr[index2]) {
                sb.append(arr[index1++]).append(" ");
            } else {
                sb.append(arr[index2++]).append(" ");
            }
        }

        // 남아있는 요소 삽입
        if(index1 <= mid){
            for (int i = index1; i <= mid; i++) {
                sb.append(arr[i]).append(" ");
            }
        }

        if (index2 <= end) {
            for (int i = index2; i <= end; i++) {
                sb.append(arr[i]).append(" ");
            }
        }
    }
}
