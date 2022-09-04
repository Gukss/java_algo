package _20220903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_2146_bridge {
    static int N;
    static int[][] map;
    static Queue<Pos> qu;
    static int[] dr = {-1, 0, 1, 0}; //위, 오, 밑, 왼
    static int[] dc = {0, 1, 0, -1};
    static boolean[][] v;
    static int result;

    public static void bfs(){
        while(!qu.isEmpty()){
            Pos cur = qu.poll();
            int sr = cur.r;
            int sc = cur.c;
            for (int i = 0; i < 4; i++) {
                int nr = sr + dr[i];
                int nc = sc + dc[i];
                if(nr>=0&&nc>=0&&nr<N&&nc<N && map[nr][nc]==1){
                    map[nr][nc] = cur.count;
                    qu.add(new Pos(nr, nc, cur.count));
                }
            }
        }
    }

    public static void check(){
        int count = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == 1){
                    map[i][j] = count; //시작점도 바꿔주기
                    qu.add(new Pos(i, j, count++));
                    bfs();
                }
            }
        }
    }

    public static void start(int r, int c){
        while(!qu.isEmpty()){
            Pos cur = qu.poll();
            int sr = cur.r;
            int sc = cur.c;
            for (int i = 0; i < 4; i++) {
                int nr = sr + dr[i];
                int nc = sc + dc[i];
                if(nr>=0&&nc>=0&&nr<N&&nc<N && map[nr][nc]!=map[r][c] && !v[nr][nc]){
                    if(map[nr][nc] != 0){
                        result = Math.min(result, cur.count);
                    }
                    v[nr][nc] = true;
//                    map[nr][nc] = cur.count;
                    qu.add(new Pos(nr, nc, cur.count+1));
                }
            }
        }
    }

//    public static void start(int r, int c, int sr, int sc, int count){
//        for (int i = 0; i < 4; i++) {
//            int nr = r + dr[i];
//            int nc = c + dc[i];
//            if(nr>=0&&nc>=0&&nr<N&&nc<N && map[nr][nc] != map[sr][sc] && !v[nr][nc]){
//                v[nr][nc] = true;
//                if(map[nr][nc] != 0){ //다른 섬에 도착
//                    result = Math.min(result, count);
//                    return;
//                }
//                start(nr, nc,sr,sc, count+1);
//            }
//        }
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        qu = new LinkedList<>();
        map = new int[N][N];
        result = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //섬 bfs로 구분하기
        check();

        //dfs로 다른 섬으로 가면서 count늘리기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] != 0){
                    v = new boolean[N][N];
                    v[i][j] = true;
                    qu.add(new Pos(i, j, 0));
                    start(i, j);
                }
            }
        }
        System.out.println(result);
    }

    public static class Pos{
        int r, c, count;

        public Pos(int r, int c, int count) {
            this.r = r;
            this.c = c;
            this.count = count;
        }
    }
}
/*
5
0 0 0 0 0
0 0 1 0 0
0 1 0 0 0
0 0 0 0 0
0 0 0 0 0
 */
