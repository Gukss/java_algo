package _20240101;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_14502_lab{
	public static int N,M; //N*M
	public static int[][] map;
	public static LinkedList<Pos> initVirus;
	public static Queue<Pos> qu;
	public static Pos[] sel;
	public static LinkedList<Pos> area;
	public static int[][] cpmap;
	public static int[] dr = {-1,0,1,0}; //위오아왼
	public static int[] dc = {0,1,0,-1}; //위오아왼
	public static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		//벽 설치하기 => 조합
		//바이러스 퍼뜨리기 => bfs
		//빈 공간 숫자 세기
		//=> 반복

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sel = new Pos[3]; //벽은 3개만 세울 수 있다.

		initVirus = new LinkedList<Pos>();
		area = new LinkedList<>();

		map = new int[N][M];
		for(int i=0;i<N;i++){
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++){
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2){ //바이러스면 queue에 넣기
					initVirus.add(new Pos(i, j)); //이 큐는 for문 돌면서 bfs 하기
				} else if(map[i][j] == 0){
					area.add(new Pos(i, j));
				}
			}
		}

		//다 받았으면 벽 세워야한다.
		comb(0, 0);
		System.out.println(max);
	}

	public static void comb(int idx, int start){
		if(idx == sel.length){
			//빈 칸은 3개 이상이다.
			//벽 세울 공간 다 선택했고, map을 copy해서 벽 세우고, 바이러스 퍼트리면 된다.
			cpmap = new int[N][M];
			cpmap();
			mkwall();
			qu = new LinkedList<>();
			bfs();
			count();
			
			return;
		}
		for(int i=start;i<area.size();i++){ //조합 코드 때문에 틀렸다.
			sel[idx] = area.get(i);
			comb(idx+1, i+1);
		}
	}

	public static void count(){
		int c = 0;
		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				if(cpmap[i][j] == 0){
					c += 1;
				}
			}
		}
		max = Math.max(max, c);
	}

	public static void bfs(){
		//시작은 initVirus에 바이러스 위치가 들어있다. 해당 바이러스를 꺼내서 qu에 넣고, 시작한다.
		for(Pos x: initVirus){
			qu.add(x);
		}
		while(!qu.isEmpty()){
			Pos cur = qu.poll();
			int r = cur.r;
			int c = cur.c;
			for(int i=0;i<4;i++){
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(nr>=0&&nc>=0&&nr<N&&nc<M && cpmap[nr][nc] == 0){ //빈 곳만
					cpmap[nr][nc] = 2;
					qu.add(new Pos(nr, nc));
				}
			}
		}
	}

	public static void mkwall(){
		for(int i=0;i<sel.length;i++){
			int r = sel[i].r;
			int c = sel[i].c;
			cpmap[r][c] = 1;
		}
	}

	public static void cpmap(){
		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				cpmap[i][j] = map[i][j];
			}
		}
	}

	public static class Pos {
		int r,c;
		public Pos(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
}