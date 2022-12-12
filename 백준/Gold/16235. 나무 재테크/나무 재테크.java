import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N,M,K,x,y,z;
	static int[][] map, feed, tempfeed;
	static Deque<Tree> dq, tempdq;
	static List<Tree> newlist;
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1}; //위 부터 시계방향
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1}; //위 부터 시계방향
	
	public static void spring() {
		tempdq = new LinkedList<>();
		tempfeed = new int[N+1][N+1];
		
		for(Tree cur:dq) {
			if(map[cur.r][cur.c] - cur.age>=0) { //양분이 있는 경우 => 나이만큼 양분을 먹고, 나이 1증가한다.
				map[cur.r][cur.c] -= cur.age;
				cur.age += 1;
				tempdq.add(cur);
			}else { //양분이 나이만큼 없는 경우 => 바로 죽는다. => 죽이는 collection에 담아서 한번에 죽여야한다. => LinkedList에서는 remove하는 과정을 빼야한다.
				tempfeed[cur.r][cur.c] += cur.age/2;				
			}
		}
		dq = tempdq; //summer 에서는 map만 바꿔주면 된다.
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				map[i][j] += tempfeed[i][j];
			}
		}
	}
	
//	public static void summer() {
//		for(Tree cur:deadlist) {
//			dq.remove(cur); //dq에서 삭제
//			map[cur.r][cur.c] += cur.age/2;
//		}
//	}
	
	public static void autumn() {
		newlist = new ArrayList<>();
		for(Tree cur:dq) {
			if(cur.age % 5 == 0) { //나무 나이가 5의 배수일 때
				int r = cur.r;
				int c = cur.c;
				for(int i=0;i<8;i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					if(nr>=1&&nr<=N&&nc>=1&&nc<=N) { //경계처리
						newlist.add(new Tree(nr, nc, 1));
					}
				}
			}
		}
		for(Tree cur:newlist) {
			dq.addFirst(cur);
		}
	}
	
	public static void winter() {
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				map[i][j] += feed[i][j];
			}
		}
	}
	
	public static void start() {
		for(int i=0;i<K;i++) {			
			spring();
			autumn();
			winter();
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //N*N배열
		M = Integer.parseInt(st.nextToken()); //나무 개수
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1]; //배열은 1부터 시작
		for(int i=1;i<=N;i++) { //가장 처음 양분은 5로 차있다.
			Arrays.fill(map[i], 5);
		}
		
		feed = new int [N+1][N+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				feed[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dq = new LinkedList<>();
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			dq.add(new Tree(r, c, age));
		}
		
		start();
		System.out.println(dq.size());
	}
	
	public static class Tree{
		int r,c,age;

		public Tree(int r, int c, int age) {
			super();
			this.r = r;
			this.c = c;
			this.age = age;
		}
	}
}
/*
10 1 1000
100 100 100 100 100 100 100 100 100 100
100 100 100 100 100 100 100 100 100 100
100 100 100 100 100 100 100 100 100 100
100 100 100 100 100 100 100 100 100 100
100 100 100 100 100 100 100 100 100 100
100 100 100 100 100 100 100 100 100 100
100 100 100 100 100 100 100 100 100 100
100 100 100 100 100 100 100 100 100 100
100 100 100 100 100 100 100 100 100 100
100 100 100 100 100 100 100 100 100 100
1 1 1
ans: 5443
*/