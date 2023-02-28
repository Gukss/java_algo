package _20230224;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_15686_Chicken {
	public static class Pos{
		int r,c,count;
		public Pos(int r, int c, int count) {
			this.r = r;
			this.c = c;
			this.count = count;
		}
	}

	public static int N,M;
	public static int[][] map;
	public static List<Pos> listChicken;
	public static List<Pos> listHouse;
	public static int size;
	public static int[] sel;
	public static Queue<Pos> qu;
	public static int[] dr = {-1, 0, 1, 0}; //위오밑왼
	public static int[] dc = {0, 1, 0, -1}; //위오밑왼
	public static boolean[][] v;
	public static int result;
	public static int count;

	public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			listChicken = new ArrayList<>();
			listHouse = new ArrayList<>();

			for(int i=0;i<N;i++){
					st = new StringTokenizer(br.readLine());
					for(int j=0;j<N;j++){
							map[i][j] = Integer.parseInt(st.nextToken());
							if(map[i][j] == 1){ //집이면
								listHouse.add(new Pos(i,j,0));
							}else if(map[i][j] == 2){ //치킨집이면
								listChicken.add(new Pos(i,j,0));
							}
					}
			}
			size = listChicken.size();
			sel = new int[M];
			result = Integer.MAX_VALUE;
			comb(0,0); //선택할 치킨집 조합으로 구하기
			System.out.println(result);
	}

	public static void comb(int idx, int start){
		if(idx == M){ //조합 완료
			count = 0; //집 별로 count를 누적
			check();
			result = Math.min(result, count); //집별로 누적한 count의 최솟값 저장
			return;
		}
		for(int i=start;i<size;i++){
			sel[idx] = i;
			comb(idx+1, i+1);
		}
	}

	public static void check(){
		for(Pos x: listHouse){ //집 모두 반복
			int len = Integer.MAX_VALUE;
			int r = x.r;
			int c = x.c;
			for(int i: sel){ //조합에 선택된 치킨집 반복
				Pos target = listChicken.get(i);
				len = Math.min(len, Math.abs(r-target.r) + Math.abs(c-target.c)); //집에서 선택한 치킨집까지 최소거리 구한다.
			}
			count += len;
		}
	}
}

//1시간 반
//bfs로 하면 시간초과
//맨하탄거리가 나와있고, 맨하탄거리가 출력값이면
//맨하탄거리를 어떻게 사용할 지 고민하자