import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] arr;
    static int[] population;
    static int min = Integer.MAX_VALUE;
    static int n;
    static boolean[] visitedA;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        population = new int[n + 1]; //각 구역의 인구 수

        arr = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++)
            arr[i] = new ArrayList<>();

        //인구저장
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }
        //인접 도시 저장
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            if (num != 0) {
                for (int j = 0; j < num; j++) {
                    arr[i].add(Integer.parseInt(st.nextToken()));
                }

            }
        }
        boolean[] visited = new boolean[n + 1];
        separate(1, visited);

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    private static void separate(int idx, boolean[] visited) {
        if (idx == n+1) {
            //visited로 나눠진 두 분류 인접한지 확인, 인구 수 미리 구해놓기
            ArrayList<Integer> group1 = new ArrayList<>();
            ArrayList<Integer> group2 = new ArrayList<>();
            int pop1 = 0, pop2 = 0;
            for (int i = 1; i <= n; i++) {
                if (visited[i]) {
                    group1.add(i);
                    pop1 += population[i];
                } else {
                    group2.add(i);
                    pop2 += population[i];
                }
            }
            visitedA = new boolean[n + 1];
            //두 구역으로 나누어지는 경우만 인접여부 확인하기
            if (!group1.isEmpty() && !group2.isEmpty()) {
                //두 구역 인접해있는지 확인 및 방문 체킹
                checkClose(group1);
                checkClose(group2);
                //그룹 안의 구역 중 방문하지 않은 구역 있다면 인접하지 않은 도시 존재
                for (int i = 1; i <= n; i++) {
                    if (!visitedA[i])
                        return;
                }
                //두 구역 서로 인접해있을 경우 인구 차이 구하기
                min = Math.min(min, Math.abs(pop1 - pop2));
            }
            return;
        }
        visited[idx] = true;
        separate(idx + 1, visited);
        visited[idx] = false;
        separate(idx + 1, visited);
    }

    private static void checkClose(ArrayList<Integer> group) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(group.get(0));
        visitedA[group.get(0)] = true;

        while (!q.isEmpty()) {
            int val = q.poll();
            for (int i=0; i<arr[val].size(); i++) {
                int next  =arr[val].get(i);
                if (!visitedA[next] && group.contains(next)) {
                    visitedA[next] = true;
                    q.add(next);
                }
            }
        }
    }
}