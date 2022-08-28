package _20220827;

import java.util.Arrays;
import java.util.Scanner;

public class SW_ex1_fishing {

	
	static int[] sel;
	static boolean[] v;
	static int[] map;
	static int[][] line;
	static int count;
	static int N;
	static int result;
	
	public static int right(int i, int dist) {
		int right = line[i][1] + dist;
		if(right <= N && map[right] == 0) {
			map[right] = i; //i로 초기화해 준다.
			count += 1;
			return dist + 1;
		}
		return 0;
	}
	
	public static int left(int i, int dist) {
		int left = line[i][1] - dist;
		if(left > 0 && map[left] == 0) {
			map[left] = i;
			count += 1;
			return dist + 1;
		}
		return 0;
	}
	
	public static int move(int i, int num, boolean l) {
		int dist = 0;
		int sum = 0;
		
		while(count < num) {
			sum += l ? left(i, dist) : right(i, dist);
			
			if(count == num) break;
			
			sum += l ? right(i, dist) : left(i, dist);
			dist += 1;
		}
		
		return sum;
	}
	
	public static void redo(int idx) {
		int count = 0;
		for (int i = 1; i <= N; i++) {
			if(map[i] == idx) { //move() 실행되고 나면 해당 i로 
				map[i] = 0;
				count += 1;
			}
			if(line[idx][1] == count) return;
		}
	}
	
	public static void perm(int idx, int sum) {
		if(idx == 3) {
			result = Math.min(sum, result);
			return;
		}
		for (int i = 1; i <= 3; i++) {
			if(v[i]) continue;
			v[i] = true;
			
			perm(idx+1, sum + move(i, line[i][1], true)); //true면 왼->오
			redo(i); //돌려놓기
			
			if(line[i][1] % 2 == 0) {
				perm(idx+1, sum + move(i, line[i][1], false)); //false면 오->왼
				redo(i); //돌려놓기
			}
			v[i] = false;
		}
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			/////////////////////////////////////////////////////////////////////////////////////////////

			N = sc.nextInt();
			map = new int[N+1]; //낚시터 자리 번호를 인덱스로 사용-0없다.
			
			line = new int[4][2]; //
			
			for (int i = 1; i <= 3; i++) {
				line[i][0] = sc.nextInt(); //출입구 위치
				line[i][1] = sc.nextInt(); //인원수
			}
			
			//3개 순열 필요 3!
			sel = new int[3];
			v = new boolean[4];
			
			perm(1, 0);
			
			/////////////////////////////////////////////////////////////////////////////////////////////

		}
		
	}
	
	

}

//시작1904