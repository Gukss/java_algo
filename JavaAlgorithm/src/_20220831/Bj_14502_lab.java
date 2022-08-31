package _20220831;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_14502_lab {
    static int N,M;
    static int[][] map;
    static ArrayList<Pos> list;
    static ArrayList<Pos> twoList;
    static Queue<Pos> qu;
    static int[] sel;
    static int[] dr = {-1, 0, 1, 0}; //위, 오, 밑, 왼
    static int[] dc = {0, 1, 0, -1};
    static int[][] cpmap;
    static int sum, max;

    public static void cp(){
        cpmap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                cpmap[i][j] = map[i][j];
            }
        }
    }

    public static void mark(){
        for(int x: sel){
            Pos cur = list.get(x);
            cpmap[cur.r][cur.c] = 1;
        }
    }

    public static void bfs(){
        for (int i = 0; i < twoList.size(); i++) {
            Pos temp = twoList.get(i);
            qu.add(new Pos(temp.r, temp.c));
        }

        while(!qu.isEmpty()){ //큐에 처음 시작 좌표는 항상 들어있어야 한다.
            Pos cur = qu.poll();
            int sr = cur.r;
            int sc = cur.c;
            for (int i = 0; i < 4; i++) {
                int nr = sr + dr[i];
                int nc = sc + dc[i];
                if(nr>=0&&nc>=0&&nr<N&&nc<M && cpmap[nr][nc] == 0){
                    cpmap[nr][nc] = 2;
                    qu.add(new Pos(nr, nc));
                }
            }
        }
    }

    public static void check(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(cpmap[i][j] == 0){
                    sum += 1;
                }
            }
        }
    }

    public static void comb(int idx, int start){
        if(idx == 3){ //sel에 조합 저장돼있다.
            sum = 0;
            cp();
            mark();
            bfs();
            check();
//            redo();
            max = Math.max(max, sum);
            return;
        }
        for (int i = start; i < list.size(); i++) {
            sel[idx] = i;
            comb(idx+1, i+1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        list = new ArrayList<>();
        sum = 0;
        max = Integer.MIN_VALUE;
        qu = new LinkedList<>();
        twoList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0){
                    list.add(new Pos(i,j));
                }else if(map[i][j] == 2){
                    twoList.add(new Pos(i,j));
                }
            }
        }
        sel = new int[3];

        // 0인곳 입력받고, 2인곳 입력받고, 0인곳 개수C3 해서 해당 좌표에다가 1 입력하고
        // 2인곳 큐에서 꺼내서 bfs하고
        // 0인 곳 개수 세기

        comb(0, 0);
        System.out.println(max);
    }
    public static class Pos{
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
//1시간