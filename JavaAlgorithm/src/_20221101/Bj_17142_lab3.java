package _20221101;

import java.awt.Checkbox;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_17142_lab3 {
    static int N,M; //연구소 크기, 활성 바이러스 개수
    static int[][] map;
    static List<Pos> list;
    static int numlab;
    static int[] sel;
    static Queue<Pos> qu;
    static boolean[][] v;
    static int[] dr = {-1, 0, 1, 0}; //위, 오, 밑, 왼
    static int[] dc = {0, 1, 0, -1}; //위, 오, 밑, 왼
    static int tempresult;
    static int result;
    static int truecount;

    public static int[][] cpmap() {
        int[][] cpmap = new int[N][N];
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                cpmap[i][j] = map[i][j];
            }
        }
        return cpmap;
    }

    public static void check(int[][] cpmap){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(cpmap[i][j] == -3){ //빈칸일 때

                }else{

                }
            }
        }
    }

    public static void bfs(int[][] cpmap) {
        qu = new LinkedList<>();
        for(int x: sel) {
            Pos cur = list.get(x);
            qu.add(cur);
            cpmap[cur.r][cur.c] = cur.time;
        }
        while(!qu.isEmpty()) {
            Pos cur = qu.poll();
            int sr = cur.r;
            int sc = cur.c;
            int stime = cur.time;

            for(int i=0;i<4;i++) {
                int nr = sr + dr[i];
                int nc = sc + dc[i];
                if(nr>=0&&nr<N&&nc>=0&&nc<N && !v[nr][nc] && cpmap[nr][nc] != -1 && cpmap[nr][nc] < 0) {
                    if(cpmap[nr][nc] == -2) { //비활성화 바이러스일 때
                        cpmap[nr][nc] = 0;
                        qu.add(new Pos(nr, nc, 0));
                    }else {
                        cpmap[nr][nc] = stime+1;
                        qu.add(new Pos(nr, nc, stime+1));
                    }
                }
            }
        }
        check(cpmap);
    }

    public static void start() {
        v = new boolean[N][N];
        int[][] cpmap = cpmap();
        bfs(cpmap);
    }

    public static void comb(int idx, int start) {
        if(idx == M) {
            start();
            return;
        }
        for(int i=start;i<numlab;i++) {
            sel[idx] = i;
            comb(idx+1, i+1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //0빈칸, 1벽, 2바이러스
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        list = new ArrayList<>();
        numlab = 0;
        truecount = 0;

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) { //list에 넣기
                    list.add(new Pos(i,j,0));
                    numlab += 1;
                    map[i][j] = -2; //-2가 비활성화 바이러스
                }else if(map[i][j] == 1) {
                    map[i][j] = -1; //-1은 벽
                }else if(map[i][j] == 0) { //-3은 빈칸
                    map[i][j] = -3;
                }
            }
        }

        result = Integer.MAX_VALUE;
        sel = new int[M];
        comb(0, 0);

        System.out.println(result);
    }

    public static class Pos{
        int r, c, time;

        public Pos(int r, int c, int time) {
            super();
            this.r = r;
            this.c = c;
            this.time = time;
        }

    }

}