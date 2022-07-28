package day002;

import java.util.LinkedList;
import java.util.Scanner;

public class Bj_1926_picture {
	static LinkedList<Integer> qu = new LinkedList<>();
	static boolean[][] visited;
	static int[][] pic;
	static int n, m;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		pic = new int[n][m];
		visited = new boolean[n][m];
		
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				pic[r][c] = sc.nextInt();
			}
		}
		
		int[] dr = {1,0,-1,0}; //위,오른,아래,왼
		int[] dc = {0,1,0,-1};
		
		
		int pic_count = 0; //그림 개수 저장
		int area = 0; //그림 넓이 저장
		int max = 0; //그림 넓이 중 최대값
		
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				if(pic[r][c] == 0 || (visited[r][c])) { //그림이 없거나 방문했던 좌표는 생략
					continue;
				}
				visited[r][c] = true; //방문 처리
				pic_count++; //그림 개수 증가
				
				qu.add(r);
				qu.add(c); //큐에 좌표 삽입
				area = 0; //넓이 0으로 초기화
				while(!qu.isEmpty()) { //큐가 비어있지 않으면
					int p_r = qu.poll();
					int p_c = qu.poll(); //큐에서 좌표 꺼내기
					area++;
					for (int i = 0; i < 4; i++) {
						int nr = p_r + dr[i];
						int nc = p_c + dc[i]; //사방 확인하기
						if(nr < 0 || nr >= n || nc < 0 || nc >= m) {
							continue; //그림에서 벗어나면 넘기기
						}
						if(!visited[nr][nc] && (pic[nr][nc] == 1)) { //방문하지 않았고 1이면 
							qu.add(nr); //큐에 좌표 넣기
							qu.add(nc);
							visited[nr][nc] = true; //방문확인
						}
					}
				}
				max = Math.max(area, max); //넓이 최댓값 구하기
					
			}
		}
		System.out.println(pic_count);
		System.out.println(max);
	}

}