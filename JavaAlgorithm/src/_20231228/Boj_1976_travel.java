package _20231228;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1976_travel {
	public static boolean[][] map;
	public static int N, M; //도시 수, 여행 계획 수
	public static int[] plan;
	public static boolean[] v;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//같은 도시 여러 번 방문하기 가능
		//N개의 도시가 있다. N<=200
		//여행계획에 속한 도시 수 M <= 1000
		//N개의 줄에 연결여부, 1은 양방향 연결, 0은 연결 안됐다.
		//제일 마지막에 여행 계획, 도시는 1부터 N까지 

		//bfs로 다음 도시를 찾는다. 방문 순서인 도시에 도착하고 나면 다시 v를 초기화하고, bfs를 돌려 다음 도시를 찾는다.
		//=>도시 개수만큼 반복
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());

		map = new boolean[N][N];
		
		for(int i=0;i<N;i++){
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++){
				if(1==Integer.parseInt(st.nextToken())){
					map[i][j] = true;
					map[j][i] = true;
				}
			}
		}

		plan = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<M;i++){
			plan[i] = Integer.parseInt(st.nextToken())-1;
		}

		if(depth(0)){
			System.out.println("YES");
		}else{
			System.out.println("NO");
		}
	}

	public static boolean depth(int n){
		if(n == M-1){
			return true;
		}
		for(int i=0;i<M;i++){
			if(map[plan[n]][i]){
				if(depth(n+1)){
					return true;
				}
			}
		}
		return false;
	}
}
