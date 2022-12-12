package _20221205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Bj_1026_treasure {

	public static int[] A;
	public static Integer[] B;
	public static int N;
	//배열의 수를 한 개씩 곱한 수를 더해서 최솟값을 만드려면 
	//한 배열은 오름차순, 한 배열은 내림차순 정렬을 하면 된다.
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		A = new int[N];
		B = new Integer[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		//오름차순
		Arrays.sort(A);
		
		//내림차순
		Arrays.sort(B, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
			
		});
		int result = 0;
		for(int i=0;i<N;i++) {
			result += A[i]*B[i];
//			System.out.println(A[i] + " "+ B[i]);
		}
		System.out.println(result);
	}

}
//20분 => Comparator, 정렬하는것 다시 보기