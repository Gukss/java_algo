package _20220827;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_7576_tomato {
	
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0}; //위, 오, 밑, 왼
	static int[] dc = {0, 1, 0, -1};
	static Queue<tomato> qu;
	static boolean[][] v;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		qu = new LinkedList<>();
		v = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					qu.add(new tomato(i, j, 0));
					v[i][j] = true;
				}
			}
		}
		
		
		int result = Integer.MIN_VALUE;
		
		while(!qu.isEmpty()) {
			tomato cur = qu.poll();
			result = Math.max(result, cur.day);
			
			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				if(nr>=0&&nc>=0&&nr<N&&nc<M && !v[nr][nc] && map[nr][nc] == 0) {
					qu.offer(new tomato(nr, nc, cur.day+1));
					map[nr][nc] = cur.day+1;
					v[nr][nc] = true;
					
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					result = -1;
					break;
				}
			}
			if(result == -1) {
				break;
			}
		}
		
		System.out.println(result);
	}
	
	public static class tomato{
		int r, c,day;

		public tomato(int r, int c, int day) {
			super();
			this.r = r;
			this.c = c;
			this.day = day;
		}
	}
}
