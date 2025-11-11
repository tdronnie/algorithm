import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int T = scanner.nextInt();
        int[] nums = new int[T];
        int max = Integer.MIN_VALUE;
        
        for(int test_case = 0; test_case <T; test_case++){
            nums[test_case] = scanner.nextInt();
            max = Math.max(max, nums[test_case]);
        }
        
        int[] kinds = new int[max+1];
        kinds[1] = 1;
        kinds[2] = 2;
        kinds[3] = 4;
        
        for(int i=4; i <=max ; i++){
            kinds[i] = kinds[i-3] + kinds[i-2] + kinds[i-1];
        }
        for(int i=0; i<nums.length; i++){
            sb.append(kinds[nums[i]]).append("\n");
        }
        System.out.println(sb);
    }
}