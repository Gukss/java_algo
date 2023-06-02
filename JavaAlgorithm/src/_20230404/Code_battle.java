package _20230404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Code_battle {
	public static int[][] map;
	public static int[][] playerMap;
	public static int[] dr = {-1, 0, 1, 0}; //위오밑왼
	public static int[] dc = {0, 1, 0, -1}; //위오밑왼
	public static List<Player> list;
	public static int n,m,k;

	public static void start(){
		for(int i=0;i<m;i++){ //플레이어 수 만큼 반복 => 이동
			Player cur = list.get(i); //i번째 플레이어를 움직일 차례
			int r = cur.r;
			int c = cur.c;
			int d = cur.d;
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(!(nr>=0&&nc>=0&&nr<n&&nc<n)){ //경계 밖이면
				nr = r + dr[(d+2)%4];
				nc = c + dc[(d+2)%4]; //반대 방향으로 돌리기
			}
			if(playerMap[nr][nc] != 0){ //playerMap이 0이 아니면 누가 들어있다는 이야기다.
				
			}else{ //누가 없을 때
				//놓여있는 총과 이미 가지고 있는 총을 확인
				int curGun = map[nr][nc];
				int havingGun = cur.gun;
				if(curGun > havingGun){
					int temp = cur.gun;
					cur.gun = curGun; //총 주워서 현재 총으로 변경
					map[nr][nc] = temp;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); //격자의 크기
		m = Integer.parseInt(st.nextToken()); //플레이어의 수
		k = Integer.parseInt(st.nextToken()); //라운드 수

		map = new int[n][n];
		playerMap = new int[n][n];

		for(int i=0;i<n;i++){
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//플레이어 정보 x,y,d,s => (x,y)위치, d는 방향, s는 초기 능력치
		//d는 0~3, 위오밑왼
		//플레이어의 순서는 바뀌지 않는다. => arrayList로 구현
		list = new ArrayList<>();
		for(int i=0;i<m;i++){
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1; //좌표가 1부터 시작한다. => 보정
			int c = Integer.parseInt(st.nextToken())-1; //좌표가 1부터 시작한다. => 보정
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			list.add(new Player(r,c,d,s,0,0));
			map[r][c] = i+1; //0으로 초기화돼있기 때문에 1부터 넣는다.
		}
		
		for(int i=0;i<k;i++){
			start();
		}
	}

	public static class Player{
		int r, c, d, s, gun, point;
		public Player(int r, int c, int d, int s, int gun, int point){
			this.r = r;
			this.c = c;
			this.d = d;
			this.s = s;
			this.gun = gun;
			this.point = point;
		}
	}
}
