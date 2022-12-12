package _20221204;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_1010_bridge {

	public static int T,N,M; //테스트케이스, 서쪽, 동쪽
	public static int[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int test_case=1;test_case<=T;test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			if(M>N) { //nCm의 m이 크면 n,m을 바꿔준다.				
				//30C15는 너무 크기때문에 재귀로 못짠다. => 파스칼의 삼각형 필요하다.
				int temp = M;
				M = N;
				N = temp;
			}
			
			map = new int[N+1][N+1];
			
			for(int i=0;i<=N;i++) {
				for(int j=0;j<=N;j++) {
					if(i==j || j==0) { //i와 j가 같거나, 행의 시작이면 1로 초기화
						map[i][j] = 1;
					}else if(i!=0) {
						map[i][j] = map[i-1][j] + map[i-1][j-1];
					}
				}
			}
			
			sb.append(map[N][M]+"\n");
		}
		System.out.println(sb);
	}

}
