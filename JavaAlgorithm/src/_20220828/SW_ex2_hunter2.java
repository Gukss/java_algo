package _20220828;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SW_ex2_hunter2 {

	static int[][] map;
	static int N;
	static ArrayList<Node> monsters;
	static ArrayList<Node> customers;
	static int min;
	static boolean[] monsterV;
	static boolean[] customerV;
	
	public static int getDistance(int sr, int sc, int er, int ec) {
		return Math.abs(sr-er)+Math.abs(sc-ec);
	}
	
	public static void dfs(int count, int dist, int r, int c) {
		if(dist >= min) { //최소값 보다 크면 더 이상 가볼 필요 없다.
			return;
		}
		if(count == monsters.size()*2) {
			min = Math.min(min, dist);
		}
		
		for(Node next: monsters) {
			if(monsterV[next.n]) continue;
			monsterV[next.n] = true;
			dfs(count+1, dist + getDistance(r, c, next.r, next.c), next.r, next.c);
			monsterV[next.n] = false;
		}
		
		for(Node next: customers) {
			if(customerV[next.n] || !monsterV[next.n]) continue;
			customerV[next.n] = true;
			dfs(count+1, dist + getDistance(r, c, next.r, next.c), next.r, next.c);
			customerV[next.n] = false;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			map = new int[N][N];
			monsters = new ArrayList<>();
			customers = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
					if(map[i][j] < 0) { //고객일 때
						customers.add(new Node(i, j, Math.abs(map[i][j]))); //음수 처리
					}else if(map[i][j] > 0) { //몬스터일 때
						monsters.add(new Node(i, j, map[i][j]));
					}
				}
			}
			min = Integer.MAX_VALUE;
			monsterV = new boolean[monsters.size()+1];
			customerV = new boolean[customers.size()+1];
			
			dfs(0, 0, 0, 0);
			System.out.println(min);
			
		}
	}

	public static class Node{
		int r, c, n;

		public Node(int r, int c, int n) {
			super();
			this.r = r;
			this.c = c;
			this.n = n;
		}
	}
	
}

//시작1243