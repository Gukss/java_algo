package _20230403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_16719_ZOAC {
	//map으로 문자열에서 인덱스랑 문자 저장하고
	//pq로 문자열을 기준으로 오름차순으로 정렬한다.
	//StringBuilder에 한 개씩 붙이면서 출력해준다.? => String에 붙이면서 StringBuilder로 출력할꺼 누적한다.
	//=>X

	//for문 돌면서 제일 작은 글자 찾는다. => idx로 들어가서 idx부터 해당함수 반복, => 들어갈 때 방문처리해준다.
	//그 밑에 재귀는 처음부터 도는 함수 적어준다.

	public static String input;
	public static int len;
	public static boolean[] v;
	public static StringBuilder sb1;
	public static void recr(int start, int end){
		if(start > end) return; //종료조건, 이분탐색의 종료조건이다.
		StringBuilder sb2 = new StringBuilder(); //함수 하나 안에서 문자열 저장하는 StringBuilder
	
		int idx = start;
		for(int i=start;i<=end;i++){ //해당 구간에서 가장 작은 문자 찾는다.
			if(input.charAt(i) < input.charAt(idx) && !v[i]){ //"<=" 조건으로 해서 틀렸다.
				idx = i;
			}
		}
		if(idx != -1 && !v[idx]){ //혹시나 방문한 문자거나, 위 for문에서 안걸렸을 때 탈출할 수 있도록 조건 => 없어도 된다. 
			//-> 무조건 걸린다.
			v[idx] = true; //방문처리
			for(int i=0;i<len;i++){ //처음부터 돌면서 방문한 문자를 append해준다.
				// 처음에는 재귀에서 왼쪽, 오른쪽에 문자를 concat해주는 걸로 작성했다 => 잘못된 접근
				if(v[i]){
					sb2.append(input.charAt(i));
				}
			}
			sb2.append("\n");
			sb1.append(sb2.toString());
			recr(idx+1, end); //이분 탐색 => idx는 빼고 양옆으로 탐색한다. => 오른쪽으로 먼저 가야 사전순 오름차순 된다.
			recr(start, idx-1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine();
		len = input.length();
		v = new boolean[len];
		sb1 = new StringBuilder(); //전체 문자열(정답 문자열)저장하는 StringBuilder
		recr(0, len-1);
		System.out.println(sb1);
	}
}
// 1시간 반
