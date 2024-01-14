package _20240104;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//2차원 배열로 풀이 => 메모리 초과

public class Boj_11725_parentTree {
	public static int N;
	public static LinkedList[] map;
	public static boolean[] v;
	public static int[] result;
	public static Queue<Integer> qu;
	public static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new LinkedList[N+1];
		result = new int[N+1];
		qu = new LinkedList<>();
		sb = new StringBuilder();
		for (int i=0;i<N+1;i++) {
			map[i] = new LinkedList<Integer>();
		}
		for (int i=0;i<N-1;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r].add(c);
			map[c].add(r);
		}

		v = new boolean[N+1];
		//1부터 dfs?bfs 하면서 result 배열에 result[밑] = 본인 이렇게 저장한다.
		//dfs, bfs 둘 중에 뭐든 상관없다.
		
		qu.add(1);
		v[1] = true;

		while (!qu.isEmpty()) {
			int cur = qu.poll();
			int size = map[cur].size();
			for (int i=0;i<size;i++) {
				int next = (int) map[cur].get(i);
				if (map[cur].contains(next) && !v[next]) { //연결되어있으면
					qu.add(next);
					v[next] = true;
					result[next] = cur;
				}
			}
		}

		for (int i=2;i<=N;i++) {
			sb.append(result[i] + "\n");
		}

		System.out.println(sb);
	}
}

//처음 2차원 배열로 풀었다. => 메모리 초과
//연결리스트로 풀었는데, 마지막 연결 여부 확인할 때 [1,N] 모두 for문으로 확인하니까 => 시간 초과
//연결리스트의 개수만큼 반복문을 반복하게 해서 해결했다.