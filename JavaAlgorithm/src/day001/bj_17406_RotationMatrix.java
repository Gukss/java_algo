package day001;

import java.util.Scanner;

public class bj_17406_RotationMatrix {
	
	public static void permutation(int [] arr, int[] output, int[][] result, boolean[] visited, int depth, int r) {
		if(depth == r) {
			for(int i=0;i<r;i++) {
				result[][i] = output[i];
			}
			return;
		}
		for(int i=0;i<arr.length;i++) {
			if(!visited[i]) {
				visited[i] = true;
				output[depth] = arr[i];
				permutation(arr, output, result, visited, depth+1, r);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int K = sc.nextInt();
		int[][] map = new int[N][M];
		int[][] temp = new int[N][M]; //회전 결과 저장하는 배열
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		for (int i = 0; i < N; i++) { //map을 temp로 깊은 복사
			for (int j = 0; j < M; j++) {
				temp[i][j] = map[i][j];
			}
		}
		
		int[] input = new int[K];
		int c = 1;
		for (int i = 0; i < input.length; i++) {
			input[i] = i;
			c *= i;
		}
		int[] output = new int[K];
		boolean[] visited = new boolean[input.length];
		int[][] result_arr = new int[c][3];
		permutation(input, output, result_arr, visited, 0, K);
		
		
		int[][] rotate = new int[K][3];
		for (int i = 0; i < rotate.length; i++) {
			for (int j = 0; j < 3; j++) {
				rotate[i][j] = sc.nextInt();
			}
		}
		
		for (int i = 0; i < K; i++) { //회전 연산 개수만큼 반복
//			for (int j = rotate[i][0] - rotate[i][2]-1; j < rotate[i][0] + rotate[i][2]; j++) { //행
//				for (int k = rotate[i][1] - rotate[i][2]-1; k < rotate[i][1] + rotate[i][2]; k++) { //열
//					
//				}
//			}
//		for (int j = 0; j < 4; j++) {
			for (int k = 1; k <= (rotate[i][2]*2+1)/2; k++) {
				for (int j = 0; j < k*2; j++) {					
					temp[rotate[i][0]-1 - k + j+1][rotate[i][1]-1 + k] = map[rotate[i][0]-1 - k + j][rotate[i][1]-1 + k]; //아래
					temp[rotate[i][0]-1 + k][rotate[i][1]-1 + k - j - 1] = map[rotate[i][0]-1 + k][rotate[i][1]-1 + k - j]; //왼
					temp[rotate[i][0]-1 + k - j-1][rotate[i][1]-1 - k] = map[rotate[i][0] -1 + k - j][rotate[i][1]-1 - k]; //위
					temp[rotate[i][0]-1 - k][rotate[i][1]-1 - k + j + 1] = map[rotate[i][0]-1 - k][rotate[i][1]-1 - k + j]; //오른
				}
			}
			for (int x = 0; x < N; x++) { //temp를 map으로 깊은 복사
				for (int j = 0; j < M; j++) {
					map[x][j] = temp[x][j];
				}
			}
		//}
			
//			for (int x = 0; x < N; x++) { //출력
//				for (int j = 0; j < M; j++) {
//					System.out.printf("%d ",temp[x][j]);
//				}
//				System.out.println("");
//			}
//			System.out.println("");
		}
		int result = 9999;
		for (int i = 0; i < temp.length; i++) {
			int flag = 0;
			for (int j = 0; j < temp[0].length; j++) {
				flag += temp[i][j];
			}
			result = Math.min(result, flag);
		}
		System.out.println(result);
		
	}
	
}
