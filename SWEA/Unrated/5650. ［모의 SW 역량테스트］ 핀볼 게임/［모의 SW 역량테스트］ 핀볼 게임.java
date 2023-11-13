import java.io.*;
import java.util.*;

import java.io.IOException;

public class Solution {
	
	static int n, score, max;
	static int[][] map;
	static ArrayList<int[]>[] worm;
	public static void main(String[] args) throws IOException{
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(br.readLine().trim());
			map = new int[n][n];
			worm = new ArrayList[11]; //웜홀 6번부터 10번까지 존재
			//웜홀 저장할 리스트 배열 초기화
			for (int i = 0; i < worm.length; i++) {
				worm[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					//웜홀인 경우 저장
					if(map[i][j] >= 6 && map[i][j]<=10) {
						worm[map[i][j]].add(new int[] {i, j}); //웜홀 숫자 인덱스에 대해 웜홀 좌표저장, 두개씩 저장된다
					}
				}
			}
			
			max=0;
			//임의의  빈공간 좌표에서 출발해서 스코어 최댓값 찾기
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(map[i][j] == 0) {
						//사방탐색 시작
						for (int k = 0; k < 4; k++) {
							score = 0;
							findMaxScore(i, j, k);
						}
					}
				}
				
			}
			
			sb.append("#").append(tc).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
		
		
		
	}
	
	static int[] dx = {-1,0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	
	private static void findMaxScore(int i, int j, int d) {
		
		int newX = i;
		int newY = j;
		while(true){
			newX += dx[d];
			newY += dy[d];
			
			//벽에 부딪히는 경우 or 블록의 수직벽에 부딪히는 경우 -> 다시 출발했던 곳으로 돌아감, score * 2 + 1해주고 끝
			if(!isValid(newX, newY) || (d==0 || d==1) && map[newX][newY] == 1 || (d==1 || d==2) && map[newX][newY] == 2 || (d==2 || d==3) && map[newX][newY] == 3 || (d==0 || d==3) && map[newX][newY] == 4 ||map[newX][newY] == 5) {
				score = score * 2 + 1;
				break;
			}

			//블랙홀이거나 나의 좌표 돌아왔을 때 종료
			if(map[newX][newY] == -1 || newX==i && newY == j)
				break;
			
			//0인 경우 그냥 지나가기
			if(map[newX][newY] == 0) continue;
			
			//웜홀인 경우
			if(map[newX][newY]>=6 && map[newX][newY]<=10) {
				int[] chLoc = changeLoc(map[newX][newY], newX, newY);
				newX = chLoc[0];
				newY = chLoc[1];
				//위치만 달라지고 방향은 그대로
				continue;
				
			}
			//그 외의 경우, 블록의 45도 벽 만났을 경우 score처리 및 방향 전환
			if(map[newX][newY] == 1) {
				if(d==2) d = 1;
				else d = 0;
			}
			if(map[newX][newY] == 2) {
				if(d==3) d = 2;
				else d = 1;
			}
			if(map[newX][newY] == 3) {
				if(d==1) d=2;
				else d = 3;
			}
			if(map[newX][newY] == 4) {
				if(d==1) d=0;
				else d = 3;
			}
			score++;
			
		}
		
		max = Math.max(max, score);
		
		
	}


	private static int[] changeLoc(int wormN, int r, int c) {
		if(worm[wormN].get(0)[0] == r && worm[wormN].get(0)[1] == c)
			return worm[wormN].get(1);
		else
			return worm[wormN].get(0);
				
	}


	private static boolean isValid(int newX, int newY) {
		return newX>=0 && newY>=0 && newX<n && newY <n;
	}
}