package _20230301;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Boj_21315_card {
	public static int N;
	public static int[] arr;
	public static List<Integer> result;

	public static boolean check(int idx){
		if(idx==0 && arr[0] != arr[1]){
			return false;
		}
		for(int i=1;i<idx;i++){
			if(arr[i] != arr[i-1]+1){
				return false;
			}
		}
		// System.out.println(idx);
		// System.exit(0);
		return true;
	}

	public static void swap(int n, int m){
		int temp = arr[n];
		arr[n] = arr[m];
		arr[m] = temp;
	}


	public static void rollback(int idx, int depth, int before){
		if(depth == 2){
			result.add(before);
			result.add(idx);
			return;
		}
		if(check(idx)){
			rollback(0, depth+1, idx);
			return;
		}
		for(int i=0;i<Math.pow(2,idx);i++){
			if(idx+i>=N){
				break;
			}
			swap(i, idx+i);
			rollback((int)Math.pow(2,idx+1), 0, -1);
		}
	}

	// public static int rollback(int idx, int depth){
	// 	System.out.println(Arrays.toString(arr));
	// 	if(depth == 2) return -1;
	// 	if(check(idx)){
	// 		result.add(idx);
	// 		rollback(0, depth+1);
	// 		return idx;
	// 	}
	// 	for(int i=0;i<Math.pow(2,idx);i++){
	// 		if(idx+i>=N){
	// 			break;
	// 		}
	// 		// System.out.println(i+": "+(idx+i));
	// 		swap(i, idx+i);
	// 	}
	// 	int returnValue = rollback((int)Math.pow(2,idx+1), 0);
	// 	if(returnValue != -1){
	// 		return returnValue;
	// 	}
	// 	return -1;
	// }

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		result = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}
		rollback(0, 0, -1);
		System.out.println(result.toString());
	}
}
