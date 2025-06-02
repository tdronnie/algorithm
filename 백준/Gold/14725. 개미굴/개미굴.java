import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node{
        HashMap<String, Node> child;

        public Node() {
            child = new HashMap<>();
        }
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        Node root = new Node();


        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int kinds = Integer.parseInt(st.nextToken());
            List<String> route = new ArrayList<>();

            for (int j = 0; j < kinds; j++) {
                route.add(st.nextToken());
            }

            insert(root, route);
        }

        showStructure(root, 0);
    }

    static void showStructure(Node root, int depth) {
        TreeMap<String, Node> sortedChildren = new TreeMap<>(root.child);

        for (Map.Entry<String, Node> entry : sortedChildren.entrySet()) {
            String loc = entry.getKey();
            Node child = entry.getValue();

            System.out.print("--".repeat(depth));
            System.out.println(loc);

            showStructure(child, depth + 1);

        }
    }

    static void insert(Node root, List<String> route) {
        Node curr = root;

        for (String loc : route) {
            if (!curr.child.containsKey(loc)) {
                curr.child.put(loc, new Node());
            }
            curr = curr.child.get(loc);
        }
    }
}
