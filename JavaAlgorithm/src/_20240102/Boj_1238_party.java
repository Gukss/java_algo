package _20240102;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_1238_party {
	public static int N,M,X;
	public static int[][] map;
	public static boolean[][][] v;
	public static Queue<S> qu;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); //학생, 마을 숫자 [1,1000], 각 마을에 1명씩 살고 있다.
		M = Integer.parseInt(st.nextToken()); //단방향 도로 [1,10000]
		X = Integer.parseInt(st.nextToken()); //도착지점
		//왕복 가장 오래걸리는 시간 출력

		map = new int[N][N];
		v = new boolean[N][N][N];
		qu = new LinkedList<>();
		
		for(int i=0;i<M;i++){
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			map[s][e] = t;
		}

		//출발점, 누적 거리, 방문했던 마을(갈때 방문, 올때 방문 이렇게 2개 가지고 있어야한다.)
		//최단 시간이면 그냥 bfs에서 제일 먼저 도착하면 break하면 되는거 아닌가?
		//각 마을 별로 v가지고 있으면서 방문체크해주고,

		for(int i=0;i<N;i++){ //갈 때, X가 도착지점이다.
			for(int j=0;j<N;j++){ //처음 마을과 연결된 마을 찾기
				if(map[i][j] != 0){ //길이 연결되어 있으면
					qu.add(new S(i, j, map[i][j], false));
					v[i][i][j] = true;
				}
			}
			//갈 때 가장 가깝다고 누적이 가장 가까운게 아니다.
			while(!qu.isEmpty()){
				S cur = qu.poll();
				if(cur.end == X){
					cur.rtn = true;
				}
			}
		}
	}

	public static class S{
		int start, end, sum;
		boolean rtn;

		public S(int start, int end, int sum, boolean rtn){
			this.start = start;
			this.end = end;
			this.sum = sum;
			this.rtn = rtn;
		}
	}
}
