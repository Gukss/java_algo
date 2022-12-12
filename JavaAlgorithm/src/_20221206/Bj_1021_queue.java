package _20221206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Bj_1021_queue {
	
	public static int N,M;
	public static Deque<Integer> dq;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dq = new ArrayDeque<>(); //dq에 N만큼 숫자 집어넣기;1부터 시작
		for(int i=1;i<=N;i++) {
			dq.add(i);
		}
		st = new StringTokenizer(br.readLine());
		
		int result = 0;
		for(int i=0;i<M;i++) { //M만큼 반복 => 한 개씩 가까운 곳 찾아서 뽑는다.
			int targetNum = Integer.parseInt(st.nextToken());
			int size = dq.size();
			int idx = 0;
			for(int x: dq) { //반복하면서 숫자를 찾는다.
				if(targetNum==x) { //찾으려는 숫자와 dq의 숫자가 같으면 정지한다.
					break;
				}
				idx += 1; //못 찾으면 idx증가시킨다.
			}
			boolean firstPoll = false; //첫 부분에서 poll하는지 마는지 확인하는 변수.
			if(idx > size/2) { //idx가 dq크기의 반 보다 크면 뒤에서 뺀다.
				firstPoll = false;
			}else { //앞에서 뺀다.
				firstPoll = true;
			}
			
			//반복문 나오면 idx가 해당 숫자를 가리킨다.
			int pullNum = -1;
			while(true) {		
//				System.out.print(result+": ");
//				for(int x:dq) {
//					System.out.print(x+ " ");
//				}
//				System.out.println();
				if(firstPoll) { //일단 poll하고,
					pullNum = dq.pollFirst();
				}else {
					pullNum = dq.pollLast();
				}
				if(pullNum == targetNum) { //목표 숫자를 찾았으면 종료 => 그냥 종료하면 안된다.
					//앞에서 poll하면 그냥 종료
					if(!firstPoll) { 
						//결과를 count하는 조건을 생각해야한다.
						//round할 때만 count하고, 앞에서 poll이 아닐때는 result+=1해서
						//라운드해서 앞에서 poll하는 것 처럼 계산해야한다.
						result += 1;
					}
					break;
				}else {
					//라운드 하면 2,3 연산을 했다. => result += 1해준다.
					if(firstPoll) {
						dq.addLast(pullNum);
					}else {
						dq.addFirst(pullNum);
					}
					result += 1;
				}
			}
		}
		System.out.println(result);
	}
}
//1시간15분