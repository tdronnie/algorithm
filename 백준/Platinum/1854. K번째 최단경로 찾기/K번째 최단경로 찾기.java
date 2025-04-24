import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int k;
	static PriorityQueue<Integer>[] kDis;

	static class City implements Comparable<City> {
		int to, cost;

		public City(int to, int cost) {
			super();
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(City o) {
			return o.cost - this.cost; // k번째 찾기 위한 내림차순 정렬
		}
	}

	static ArrayList<City>[] arr;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		arr = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			arr[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			arr[a].add(new City(b, c));
		}

		// 1번 도시로부터 각 도시들에 대한 거리 pq 필요
		//따라서 각 도시들을 인덱스로 하는 pq배열 만들어서 각 도시에 대한 k번째 최단경로 구할 수 있도록
		kDis = new PriorityQueue[n + 1];
		for (int i = 1; i <= n; i++) {
			kDis[i] = new PriorityQueue<>(k, Collections.reverseOrder());

		}
		// 1번 도시에 대해  도시 i로 가는 k번째 최단경로 찾기
		bfs();
		for (int i = 1; i <= n; i++) {
			if (kDis[i].size() != k) // k번째까지 순위 매겨지지 않은 경우
				System.out.println(-1);
			else
				System.out.println(kDis[i].peek()); // 내림차순으로 가장 끝에 있는 k번째 최단거리
		}

	}

	// 1번 도시부터 대해 bfs 시작
	private static void bfs() {
		//거리 순으로 탐색하기 위한 pq생성, 적은 시간인 도로 먼저 탐색하도록 한다
		PriorityQueue<City> pq = new PriorityQueue<>((c1, c2) -> c1.cost- c2.cost); 
		pq.add(new City(1, 0));
		kDis[1].add(0);

		while (!pq.isEmpty()) {

			City c = pq.poll();
			int cur = c.to;
			int cost = c.cost;

			//갈 도로가 없는 경우 탐색 종료
			if(arr[cur].size() == 0)
				continue; //다음 이어진 도시 탐색
			
			for (int j = 0; j < arr[cur].size(); j++) {
				int next = arr[cur].get(j).to;
				int nextCost = arr[cur].get(j).cost;

				// k=4인 경우
				// 7, 5, 4, 3 -> 6 들어오면 7빠지고 6넣기 -> pq.poll() + pq.add()
				// k 크기의 큐안에 있는 가장 k번째 최단경로보다 더 작은 값이 들어왔을 경우 poll해주고 add해주기
				if (kDis[next].size() == k) {
					if (kDis[next].peek() > cost + nextCost) {
						kDis[next].poll();
						kDis[next].add(cost + nextCost);
						pq.add(new City(next, cost + nextCost)); //k크기 맞춰주기
					}
				}
				else { //아직 큐의 크기가 k가 아닌 경우 그냥 넣어준다
					//k번째 찾는 pq에 넣어주고 bfs위한 pq에도 넣어준다
					kDis[next].add(cost + nextCost);
					pq.add(new City(next, cost + nextCost));
				}
			}
		}
	}

}