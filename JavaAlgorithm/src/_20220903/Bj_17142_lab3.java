package _20220903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj_17142_lab3 {
    static int N,M;
    static int[][] map;
    static ArrayList<Pos> list;
    static int[] sel;
    static Queue<Pos> qu;
    static int[][] cpmap;
    static int[] dr = {-1, 0, 1, 0}; //위, 오, 밑, 왼
    static int[] dc = {0, 1, 0, -1};
    static ArrayList<Integer> checklist;

    public static void cp(){
        cpmap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cpmap[i][j] = map[i][j];
            }
        }
    }

    public static void bfs(){
        for (int i = 0; i < M; i++) {
            Pos cur = list.get(sel[i]);
            qu.add(new Pos(cur.r, cur.c, cur.count));
            cpmap[cur.r][cur.c] = cur.count;
        }
        while(!qu.isEmpty()){
            Pos cur = qu.poll();
            int sr = cur.r;
            int sc = cur.c;
            for (int i = 0; i < 4; i++) {
                int nr = sr + dr[i];
                int nc = sc + dc[i];
                if(nr>=0&&nc>=0&&nr<N&&nc<N && (cpmap[nr][nc] == 0)){
                    cpmap[nr][nc] = cur.count+1;
                    qu.add(new Pos(nr, nc, cur.count+1));
                }
            }
        }
    }

    public static void maxcheck(){
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(cpmap[i][j] == 0) {
                    checklist.add(-1);
                    return;
                }else{
                    max = Math.max(max, cpmap[i][j]);
                }
            }
        }
        checklist.add(max);
    }

    public static void comb(int idx, int start){
        if(idx == M){
            cp();
            bfs();
            maxcheck();
//            System.out.println(Arrays.toString(sel));
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
        map = new int[N][N];
        list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int temp = Integer.parseInt(st.nextToken());
                if(temp == 1 || temp == 2){ //벽, 바이러스 모두 음수로 나타냄
                    map[i][j] = -1 * temp;
                }
                if(temp == 2){
                    list.add(new Pos(i, j, 1)); //count는 빈칸과 구분하기 위해 1부터
                }
            }
        }
        sel = new int[M];
        checklist = new ArrayList<>();
        qu = new LinkedList<>();

        comb(0, 0);
        checklist.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });

        for (int i = 0; i < checklist.size(); i++) {
            int check = checklist.get(i);
            if(check != -1){
                System.out.println(check-1);
                return;
            }
        }
        System.out.println(-1);

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
//45분