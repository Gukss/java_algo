package day004;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_2206_wall {
	
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static boolean[][] visited;
	
	static Queue<int[]> qu;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		map = new int[N][M];
		qu = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			String temp = sc.next();
			for (int j = 0; j < M; j++) {
				map[i][j] = temp.charAt(j) - '0';
			}
		}
		
		qu.add(new int[] {0,0, 1, 1}); //row, col, 벽뚫 가능여부, count, 
		int result = Integer.MAX_VALUE;
		visited = new boolean[N][M];
		
		while(!qu.isEmpty()) {
			int[] pos = qu.poll();
			if(pos[0] == N-1 && pos[1] == M-1) {
				result = Math.min(result, pos[3]);
			}
			for (int i = 0; i < 4; i++) {
				int nr = pos[0] + dr[i];
				int nc = pos[1] + dc[i];
				
				if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue; //map 벗어나는 경우
				if(map[nr][nc] == 1) { //벽일 때
					if(pos[2] == 0) continue; //이미 벽을 뚫고 온 경우
					else pos[2] = 0; //벽을 뚫지 않았을 경우
				}
				if(!visited[nr][nc]) { //방문하지 않은 곳인 경우
					visited[nr][nc] = true;
					qu.add(new int[] {nr, nc, pos[2], pos[3]+1});
				} 
				
			}	
		}
		if(result == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(result);
		
	}
}
