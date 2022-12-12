package _20221207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Bj_1874_stack {
	
	public static Stack<Integer> stack;
	public static int n;
	public static Deque<Integer> dq;
	public static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		stack = new Stack<>();
		dq = new ArrayDeque<>();
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n]; //목표 수열을 저장하는 배열
		int idx = 0; //배열의 idx
		
		for(int i=1;i<=n;i++) { //1~n까지 수열 초기화
			dq.add(i);
			arr[i-1] = Integer.parseInt(br.readLine());
		}
//		System.out.println(Arrays.toString(arr));
		StringBuilder sb = new StringBuilder();
		int count = 0;//pop해야하는 숫자 저장한다.
		
		int pollNum = dq.pollFirst(); //하나 뽑는다.
		stack.add(pollNum); //시작하기 전에 하나 넣어놔야 한다.
		sb.append("+\n");
		while(true) {
			if(stack.peek() == arr[idx]) { //stack의 제일위의 값과 목표하는 순열의 값이 같으면 => pop하고, idx+=1
				stack.pop();
				idx += 1;
				sb.append("-\n");
			}else { //다르면 dq에서 하나 뽑아서 stack에 넣기
				if(dq.isEmpty()) { //stack의 제일 위가 목표하는 갑과 다르고, dq가 비었으면 순열을 만들지 못한다.=>종료
					sb = new StringBuilder();
					sb.append("NO");
					break;
				}
				pollNum = dq.pollFirst();
				stack.add(pollNum);
				sb.append("+\n");
			}
			if(idx >= n) { //순열을 모두 만들고, 종료
				break;
			}
			if(stack.isEmpty()) {
				stack.push(dq.pollFirst());
				sb.append("+\n");
			}
		}
		System.out.println(sb);
	}
}
/*
4
2
3
1
4
ans:
+
+
-
+
-
-
+
-
 
*/