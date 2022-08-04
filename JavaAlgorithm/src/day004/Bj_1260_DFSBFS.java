package day004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Bj_1260_DFSBFS {

	//
	static boolean[][] connected;
	static boolean[] visited;
	
	public static void dfs(int idx, int count, int limit) {
		if(count == limit || visited[idx]) return; //종료 조건, 출력한 정점의 개수가 limit와 같거나 방문한 정점일 때
		System.out.printf("%d ", idx); //idx 정점을 출력하고 방문표시 
		visited[idx] = true;
		for (int i = 0; i < limit+1; i++) { 
			if(connected[idx][i] == true) dfs(i, count+1, limit); //idx 정점과 연결돼있으면 dfs호출 
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); //정점의 개수
		int M = Integer.parseInt(st.nextToken()); //간선의 개수
		int V = Integer.parseInt(st.nextToken()); //시작 정점
		
		connected = new boolean[N+1][N+1]; //연결을 확인할 배열
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			connected[x][y] = true; //연결상태를 입력받아 양방향으로 배열에 저장
			connected[y][x] = true;
		}
		
		
		visited = new boolean[N+1]; //정점 방문 여부 저장 배열
		dfs(V, 0, N); //시작점, count, 종료조건 매개변수로 dfs 호출
		System.out.println("");
		
		
		LinkedList<Integer> qu = new LinkedList<>();
		visited = new boolean[N+1];
		
		qu.add(V); //큐에 시작점 add
		visited[V] = true; //시작점 visited 표시

		while(!qu.isEmpty()) {
			int idx = qu.poll(); //idx 꺼내서 출력
			System.out.printf("%d ", idx);
			for (int i = 0; i < N+1; i++) {
				if(connected[idx][i] && !visited[i]) { //idx정점과 연결돼 있는 노드 중 방문 안한것
					visited[i] = true; //방문 처리
					qu.add(i); //큐에 add
				}
			}
		}
//		while(count<=N) { //반복문으로 구현 도전했으나 실패
//			for (int i = 0; i < N+1; i++) {
//				if(connected[idx][i] && !visited[idx]) {
//					System.out.printf("%d ", idx);
//					visited[i] = true;
//					idx = i;
//					count+=1;
//					break;
//				}
//			}
//			
//		}
	}

}
