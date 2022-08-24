package _20220824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_17070_pipe {
    static Queue<int[]> qu;
    static boolean[][] v;
    static int[] dr = {0, 1, 1}; //가로, 세로, 대각선
    static int[] dc = {1, 0, 1};
    static int N;

    //	public static void bfs() {
//		while(!qu.isEmpty()) {
//			int[] cur = qu.poll();
//			int dir = cur[2];
//			int sr = cur[0];
//			int sc = cur[1];
//
//			for (int i = 0; i < 3; i++) { //가로-0, 세로-1, 대각선-2
//				if(i != 2 && i == dir) { //대각선 아니고, 같으면 못 가는 방향
//					continue;
//				}
//				int nr = sr + dr[i];
//				int nc = sc + dc[i];
//				if(nr>=0 && nc>=0 && nr<N && nc<N && !v[nr][nc]) {
//					v[nr][nc] = true;
//					qu.add(new int[] {nr, nc, i});
//				}
//			}
//		}
//	}
    static int result;
    static int[][] map;

    public static void dfs(int r, int c, int dir) {
        if(r==N-1 && c==N-1) {
            result += 1;
            return;
        }
        for (int i = 0; i < 3; i++) { //가로-0, 세로-1, 대각선-2
            if((dir==0 && i==1) || (dir==1 && i==0)) {
                continue;
            }
            if(i==2) {
                int nr2 = r+1;
                int nc2 = c;
                int nr3 = r;
                int nc3 = c+1;
                if(nr2<0 || nc2<0 || nr3<0 || nc3<0 || nr2>=N || nc2>=N || nr3>=N || nc3>=N || map[nr2][nc2] == 1 || map[nr3][nc3] == 1) {
                    continue;
                }
            }
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr>=0 && nc>=0 && nr<N && nc<N && !v[nr][nc] && map[nr][nc] != 1) {
                v[nr][nc] = true;
                dfs(nr, nc, i);
                v[nr][nc] = false;
            }
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        v = new boolean[N][N];
        qu = new LinkedList<>();

        qu.add(new int[] {0, 1, 0}); //가로-0, 세로-1, 대각선-2
        v[0][1] = true;
        v[0][0] = true;
        result = 0;
        //bfs();
        dfs(0, 1, 0);
        System.out.println(result);
    }

}
//1시간 -> bfs가 아니다.. 방법의 수: dfs, 최단거리bfs