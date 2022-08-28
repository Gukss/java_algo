package _20220827;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_2206_wall {

	static int[] dr = {-1, 0, 1, 0}; //위, 오, 밑, 왼
	static int[] dc = {0, 1, 0, -1};
	static int[][] map;
	static boolean[][][] v;
	static int N,M;
	static Queue<pos> qu;
	static int result;
	
	public static void bfs() {
		
		while(!qu.isEmpty()) {
			pos cur = qu.poll();
						
//			v[cur.wall][cur.r][cur.c] = true; -> 꺼낼 때 방문처리하면 밑에 for문에서 모든 경우 다 큐에 넣는다. -> 메모리 조심
			
			if(cur.r == N-1 && cur.c == M-1) { //목적지 도달했을 때
				result = Math.min(result, cur.len);				
			}
			
			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				if(nr>=0&&nc>=0&&nr<N&&nc<M) {
					if(map[nr][nc] == 1) { //벽 만났을 때
						if(cur.wall == 0 && !v[cur.wall][nr][nc]) { //벽 부순적 없을 때, 벽 부수고 이동한 적 있는 곳이면 이동할 필요 없다.
							qu.offer(new pos(nr, nc, 1, cur.len+1));
							v[1][nr][nc] = true;  //->큐에 넣을 때 방문 처리를 하면 위에 조건에 맞는 곳을 미리 이동하는 것->poll해서는 조건검사만 하게 된다.
						}else { //벽 부순적 있으면 더이상 벽을 부술 수 없다.
							continue;
						}
					}else if(map[nr][nc] == 0) { //벽 아닐 때
						if(!v[cur.wall][nr][nc]) { //방문한 곳 아니면
							qu.offer(new pos(nr, nc, cur.wall, cur.len+1)); //큐에 추가
							v[cur.wall][nr][nc] = true; 
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		v = new boolean[2][N][M]; //0-벽 부순적 없다. 1-벽부순적 있다.
		
		for (int i = 0; i < N; i++) {
			String temp = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = temp.charAt(j) - '0';
			}
		}
		
		qu = new LinkedList<>();
		qu.add(new pos(0,0,0, 1)); //0,0 부터 시작, 벽 부수기 없다-0, 길이 1으로 시작
		v[0][0][0] = true; 
		result = Integer.MAX_VALUE;
		
		bfs();
		
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}
	
	public static class pos{
		int r, c, len;
		int wall;

		public pos(int r, int c, int wall, int len) {
			super();
			this.r = r;
			this.c = c;
			this.wall = wall;
			this.len = len;
		}
	}
}
/*
2 6
010001
000110
정답: 9

6 6
010001
010101
010101
010101
010110
000110
Answer : 21

9 9 
010001000
010101010
010101010
010101010
010101010
010101010
010101010
010101011
000100010
ANSWER : 33

큐에 넣을 때 방문처리를 할 지, 큐에서 꺼낼 때 방문처리를 할 지 생각하기
visited상태를 1개 이상으로 생각할 수 있어야 한다.
 */