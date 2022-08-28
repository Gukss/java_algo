package _20220827;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Bj_2580_sudoku {
	
	static int[][] map;
	static boolean totalb;
	
	public static void row() {
		for (int i = 0; i < 9; i++) { //9번 반복
			int count = 0;
			int idx = -1;
			boolean bp = false;
			
			int[] flag = new int[9]; //숫자는 인덱스+1, 0이 아닌 값이 들어있으면 해당 숫자가 존재한다.
			
			for (int j = 0; j < 9; j++) {
				if(map[i][j] != 0) { //0이 아니면
					flag[map[i][j]-1] = j+1; //해당 숫자로 초기화하기
				}else { //0이면 인덱스 저장
					count += 1;
					idx = j;
				}
				if(count > 1) { //count가 1보다 커지면 다음 행 조사하기
					bp = true;
					break;
				}
			}
			if(!bp) {				
				//break 안되고 넘어왔으면 1개만 비어있다.
				for (int j = 0; j < 9; j++) {
					if(flag[j] == 0) {
						map[i][idx] = j+1;
					}
				}
			}
		}
//		System.out.println("row--------------");
//		for(int[] x: map) {
//			System.out.println(Arrays.toString(x));
//		}
	}
	
	public static void col() {
		for (int i = 0; i < 9; i++) { //9번 반복
			int count = 0;
			int idx = -1;
			boolean bp = false;
			
			int[] flag = new int[9]; //숫자는 인덱스+1, 0이 아닌 값이 들어있으면 해당 숫자가 존재한다.
			
			for (int j = 0; j < 9; j++) {
				if(map[j][i] != 0) { //0이 아니면
					flag[map[j][i]-1] = j+1; //해당 숫자로 초기화하기
				}else { //0이면 인덱스 저장
					count += 1;
					idx = j;
				}
				if(count > 1) { //count가 1보다 커지면 다음 행 조사하기
					bp = true;
					break;
				}
			}
			if(!bp) {				
				//break 안되고 넘어왔으면 1개만 비어있다.
				for (int j = 0; j < 9; j++) {
					if(flag[j] == 0) {
						map[idx][i] = j+1;
					}
				}
			}
		}
//		System.out.println("col--------------");
//		for(int[] x: map) {
//			System.out.println(Arrays.toString(x));
//		}
	}
	
	public static void third() {
		for (int i = 0; i < 9; i+=3) {
			for (int j = 0; j < 9; j+=3) { //3*3 반복
				
				int count = 0;
				int r = -1;
				int c = -1;
				boolean bp = false;
				int[] flag = new int[9]; //숫자는 인덱스+1, 0이 아닌 값이 들어있으면 해당 숫자가 존재한다.
				
				for (int q = i; q < i+3; q++) {
					for (int w = j; w < j+3; w++) {
						if(map[q][w] != 0) {
							//flag[map[q][w]-1] = j+1;
							flag[map[q][w] - 1] = 1; //일단 1로 해당 숫자가 있다, 만 표시
						}else { //0이면 인덱스 저장
							count += 1;
							r = q;
							c = w;
						}
						if(count > 1) { //count가 1보다 커지면 다음 행 조사하기
							bp = true;
							break;
						}
					}
				}
				if(!bp) {				
					//break 안되고 넘어왔으면 1개만 비어있다.
					for (int x = 0; x < 9; x++) {
						if(flag[x] == 0) {
							map[r][c] = x+1;
						}
					}
				}
				
			}
		}
//		System.out.println("thrid--------------");
//		for(int[] x: map) {
//			System.out.println(Arrays.toString(x));
//		}
	}
	
	public static boolean check() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(map[i][j] == 0) { //0이 있으면 계속 반복하게
					return true;
				}
			}
		}
		return false; //0이 없으면 반복 종료
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        map = new int[9][9];
        for (int i = 0; i < 9; i++) {
        	st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
        
        totalb = true; //while 반복 위해
        
        //행, 열, 3*3 반복하면서 1개 비어있으면 빈 자리 찾아서 넣고 1개보다 많으면 continue하기 -> 종료조건은 어떻게 하지?
        totalb = check(); //시작할 때 검사하고 들어온다 백트래킹
        while(totalb) {        	
        	row();
        	col();
        	third();
        	totalb = check();
        }
        
        StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
    }
}
//이렇게 풀면 안된다. 비어있는 곳 찾아서 모두 다 넣어보고 안되면 백트래킹 하기