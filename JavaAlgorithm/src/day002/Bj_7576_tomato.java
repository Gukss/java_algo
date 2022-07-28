package day002;

import java.util.LinkedList;
import java.util.Scanner;

public class Bj_7576_tomato {

	public static LinkedList<int[]> qu = new LinkedList<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
		int N = sc.nextInt();
		
		int[][] box = new int[N][M];
		int dr[] = {-1, 0, 1, 0}; //사방
		int dc[] = {0, 1, 0, -1};
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				box[r][c] = sc.nextInt();
				if(box[r][c] == 1) { //익은 토마토일 때
					qu.add(new int[] {r,c}); //qu에  add
				}
			}
		}
		
		while(!qu.isEmpty()) {
			int[] pos = qu.poll();
			for (int i = 0; i < 4; i++) { //사방
				int nr = pos[0] + dr[i];
				int nc = pos[1] + dc[i];
				if(nr < 0 || nr >=N || nc < 0 || nc >= M) { //박스 크기 벗어나면
					continue; //다음 좌표 확인
				}
				if(box[nr][nc] == 0) {
					qu.add(new int[] {nr, nc}); //익지 않은 토마토면 qu에 추가
					box[nr][nc] = box[pos[0]][pos[1]] + 1; //이전 좌표의 값(날짜)에 +1
				}
			}
		}
				
		int result = Integer.MIN_VALUE;
		for (int r = 0; r < N; r++) { //box를 차례대로 탐색
			for (int c = 0; c < M; c++) {
				if(box[r][c] == 0) {
					System.out.println("-1");
					return;
				}
				result = Math.max(result, box[r][c]);
			}
		}
		if(result == 1) {
			System.out.println("0");
		}else {
			System.out.println(result-1); //날짜가 1부터 시작했으므로 -1
		}
		
	}

}