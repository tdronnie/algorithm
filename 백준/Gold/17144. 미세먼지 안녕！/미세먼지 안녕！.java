import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int r, c;
	static int[][] map, copy;
	static int[] cleaner; //공기청정기가 위치하는 행들 저장
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		
		map = new int[r][c];
		
		cleaner = new int[2];
		int k=0;
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) cleaner[k++] = i;
			}
		}
		
		//t초 만큼 진행
		for (int i = 0; i < t; i++) {
			//원 배열 복사해서 미세먼지 확산 적용
			copy = new int[r][c];
			for (int a = 0; a < r; a++) {
				for (int b = 0; b < c; b++) {
					copy[a][b] = map[a][b];
				}
			}
			spread();
			
			//공기청정기로 인한 순환
			ativate();
			
			//원 배열에 덮어쓰기
			for (int a = 0; a < r; a++) {
				for (int b = 0; b < c; b++) {
					map[a][b] = copy[a][b];
				}
			}
		}
		
		//t초 후 미세먼지 양 구하기
		int cnt = 0;
		for (int a = 0; a < r; a++) {
			for (int b = 0; b < c; b++) {
				if(map[a][b]>0) cnt += map[a][b];
			}
		}
		System.out.println(cnt);
	}
	private static void ativate() {
		//위쪽 반시계 방향으로 돌리기
		int cr = cleaner[0];
		for (int i = cr-1; i > 0 ; i--) {
			copy[i][0] = copy[i-1][0];
		}
		for (int j = 0; j < c-1; j++) {
			copy[0][j] = copy[0][j+1];
		}
		for (int i = 0; i < cr; i++) {
			copy[i][c-1] = copy[i+1][c-1];
		}
		for (int j = c-1; j > 0; j--) {
			copy[cr][j] = copy[cr][j-1];
		}
		copy[cr][1] = 0;
		//아래쪽 시계방향으로 돌리기
		cr = cleaner[1];
		for (int i = cr+1; i < r-1; i++) {
			copy[i][0] = copy[i+1][0];
		}
		for (int j = 0; j < c-1; j++) {
			copy[r-1][j] = copy[r-1][j+1];
		}
		for (int i = r-1; i > cr; i--) {
			copy[i][c-1] = copy[i-1][c-1];
		}
		for (int j = c-1; j > 0; j--) {
			copy[cr][j] = copy[cr][j-1];
		}
		copy[cr][1] = 0;
//		System.out.println("턴 진행 후!!!!!!!");
//		print();
		
		
		
	}
	private static void print() {
		for (int a = 0; a < r; a++) {
			for (int b = 0; b < c; b++) {
				System.out.print(copy[a][b] + " ");
			}
			System.out.println();
		}
		
	}
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0 ,1, 0, -1};
	private static void spread() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if(map[i][j]!=-1 && map[i][j]!=0) { //미세먼지인 경우
					int cnt=0;
					for (int k = 0; k < 4; k++) {
						int newX = i + dx[k];
						int newY = j + dy[k];
						
						if(newX<0 || newY<0 || newX>= r || newY >= c) continue;
						if(map[newX][newY] == -1) continue;
						
						copy[newX][newY] += map[i][j]/5; //4방 갈 수 있는 곳에 미세먼지 뿌려주기
						cnt++;
					}
					copy[i][j] -= map[i][j]/5*cnt; //뿌려지고 중심에 남은 미세먼지 
				}
				
			}
			
		}
		
	}

}