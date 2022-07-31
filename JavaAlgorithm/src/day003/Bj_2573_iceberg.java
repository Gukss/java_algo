package day003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_2573_iceberg {
	static int N, M;
	static int[][] ice;
	static int[][] melt;
	static final int[] dr = {-1,0,1,0};
	static final int[] dc = {0,1,0,-1};
	static int visited[][];
	
	public static void check(int i, int j) {
		visited[i][j] = 1; //현재 좌표를 방문했다고 표시한다.
		for (int k = 0; k < 4; k++) { //사방 탐색
			int nr=i+dr[k];
			int nc = j+dc[k];
			if(nr < 0 || nr >= N || nc <0 || nc >= M) {
				continue;
			}
			if(ice[nr][nc] != 0 && visited[nr][nc] == 0) { //빙산이고 방문하지 않았으면 check를 호출한다. 
				check(nr, nc); //
			}
		}
	}
	
	public static void time() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) { //배열 순회하면서
				if(ice[i][j] == 0) { //바다면
					for (int k = 0; k < 4; k++) { //사방 탐색 
						int nr=i+dr[k];
						int nc = j+dc[k];
						if(nr < 0 || nr >= N || nc <0 || nc >= M) {
							continue;
						}
						melt[nr][nc] += 1; //바다랑 접해 있으면 +=1
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ice = new int[N][M];
		melt = new int[N][M];
		visited = new int[N][M];
		
		for (int i = 0; i < N; i++) { //빙산 입력
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				ice[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int year = 0; //시간 저장
		while(true) {
			year += 1;
			int result = 0;
			time(); //1년이 지남
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) { //시간이 지나서 melt에 녹은 양 저장돼있다.
					if(ice[i][j] != 0) { //빙산이면
						ice[i][j] -= melt[i][j]; //녹은 양 만큼 빼준다.
						visited[i][j] = 0; //visited, melt 초기화
						melt[i][j] = 0;
					}
					if(ice[i][j] < 0) { //음수가 있으면 0으로 바꿔준다.
						ice[i][j] = 0;
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) { //반복문 돌면서
					if(ice[i][j] != 0 && visited[i][j] == 0) { //빙산이고 방문하지 않았으면 
						result += 1; //result 1증가하고
						check(i,j); //check함수를 실행해서 빙산의 개수를 센다
					} //check를 한 번 실행완료하면 한 덩이의 빙산을 모두 방문한다.
				}
			}
			if(result>=2) { //빙산이 2개 이상이면 종료하고 시간이 얼마나 걸린지 출력한다.
				System.out.println(year);
				break;
			} else if(result == 0) { //빙산이 모두 녹았으면 방문할 빙산이 없기 때문에 result가 0이다.
				System.out.println(result); //0을 출력한다.
				break;
			}
			
		}

	}
}
