package _20230602;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj_13023_ABCDE{
	/*
	 * 인접 리스트 만들어서 재귀로 풀기
	 * 시작: 13:00
	 * 끝: 13:45
	 */
	public static int N,M; //사람수, 친구관계수
	public static List[] list;
	public static boolean[] v;
	public static int result;

	public static boolean dfs(int start, int depth){
		// System.out.println("start: "+start+", depth: "+depth);
		if(depth == 4){
			result = 1;
			return true;
		}
		int size = list[start].size();
		for(int i=0;i<size;i++){
			int next = (int)list[start].get(i);
			if(v[next]) continue;
			v[next] = true;
			if(dfs(next, depth+1)){
				return true;
			}
			v[next] = false;
		}
		return false;
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new List[N];
		for(int i=0;i<N;i++){
			list[i] = new ArrayList<Integer>();
		}

		for(int i=0;i<M;i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		result = 0;
		for(int i=0;i<N;i++){
			v = new boolean[N];
			v[i] = true;
			if(dfs(i, 0)){ //dfs 함수가 true면 5개 연속된 친구관계를 찾았다.
				break;
			}
		}
		// System.out.println(list[0].toString());
		System.out.println(result);
	}
}
//LinkedList로 만들었는데 시간초과
//ArrayList로 만드니까 시초 해결