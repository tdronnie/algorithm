import java.io.*;
import java.util.*;

public class Solution {

	static int n;
	static int[][] board;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			board = new int[n + 1][n + 1];
			// 초기 세팅
			for (int i = n / 2; i <= n / 2 + 1; i++) {
				for (int j = n / 2; j <= n / 2 + 1; j++) {
					if (i == j)
						board[i][j] = 2; // 백돌
					else
						board[i][j] = 1;
				}
			}

			int cnt = 4; // 이미 놓은 돌 4개
			boolean flag = false;
			// m번 돌 놓기 시행
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int col = Integer.parseInt(st.nextToken());
				int row = Integer.parseInt(st.nextToken());
				int color = Integer.parseInt(st.nextToken());

				// 이미 모든 보드에 돌을 놓았을 경우 게임 끝
				if (cnt == n * n)
					break;

				// 이미 돌 있는 경우 놓기 불가
				if (board[row][col] != 0)
					continue;

				if (canPutStone(row, col, color)) {
					board[row][col] = color;
					cnt++; // 놓은 돌 수++
					flag = false;
				} else { // 돌을 놓을 수 없는 경우
					if (flag) // 두번 연속으로 놓을 곳 없는 경우 게임 끝
						break;
					flag = true;
				}
			}

			int cnt1 = 0, cnt2 = 0;
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (board[i][j] == 1)
						cnt1++;
					else if (board[i][j] == 2)
						cnt2++;
				}
			}
			sb.append("#").append(tc).append(" ").append(cnt1).append(" ").append(cnt2).append("\n");
		}
		System.out.println(sb);
	}

	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };

	private static boolean canPutStone(int row, int col, int color) {
		// 8방 탐색해서 상대돌 우리돌로 변경할 수 있는 위치인지 확인
		boolean can = false;
		for (int k = 0; k < 8; k++) {
			// 한칸 씩 먼저 탐색
			int newR = row + dx[k];
			int newC = col + dy[k];

			// 범위 밖이라면 탐색 종료
			if (!isValid(newR, newC))
				continue;

			// 같은 색의 돌이거나 빈 곳일 때는 탐색 종료
			if (board[newR][newC] == color || board[newR][newC] == 0)
				continue;

			// 다른 색 돌일 경우 끝에 같은 돌 나오는지 확인, 나오면 중간의 다른 색돌 우리돌 색으로 바꿔주고 돌 놓기 가능하다고 리턴
			ArrayList<int[]> arr = new ArrayList<>();
			arr.add(new int[] { newR, newC });
			while (true) {
				newR += dx[k];
				newC += dy[k];
				// 범위 나가는지 혹은 빈공간인지
				if (!isValid(newR, newC) || board[newR][newC] == 0)
					break;

				// 같은 색 돌 나오면 끝
				if (board[newR][newC] == color) {
					// 지금까지 저장해둔 상대 돌 우리돌로 바꾸기
					for (int[] val : arr) {
						board[val[0]][val[1]] = color;
					}
					can = true; // 상대돌 바꿀 수 있음 표시
					break; // 다른 방향 탐색
				}
				arr.add(new int[] { newR, newC });
			}

		}
		return can;
	}

	private static boolean isValid(int newR, int newC) {
		return newR > 0 && newC > 0 && newR <= n && newC <= n;
	}
}