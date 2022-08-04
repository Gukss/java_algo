package day004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Bj_17471_gerrymandering {

	static int N;
	static int[] arr;
	static int[][] connected;
	static boolean[] sel;
	static boolean[] visited;
	static LinkedList<Integer> qu;
	static int result = Integer.MAX_VALUE;
	
	public static boolean checkConnected(boolean val) {
		visited = new boolean[N+1];
		qu = new LinkedList<>();
		
		for (int i = 1; i < sel.length; i++) {
			if(sel[i] == val) {
				qu.add(i);
				visited[i] = true;
				break; //val 인 시작점 하나 찾아서 큐에 넣기
			}
		}
		
		while(!qu.isEmpty()) { //bfs로 연결된 노드 모두 방문하기, val에 속한 구역이 모두 연결돼있다면 완료시 모두 방문완료여야 한다. 
			int node = qu.poll();
			for (int i = 1; i < sel.length; i++) {
				if(!visited[i] && sel[i] == val && connected[node][i] == 1) {
					visited[i] = true;
					qu.add(i);
				}
			}
		}
		
		for (int i = 1; i < sel.length; i++) { //val에 속한 구역이 모두 방문완료 상태인지 확인한다.
			if(sel[i] == val && !visited[i]) { //방문완료가 아니면
				return false;
			}
		}
		return true;
	}
	
	public static int sub() {
		int T=0;
		int F=0;
		for (int i = 1; i < arr.length; i++) {
			if(sel[i]) T += arr[i];
			else F += arr[i];
		}
		return Math.abs(T-F);
	}
	
	public static void divided(int idx) {
		if(idx == arr.length) {
			if(checkConnected(true) && checkConnected(false)) {
				result = Math.min(result, sub());
			}
			
			return;
		}
		sel[idx] = true;
		divided(idx+1);
		
		sel[idx] = false;
		divided(idx+1);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		connected = new int [N+1][N+1];
		arr = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N+1; i++) {
			arr[i] = Integer.parseInt(st.nextToken()); //구역 인구수 입력
		}
		
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			int temp = Integer.parseInt(st.nextToken());
			for (int j = 1; j <= temp; j++) {
				connected[i][Integer.parseInt(st.nextToken())] = 1; //인접행렬 만든다.
			}
		}
		sel = new boolean[N+1];
		divided(1);
		
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}

}
