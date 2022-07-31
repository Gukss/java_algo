package day003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Bj_5427_fire2 {
	
	public static int w,h; //너비, 높이 입력
	public static LinkedList<int[]> fire_pos; //불 좌표 저장하는 큐
	public static LinkedList<int[]> qu; //사람 좌표 저장하는 큐
	public static char[][] room; //방 문자 저장하는 배열 
	public static int[] dr = {-1,0,1,0}; //사방 탐색
	public static int[] dc = {0,1,0,-1};
	public static int result; //결과 저장

	public static void check() {
		int pos_size=0; //큐의 사이즈를 저장해 add하기 전 큐의 크기만큼 반복
		int f_pos_size=0;
		
		while(!qu.isEmpty()) { //사람 위치 큐가 빌 때 까지, 불이 없을 수 있어 불 큐로 하면 안된다.
			f_pos_size = fire_pos.size(); //반복문 반복하기 위해 큐 사이즈 저장
			for(int k=0;k<f_pos_size;k++) {
				int[] f_pos = fire_pos.poll(); //큐에서 좌표 꺼내기
				for (int j = 0; j < 4; j++) { //사방 탐색
					int fire_nr = f_pos[0] + dr[j];
					int fire_nc = f_pos[1] + dc[j];

					if(fire_nr<0 || fire_nr>=h || fire_nc<0 || fire_nc >= w) { //불이 방을 넘어가면 continue
						continue;
					}
					if(room[fire_nr][fire_nc] == '.' || room[fire_nr][fire_nc] == '@') { //.or@이면 불이 붙을 수 있다.
						fire_pos.add(new int[] {fire_nr, fire_nc}); //불 좌표를 add 해준다.
						room[fire_nr][fire_nc] = '*'; //room에 불위치를 표시해 준다.
					}
				}
			}
			
			pos_size = qu.size(); //사람 큐 사이즈 저장
			for (int k = 0; k < pos_size; k++) {
				int[] cur = qu.poll(); //큐에서 좌표 꺼내기
				for (int j = 0; j < 4; j++) { //사방 탐색
					int nr = cur[0] + dr[j];
					int nc = cur[1] + dc[j];
					if(nr<0 || nr >= h || nc<0 || nc >= w) { //완료조건이다. 사람이 벽에 붙었을 때
						result = cur[2]+1; //result=cur[2]하니까 조건에서 바로 탈출할 수 있는 경우를 걸러내지 못했다.
						return; //0부터 시작해서 +1하고 함수를 종료하면 result==0에서 불가능을 거를수 있다.
					}
					if(room[nr][nc] == '.') { //.일 경우 사람이 이동할 수 있다.
						qu.add(new int[] {nr, nc, cur[2]+1}); //사람이 이동한 좌표와 시간을 +1 해서 저장한다.
						room[nr][nc] = '@'; //지도에서 사람의 위치를 표시해 준다.
					}
				}
			}
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); //테스트 케이스 숫자 입력받기
		
		for (int i = 0; i < T; i++) { //테스트 케이스만큼 반복
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken()); //너비, 높이 입력받기
			h = Integer.parseInt(st.nextToken());
			room = new char[h][w]; //방 배열 생성
			
			fire_pos = new LinkedList<>(); //불 좌표 저장하기 위한 큐
			qu = new LinkedList<>(); //사람 좌표 저장하기 위한 큐
			
			for (int j = 0; j < h; j++) {
				String temp = br.readLine();
				for (int k = 0; k < w; k++) {
					room[j][k] = temp.charAt(k); //방 문자 입력받기
					if(room[j][k] == '@') { //사람일 때
						qu.add(new int[] {j, k, 0}); //좌표와 시간을 0으로 해서 사람 큐에 입력
					} else if(room[j][k] == '*'){ //불일 때
						fire_pos.add(new int[] {j,k}); //좌표를 불 큐에 입력
					}
				}
			}
			
			result=0; //result=0으로 시작
			check(); //함수 호출
			if(result == 0) { //불가능할 때				
				System.out.println("IMPOSSIBLE");
			} else { //탈출했을 때
				System.out.println(result);
			}
			while(!qu.isEmpty()) qu.poll(); //큐 비워주기
			while(!fire_pos.isEmpty()) fire_pos.poll();
		}
	}

}
