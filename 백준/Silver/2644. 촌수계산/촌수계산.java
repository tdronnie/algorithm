import java.io.*;
import java.util.*;

public class Main {
	
	static int n2, rslt=0;
	static ArrayList<Integer>[] arr;
	static boolean[] visited;

	public static void main(String[] args) throws IOException{
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); //사람들
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n1 = Integer.parseInt(st.nextToken()); //촌수계산 숫자1
		n2 = Integer.parseInt(st.nextToken()); //촌수계산 숫자2
		int m = Integer.parseInt(br.readLine()); //관계 수
		
		arr = new ArrayList[n+1];
		visited = new boolean[n+1];
		
		//관계 노드리스트 초기화
		for(int i=1; i<=n; i++) {
			arr[i] = new ArrayList<>();
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken()); //부모
			int child = Integer.parseInt(st.nextToken()); //자식
			//양방향 촌수계산 가능하도록 한다
			arr[parent].add(child);
			arr[child].add(parent);
		}
		
		dfs(n1, 1); //n1부터 n2까지의 촌수계산
		if(rslt == 0)
			System.out.println(-1);
		else
			System.out.println(rslt);
		
	}
	
	public static void dfs(int n, int cnt) {
		visited[n] = true;
		for(int i=0; i<arr[n].size(); i++) {
			int node = arr[n].get(i);
			if(!visited[node]) {
				visited[node] = true;
				if(node == n2) {
					rslt = cnt;
				}
				else {
					dfs(node, cnt+1); //다음 노드와 촌수 보내주기	
				}
			}
		}
	}

}
