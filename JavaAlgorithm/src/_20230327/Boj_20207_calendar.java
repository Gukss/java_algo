package _20230327;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_20207_calendar {
	public static int N; //일정 개수
	// public static int[][] input;
	public static int[] map;

	//idx 한 번 나올 때 마다 +=1 해주고
	//idx가 연속되면 그 구간에서 max 구한다.
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		// input = new int[N][2];
		map = new int[367];
		for(int i=0;i<N;i++){
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			for(int j=start;j<=end;j++){
				map[j] += 1;
			}
		}
		// System.out.println(Arrays.toString(map));
		int result = 0;
		int max = -1;
		int length = 0;
		for(int i=1;i<=366;i++){
			if(map[i] != 0){ //0이 아니면 max값 구하고, 길이 추가
				max = Math.max(max, map[i]);
				length += 1;
			}else{ //0이면 result에 더해주고 0으로 초기화
				result += max*length;
				max = 0;
				length = 0;
			}
		}
		System.out.println(result);
	}
}

/*
1 365
ans: 365
모두 연속인 경우 0인 경우 예외에 들어가지 않기때문에 틀렸다.
 */