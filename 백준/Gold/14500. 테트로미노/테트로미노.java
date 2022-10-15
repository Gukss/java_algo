import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0}; //위, 오, 밑, 왼
	static int[] dc = {0, 1, 0, -1}; //위, 오, 밑, 왼
	static boolean[][] v;
	static int result; 
	static int[] exdr = {0, -1, 0, 1, 0}; //자신, 위, 오, 밑, 왼
	static int[] exdc = {0, 0, 1, 0, -1}; //자신, 위, 오, 밑, 왼
	
	public static void dfs(int r, int c, int depth, int sum) {
		if(depth == 3) {
			result = Math.max(result, sum);
			return;
		}
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(nr>=0&&nr<N&&nc>=0&&nc<M && !v[nr][nc]) {
				v[nr][nc] = true;
				dfs(nr,nc,depth+1, sum+map[nr][nc]);
				v[nr][nc] = false;
			}
		}
	}
	
	public static void excheck() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 1; k < 5; k++) { //방향
					int sum = 0;
					boolean flag = false;
					for (int l = 0; l < 5; l++) { //테트로미노 모양따라
						if(k!=l) {
							int nr = i + exdr[l];
							int nc = j + exdc[l];
							if(nr>=0&&nr<N&&nc>=0&&nc<M) {
								sum += map[nr][nc];
								flag = true;
							}else {
								flag = false;
								break;
							}
						}
					}
					if(flag) {						
						result = Math.max(sum, result);
					}
				}
			}
		}
	}
	
	public static void start() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				v[i][j] = true;
				dfs(i,j, 0, map[i][j]);
				v[i][j] = false;
			}
		}
		excheck();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		v = new boolean[N][M];
		result = Integer.MIN_VALUE;
		start();
		System.out.println(result);
	}
}
/*
4 4
0 2 0 0
0 9 0 0
0 1 0 0
0 1 0 0
ans: 13
 */ 
