package day003;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Bj_1697_hide {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //현재 위치
		int K = Integer.parseInt(st.nextToken()); //목표 위치
		
		LinkedList<int[]> qu = new LinkedList<>(); //큐
		boolean[] visited = new boolean[100001]; //방문 확인용 배열
		
		int s = 0; //시작 시간
		visited[N] = true; //시작 위치 방문했다고 표시
		qu.add(new int[] {N, s}); //큐에 배열 삽입
		
		while(!qu.isEmpty()) {
			int[] cur = qu.poll(); //큐에서 1개 꺼낸다.
			if(cur[0] == K) { //목표하는 위치에 도착하면 
				System.out.println(cur[1]); //걸린 시간을 출력하고
				break; //종료한다.
			}
			if(cur[0] + 1>=0 && cur[0] + 1< visited.length && !visited[cur[0] + 1]) { //배열을 넘어가지 않고 방문하지 않은 위치일 때 실행한다.
				visited[cur[0] + 1] = true; //새로운 위치 방문했다고 표시
				qu.add(new int[] {cur[0] + 1, cur[1] + 1}); //이동한 위치 큐에 삽입
			}
			if(cur[0] - 1>=0 && cur[0] - 1< visited.length && !visited[cur[0] - 1]) {
				visited[cur[0] - 1] = true;
				qu.add(new int[] {cur[0] - 1, cur[1] + 1});
			}
			if(cur[0] * 2>=0 && cur[0] * 2< visited.length && !visited[cur[0] * 2]) {
				visited[cur[0] * 2] = true;
				qu.add(new int[] {cur[0] * 2, cur[1] + 1});
			}
		}
	}

}
