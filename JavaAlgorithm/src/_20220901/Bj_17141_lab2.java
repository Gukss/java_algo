package _20220901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_17141_lab2 {
    static int N, M;
    static int[][] map;
    static ArrayList<Pos> list;
    static int[] sel;
    static int[][] cpmap;
    static Queue<Pos> qu;
    static int[] dr = {-1, 0, 1, 0}; //위, 오, 밑, 왼
    static int[] dc = {0, 1, 0, -1};
    static int totalmin;
    static ArrayList<Integer> result;

    public static void copy(){
        cpmap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == 2){
                    cpmap[i][j] = 0;
                }else{
                    cpmap[i][j] = map[i][j];
                }
            }
        }
    }

    public static void bfs(){
        qu = new LinkedList<>();
        for(int x: sel){
            int r = list.get(x).r;
            int c = list.get(x).c;
            cpmap[r][c] = 1;
            qu.add(new Pos(r, c, 1));
        }
        while(!qu.isEmpty()){
            Pos cur = qu.poll();
            int sr = cur.r;
            int sc = cur.c;
            for (int i = 0; i < 4; i++) {
                int nr = sr + dr[i];
                int nc = sc + dc[i];
                if(nr>=0&&nc>=0&&nr<N&&nc<N && cpmap[nr][nc] == 0){ //0이고 -> 벽아니고방문안한곳
                    cpmap[nr][nc] = cur.d+1; //add할 때 표시하기
                     //지역에서 max 값을 구하고 반복문 빠져나가면 전체에서 min값을구한다. -> 첫 시작값을 0이 아닌 1로 해서 cur.d+1-1로 최대값을 구한다.
                    qu.add(new Pos(nr, nc, cur.d+1));
                }
            }
        }
    }

    public static void check(){
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(cpmap[i][j] == 0){
                    result.add(-1);
//                    totalmin = -1;
                    return;
                }else if(cpmap[i][j] >= 1){
                    max = Math.max(max, cpmap[i][j]);
                }
            }
        }
        result.add(max);
//        totalmin = Math.min(max, totalmin);
    }

    public static void comb(int idx, int start){
        if(idx == M){
            copy();
            bfs();
            check(); //bfs안에서 지역 max를 구하려 했으나 바이러스가 모두 퍼졌는지 확인이 필요하다.
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
        sel = new int[M];
        totalmin = Integer.MAX_VALUE;
        result = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int temp = Integer.parseInt(st.nextToken());
                if(temp == 1){ //벽은 음수로 저장
                    map[i][j] = -1;
                }else if(temp == 2){ //2일 때 list에 저장
                    list.add(new Pos(i, j, 0));
                    map[i][j] = temp;
                }
            }
        }

        comb(0, 0);
//        System.out.println(totalmin);
        int resultSize = result.size();
        int count = 0;
        for (int i = 0; i < resultSize; i++) {
            if(result.get(i) == -1){
                count += 1;
            }else{
                totalmin = Math.min(totalmin, result.get(i));
            }
        }
        if(resultSize == count){
            System.out.println(-1);
        }else{
            System.out.println(totalmin-1); //시작을 1부터 해서 -1해주기
        }
    }

    public static class Pos{
        int r, c, d;

        public Pos(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
}
//45분
