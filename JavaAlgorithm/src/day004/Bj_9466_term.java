package day004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_9466_term {

	static int[] s_num;
	static boolean[] visited;
	static boolean[] finished;
	static int count;
	
	public static void dfs(int i) {
		if(visited[i]) return; //방문했던 노드면 return, 루프를 한 번 돌았다면 여기서 걸린다.
		visited[i] = true; //방문 처리
		int next = s_num[i]; //다음 노드 저장
		if(!visited[next]) dfs(next); //다음 노드를 방문하지 않았으면 dfs 
		else { //다음 노드가 방문한 노드면(루프를 이룬다.)
			if(!finished[next]) { //다음 노드가 루프가 되는지 체크한 노드가 아니면
				count--; //현재 노드 i도 개수 세기, 자기자신을 가리키고 있으면 while 안에 안들어간다, 
				while(i != next) { //루프를 구성하고 있는 수 만큼 반복, 제일 마지막에 루프가 확인되면 탈출
					count--;
					next = s_num[next];
				}
			}
		}
		finished[i] = true; //루프가 되는지 확인한 노드 끝났다고 표시
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int n = Integer.parseInt(br.readLine());
			s_num = new int[n+1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < n+1; i++) {
				s_num[i] = Integer.parseInt(st.nextToken()); //원하는 팀원 배열 입력 받기
			}
			visited = new boolean[n+1];
			finished = new boolean[n+1];
			
//			int[][] check = new int[n+1][n+1];
			count = n;
			
			for (int i = 1; i < n+1; i++) {
//				check[i][0] = s_num[i];
//				int idx = 1;
//				while(idx < check[i].length) {
//					if(i == check[i][idx-1]) {
//						count-=1;
//						break;
//					}
//					check[i][idx] = s_num[check[i][idx-1]];
//					idx+=1;
//				}
				dfs(i);
				
			}
			System.out.println(count);
		}
	}

}
