package _20230325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_2661_perm {
	public static int N; //입력값 1~80
	public static boolean[] v;

	public static void perm(int idx){
		if(idx == N){ //종료조건

		}
		for(int i=0;i<N;i++){
			if(v[i]) continue;
			v[i] = true;
			perm(idx+1);
			v[i] = false;
			perm(idx+1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		v = new boolean[N];

		perm(0);

	}
}
