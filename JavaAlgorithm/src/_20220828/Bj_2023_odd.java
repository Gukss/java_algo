package _20220828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj_2023_odd {
	
	static int N;
	static StringBuilder sb;
	
	public static boolean prime(int num) {
		if(num==1 || num==0) {
			return false;
		}
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if(num % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static void check(int x, int count) {	
		if(count == N) {
			sb.append(x+"\n");
			return;
		}
		for (int i = 1; i < 10; i+=2) {
			if(prime(x*10 + i)) {		
				check(x*10 + i, count+1);
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		sb = new StringBuilder();
		
		for(int x: new int[] {2,3,5,7}) {
			check(x, 1);
		}
		
		System.out.println(sb);
	}

}
