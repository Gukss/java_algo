package _20240103;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Boj_1918_postOrder {
	public static Stack<Character> st;
	public static StringBuilder sb;
	public static boolean openFlag;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		st = new Stack<>();
		sb = new StringBuilder();

		// 1. 숫자는 그대로 출력한다.
		// 2. 만약 스택이 비어있다면 연산자를 그냥 스택에 넣는다.
		// 3. (스택의 top에 있는 연산자의 우선순위 < 현재 연산자의 우선순위) 이면 현재 연산자를 그냥 스택에 넣는다.
		// 4. (스택의 top에 있는 연산자의 우선순위 >= 현재 연산자의 우선순위) 이면 2번 혹은 3번 상황이 될 때까지 pop 하여 출력하고 연산자를 스택에 넣는다.
		// 5. 모든 수식을 다 사용했다면 스택이 빌 때까지 pop하여 출력한다.
		// 6. 우선순위는 (더하기=빼기) < (곱하기=나누기)이다.
		// 7. 여는 괄호는 스택에 그냥 추가한다.
		// 8. 여는 괄호 다음에 오는 연산자는 그냥 스택에 추가한다.
		// 9. 닫는 괄호는 여는 괄호가 나올 때까지 스택을 pop 하여 출력한다. 다 출력하고 난 뒤 괄호들은 버린다.

		for (int i=0;i<input.length();i++) {
			char cur = input.charAt(i);
			if ('*' == cur || '/' == cur || '+' == cur || '-' == cur){ //사칙연산이면 비교 후 스택에 넣는다.
				if (openFlag) { //여는 괄호 다음에 오는 연산자이면 스택에 넣는다.
					openFlag = false;
					st.add(cur);
					continue;
				}
				if (st.isEmpty()) { //스택이 비어있을 때
					st.add(cur);
				} else { //비어있지 않으면 비교해야한다.
					char prev = st.peek();
					if ('*' == cur || '/' == cur) { //현재가 우선순위가 높은 연산자일 때
						if ('*' == prev || '/' == prev) { //우선순위가 같으면
							int size = st.size();
							for (int j=0;j<size;j++) {
								if('+' == st.peek() || '-' == st.peek()){
									// st.add(cur);
									break;
								}
								char curpop = st.pop();
								sb.append(curpop);
							}
							st.add(cur);
						} else { //prev가 우선순위가 낮으면 => st에 넣는다.
							st.add(cur);
						}
					} else { //현재가 우선순위가 낮은 연산자일 때
						while(!st.isEmpty()) { //st가 빌때까지
							if ('(' == st.peek()) { //여는 괄호가 나오면 멈춘다.
								break;
							}
							sb.append(st.pop());
						}
						st.add(cur);
					}
				}
			} else if (')' == cur){ //닫는 괄호일 때
				int size = st.size();
				for (int j=0;j<size;j++) {
					char p = st.pop();
					if ('(' == p) { //여는 괄호가 나오면 pop을 멈춤 
						break;
					}
					sb.append(p);
				}
			} else if ('(' == cur) { //여는 괄호 일 때
				st.add(cur);
				openFlag = true;
			} else { //피연산자 일 때
				sb.append(cur);
			}
		}
		while(!st.isEmpty()){
			sb.append(st.pop());
		}
		System.out.println(sb);
	}
}

//2시간 반
//뭔가 예전에는 쉬웠던걸로 기억하는데, 조건도 인터넷에서 찾아서 하고, 너무 어렵게 느껴졌다.