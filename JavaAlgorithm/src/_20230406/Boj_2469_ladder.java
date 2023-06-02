package _20230406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_2469_ladder {
	public static int k,n;
	public static String inputAnswer;
	public static char[][] map;
	public static char[] inputArr;
	public static char[] initArr;
	public static int qNum;
	public static StringBuilder sb;

	public static void swap(char[] arr, int i, int j){
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void start(){
		for(int i=0;i<qNum;i++){
			for(int j=0;j<k-1;j++){
				if(map[i][j] == '-'){
					// System.out.println("i: "+i +"j: "+j);
					swap(initArr, j, j+1);
					// System.out.println("initArr: "+Arrays.toString(initArr));
				}
			}
		}
		for(int i=n-1;i>qNum;i--){
			for(int j=0;j<k-1;j++){
				if(map[i][j] == '-'){
					swap(inputArr, j, j+1);
					// System.out.println("inputArr: "+Arrays.toString(inputArr));
				}
			}
		}

		for(int i=0;i<k-1;i++){
			if(initArr[i] == inputArr[i+1] && initArr[i+1] == inputArr[i]){
				sb.append("-");
				i++;
				if(i>=k-1) return;
				sb.append("*");
			}else if(initArr[i] == inputArr[i]){ //두 개 같을 때 처리를 안해줬다.
				sb.append("*");
			}else{
				sb = new StringBuilder();
				for(int j=0;j<k-1;j++){
					sb.append("x");
				}
				return;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		//위에서 내려가면서 3번째 전까지 swap한다.
		//아래에서 올라가면서 3번째 전까지 swap 한다.
		//2번째, 4번째를 비교하면서 swap해서 만들 수 있는 경우면 -, swap 없어도 되면 *, 불가능하면 x하면된다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		n = Integer.parseInt(br.readLine());
		inputAnswer = br.readLine();
		inputArr = inputAnswer.toCharArray();
		sb = new StringBuilder();
		initArr = new char[k];
		for(int i=0;i<k;i++){
			initArr[i] = (char) (65 + i);
		}
		map = new char[n][k-1];
		for(int i=0;i<n;i++){
			String temp = br.readLine();
			for(int j=0;j<k-1;j++){
				map[i][j] = temp.charAt(j);
				if(map[i][j] == '?') qNum = i;
			}
		}
		// for(int i=0;i<n;i++){
		// 	System.out.println(Arrays.toString(map[i]));
		// }
		// 	System.out.println("initArr: "+Arrays.toString(initArr));
		// 	System.out.println("inputArr: "+Arrays.toString(inputArr));

		start();
		String answer = sb.toString();
		// String target = "";
		// String replaceAns = "";
		// for(int i=0;i<k-1;i++){
		// 	target += "*";
		// 	replaceAns += "x";
		// }
		// if(answer.equals(target)){
		// 	answer = replaceAns;
		// }
		System.out.println(answer);
	}
}
//2시간 반,,?