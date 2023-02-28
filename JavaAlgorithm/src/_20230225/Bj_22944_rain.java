package _20230225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_22944_rain {
	public static int N,H,D; //정사각형 크기, 체력, 우산 내구도
	public static char[][] map;
	public static Queue<Pos> qu;
	public static int[][] v;
	public static int[] dr = {-1, 0, 1, 0}; //위오밑왼
	public static int[] dc = {0, 1, 0, -1}; //위오밑왼
	public static int result;

	public static void bfs(){
		while(!qu.isEmpty()){
			Pos cur = qu.poll();
			int r = cur.r;
			int c = cur.c;
			int health = cur.health;
			int len = cur.len;
			int d = cur.d;
			// System.out.printf("%d %d: %d %d: %d\n", r,c,health,d,len);
			
			// for(int i=0;i<N;i++){
			// 	System.out.println(Arrays.toString(v[i]));
			// }
			// System.out.println("=========");
			if(map[r][c] == 'E'){ //안전지대에 도착하면 결과 반환
				result = len;
				break;
			}
			for(int i=0;i<4;i++){
				int nr = r + dr[i];
				int nc = c + dc[i];
				//방문 처리를 숫자로 해야한다. -> 우산을 들고 다시 최단경로를 찾아야한다.
				if(nr>=0&&nc>=0&&nr<N&&nc<N && v[nr][nc] < health+d && health > 0){ //체력이 0보다 커야 이동 가능
					v[nr][nc] = health+d;
					if(map[nr][nc] == 'U'){ //우산에 도달하면 내구도를 갱신(우산내구도로 바꾼다.)
						qu.add(new Pos(nr, nc, health, len+1, D-1));
					}
					if(map[nr][nc] == 'S'){ //시작위치는 비가 안오니까 체력 그대로
						qu.add(new Pos(nr, nc, health, len+1, d));
					}
					if(d <= 0){ //내구도가 0이면 체력이 감소한다.
						qu.add(new Pos(nr, nc, health-1, len+1, d));
					}else{ //내구도가 있으면 내구도가 감소한다.
						qu.add(new Pos(nr, nc, health, len+1, d-1));
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new char[N][N];
		qu = new LinkedList<>();
		v = new int[N][N];
		result = -1;

		for(int i=0;i<N;i++){
			st = new StringTokenizer(br.readLine());
			String row = st.nextToken();
			for(int j=0;j<N;j++){
				map[i][j] = row.charAt(j);
				if(map[i][j] == 'U'){ //우산

				}else if(map[i][j] == 'S'){ //현재위치
					qu.add(new Pos(i,j,H,0,0));
					v[i][j] = H+0; //체력+내구도로 방문 처리
				}else if(map[i][j] == 'E'){ //안전지대

				}
			}
		}

		bfs();
		
		System.out.println(result);
		
	}

	public static class Pos{
		int r,c,health,len,d;
		public Pos(int r, int c, int health, int len, int d){
			this.r = r;
			this.c = c;
			this.health = health;
			this.len = len;
			this.d = d;
		}
	}
}

/*
5 3 6
S..U.
.....
.....
.....
E....
ans: 10
 */
//3시간