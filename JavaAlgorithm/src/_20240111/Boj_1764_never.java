package _20240111;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Boj_1764_never {
	public static int N,M; //듣, 보
	public static HashSet<String> listen;
	public static HashSet<String> hear;
	public static LinkedList<String> list;
	public static void main(String[] args) throws Exception{
		//set을 2개 만들어서 넣고, 겹치는 이름 ArrsyList에 옮겨 담고 정렬

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		listen = new HashSet<>();
		hear = new HashSet<>();
		for(int i=0;i<N;i++){
			listen.add(br.readLine());
		}
		for(int i=0;i<M;i++){
			hear.add(br.readLine());
		}
		list = new LinkedList<>();
		for(String x: listen) {
			if(hear.contains(x)){
				list.add(x);
			}
		}
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		sb.append(list.size()+"\n");
		for(String x: list) {
			sb.append(x+"\n");
		}
		System.out.println(sb);
	}
}
