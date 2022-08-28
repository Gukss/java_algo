package _20220828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Bj_2606_virus {
	
	static ArrayList<Integer>[] list;
	static boolean[] v;
	static int result;
	
	public static void dfs(int idx) {
		for(int next: list[idx]) {			
			if(!v[next]) { //방문 안했으면 방문 처리
				v[next] = true;
				result += 1;
				dfs(next);
			}
		}
		return;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int edge_num = Integer.parseInt(br.readLine());
		v = new boolean[n+1]; //컴퓨터 방문여부
		
		list = new ArrayList[n+1]; //컴퓨터 번호는 1부터 -> 0번 사용안한다.
		for (int i = 0; i < n+1; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < edge_num; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list[from].add(to);
			list[to].add(from);
		}
		result = 0;
		v[1] = true;//시작점 방문 처리
		dfs(1);
		System.out.println(result);
	}
}
//시작1613~1645
//30분