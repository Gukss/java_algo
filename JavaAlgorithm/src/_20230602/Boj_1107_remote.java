package _20230602;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1107_remote {
	/*
	 * 중복순열로 만들었다가 예외에 걸려서 for문으로 완전탐색
	 */
	public static int N,M;
	public static boolean[] b;
	public static String Nstr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Nstr = br.readLine();
		N = Integer.parseInt(Nstr);
		M = Integer.parseInt(br.readLine());
		b = new boolean[10];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<M;i++){
			int x = Integer.parseInt(st.nextToken());
			b[x] = true;
		}
		
		int result = Math.abs(N-100);
		for(int i=0;i<999999;i++){
			String str = Integer.toString(i);
			int len = str.length();
			boolean stop = false;
			for(int j=0;j<len;j++){
				if(b[str.charAt(j)-'0']){ //고장나있으면
					stop = true;
					break;
				}
			}
			if(!stop){ //고장안난 숫자면 최소값 구하기
				result = Math.min(Math.abs(i-N)+len, result);
				// System.out.println(i);
			}
		}
		System.out.println(result);
	}
}
/*
 * input
 * 0
 * 10
 * 0 1 2 3 4 5 6 7 8 9
 * ans: 100
 * 
 * 1555
 * 3
 * 0 1 9
 * ans: 670
 * 
 * 99999
 * 9
 * 0 2 3 4 5 6 7 8 9
 * ans: 11118
 * 
 * 88
 * 2
 * 8 9
 * ans: 12
 */
