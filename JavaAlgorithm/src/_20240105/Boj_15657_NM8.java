package _20240105;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_15657_NM8 {
	public static int N,M;
	public static int[] map;
	public static int[] sel;
	public static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N];
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(map);
		// for (int i=0;i<N;i++) {
		// 	System.out.println(map[i]);
		// }
		sel = new int[M];
		// perm(0);
		comb(0,0);
		System.out.println(sb);
	}

	public static void comb(int idx, int start) {
		if (idx == sel.length) {
			for(int i=0;i<sel.length;i++) {
				sb.append(sel[i] + " ");
			}
			sb.append("\n");
			return;
		}
		for (int i=start;i<map.length;i++) {
			sel[idx] = map[i];
			comb(idx+1, i);
		}
	}

	public static void perm(int idx) {
		if(idx == sel.length) {
			for(int i=0;i<sel.length;i++) {
				sb.append(sel[i] + " ");
			}
			sb.append("\n");
			return;
		}
		for (int i=0;i<map.length;i++) {
			sel[idx] = map[i];
			perm(idx+1);
		}
	}
}
