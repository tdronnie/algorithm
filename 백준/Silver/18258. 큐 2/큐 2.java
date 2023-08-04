import java.io.IOException;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine()); // 명령의 수

        Deque<Integer> q = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "push": //push 입력, 큐에 넣기
                    int val = Integer.parseInt(st.nextToken());
                    q.add(val);
                    break;

                case "pop": //pop 입력, 큐 가장 앞에있는 정수 빼기. 비어있을 떄는 -1 출력
                    sb.append(q.isEmpty() ? -1 : q.removeFirst()).append("\n");
                    break;

                case "size": //size 출력
                    sb.append(q.size()).append("\n");
                    break;

                case "empty": //큐 비어있으면 1 아니면 0출력
                    sb.append(q.isEmpty() ? 1 : 0).append("\n");
                    break;

                case "front": //큐 가장 앞에 있는 값 출력, 큐 비어있을 때는 -1 출력
                    sb.append(q.isEmpty() ? -1 : q.peekFirst()).append("\n");
                    break;

                case "back": //큐 가장 뒤에 있는 값 출력, 큐 비어있을 때는 -1 출력
                    sb.append(q.isEmpty() ? -1 : q.peekLast()).append("\n");
                    break;
            }
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();

    }

}
