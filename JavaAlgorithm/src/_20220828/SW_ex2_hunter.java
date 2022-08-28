package _20220828;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SW_ex2_hunter {

	static int[][] map;
	static int N,M;
	static int[] dr = {-1, 0, 1, 0}; //위, 오, 밑, 왼
	static int[] dc = {0, 1, 0, -1};
	static Queue<Hunter> qu;
	static boolean[][][] v;
	static boolean end;
	static int result;
	
	public static void bfs() {
		if(map[0][0] <= 0) { //시작점이 몬스터가 아닐 때 그냥 큐에 넣기 | 양수 몬스터 음수 고객
			
			boolean[][] hunt = new boolean[2][M+1]; //0사용안한다. 0행은 몬스터확인용, 1행은 고객확인용 -> 종료조건
			qu.offer(new Hunter(0,0,0,0,hunt));
			v[0][0][0] = true; //방문처리
		}else { //시작점이 몬스터일 때?
			boolean[][] temp = new boolean[2][M+1];
			
			temp[0][map[0][0]] = true; //현재 몬스터에 해당하는 숫자 잡았다고 표시 -> map에는 표시안해도 될까?
			v[1][0][0] = true; //v에도 표시
			qu.offer(new Hunter(0,0,0,1, temp));
		}
		
		while(!qu.isEmpty()) {
			Hunter cur = qu.poll();
			end = true;
			if(cur.count == M) { //몬스터를 다 잡았고				
				for (int i = 1; i <= M; i++) {
					if(!cur.hunt[1][i]) { //고객중 방문안한 곳 있으면 더 방문해야 한다.
						end = false;
						break;
					}
				}
			}
			if(end) {
				result = Math.min(result, cur.time);
			}
			int sr = cur.r;
			int sc = cur.c;
			for (int i = 0; i < 4; i++) {
				int nr = sr + dr[i];
				int nc = sc + dc[i];
				if(nr>=0&&nc>=0&&nr<N&&nc<N && !v[cur.count][nr][nc]) { //경계확인, visited확인 -> 가까운 몬스터 먼저 잡는게 유리하기 때문에 count만 가지고 확인가능
					if(map[nr][nc] > 0) { //몬스터일 때
						if(!cur.hunt[0][map[nr][nc]]) {
							boolean[][] temp = new boolean[2][M+1];
							
							for (int j = 0; j < temp.length; j++) {
								for (int j2 = 0; j2 < temp[0].length; j2++) {
									temp[j][j2] = cur.hunt[j][j2];
								}
							}
							
							temp[0][map[nr][nc]] = true; //현재 몬스터에 해당하는 숫자 잡았다고 표시 -> map에는 표시안해도 될까?
							v[cur.count+1][nr][nc] = true; //v에도 표시
							qu.offer(new Hunter(nr,nc, cur.time+1, cur.count+1, temp));
						}
					}else if(map[nr][nc]<0) { //고객일 때 -> 큐 비우고 다시 확인?
						boolean[][] temp = new boolean[2][M+1];
						
						
						if(cur.hunt[0][Math.abs(map[nr][nc])]) { //해당하는 몬스터를 잡았으면
							for (int j = 0; j < temp.length; j++) {
								for (int j2 = 0; j2 < temp[0].length; j2++) {
									temp[j][j2] = cur.hunt[j][j2];
								}
							}
							temp[1][Math.abs(map[nr][nc])] = true; //해당하는 고객 방문했다고 표시
							
						}else { //해당 몬스터를 못잡았으면?
							
						}
						v[cur.count][nr][nc] = true; //v에도 표시 => 공통
						qu.offer(new Hunter(nr,nc, cur.time+1, cur.count, temp));
					}else { //일반 이동
						qu.offer(new Hunter(nr,nc,cur.time+1, cur.count, cur.hunt));
						v[cur.count][nr][nc] = true;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			/////////////////////////////////////////////////////////////////////////////////////////////
			N = sc.nextInt();
			map = new int[N][N];
			M = -1; //1~4사이
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt(); //음수는 고객, 양수는 몬스터
					M = Math.max(M, map[i][j]);
				}
			}
			v = new boolean[M+1][N][N]; //0층은 아무것도 안잡았을 때, 1~4마리 잡았을 때 visited
			qu = new LinkedList<>();
			result = Integer.MAX_VALUE;
			
			bfs();
			System.out.println();
			/////////////////////////////////////////////////////////////////////////////////////////////

		}
		
	}
	
	public static class Hunter{
		int r, c, count, time;
		boolean[][] hunt;
		
		public Hunter(int r, int c, int time, int count, boolean[][] hunt) {
			super();
			this.r = r;
			this.c = c;
			this.time = time;
			this.count = count;
			this.hunt = hunt;
		}
	}

}

//시작1243