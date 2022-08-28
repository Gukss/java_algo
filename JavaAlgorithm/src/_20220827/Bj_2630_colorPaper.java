package _20220827;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_2630_colorPaper {

	static int[][] map;
	static int N;
	static int[] result;
	
	public static void divide(int r, int c, int len) {
		
		if(len==1) {
			result[map[r][c]] += 1;
			return;
		}
		int start = map[r][c];
		
		for (int i = r; i < r+len; i++) {
			for (int j = c; j < c+len; j++) {
				if(start != map[i][j]) {
					divide(r, c, len/2);
					divide(r+len/2, c, len/2);
					divide(r, c+len/2, len/2);
					divide(r+len/2, c+len/2, len/2);
					return;
				}
			}
		}
		result[start] += 1;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}			
		}
		result = new int[2];
		
		divide(0,0,N);
		
		System.out.println(result[0]);
		System.out.println(result[1]);
	}

}
