import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class Main {

    static class TreeNode {
        HashMap<Integer, TreeNode> child;
        boolean endCheck;

        public TreeNode() {
            child = new HashMap<>();
            endCheck = false;
        }
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int test = Integer.parseInt(br.readLine());

        for (int i = 0; i < test; i++) {
            TreeNode tree = new TreeNode();
            boolean pass = true;
            int numbers = Integer.parseInt(br.readLine());
            String[] numStr = new String[numbers];

            for (int j = 0; j < numbers; j++) {
                numStr[j] = br.readLine();
            }

            Arrays.sort(numStr, Comparator.comparing(String::length));

            for (int k = 0; k < numbers; k++) {
                if(!canUse(tree, numStr[k].toCharArray())){
                    pass = false;
                }
            }

            if (pass) {
                sb.append("YES");
            } else {
                sb.append("NO");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static boolean canUse(TreeNode tree, char[] list) {
        TreeNode currTree = tree;

        for (int i=0; i<list.length; i++) {
            int num = list[i] - '0';
            if(currTree.endCheck){
                return false;
            }
            if (!currTree.child.containsKey(num)) {
                currTree.child.put(num, new TreeNode());
            }
            currTree = currTree.child.get(num);
            if (i == list.length - 1) {
                currTree.endCheck = true;
            }
        }

        return true;
    }
}
