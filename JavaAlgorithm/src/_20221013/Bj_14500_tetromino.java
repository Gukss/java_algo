package _20221013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj_14500_tetromino {
    static int N,M;
    static int[][] map;
    static int totalsum;
    static int result;
    static int[] resultarr;
    static int[] dr = {-1, 0, 1, 0}; //위, 오, 밑, 왼
    static int[] dc = {0, 1, 0, -1}; //위, 오, 밑, 왼

    public static void checkmax(){
        int sum = 0;
        for (int i = 0; i <= N-2; i++) {
            for (int j = 0; j <= M-4; j++) {
                sum = 0;
                for (int k = i; k < i+2; k++) { //세로
                    for (int l = j; l < j+4; l++) { //가로
                        sum += map[k][l];
                    }
                }
                if(totalsum < sum){
                    totalsum = sum;
                    resultarr = new int[]{i,j,0}; //마지막은 방향 0은 가로
                }
            }
        }

        for (int i = 0; i <= N-4; i++) {
            for (int j = 0; j <= M-2; j++) {
                sum = 0;
                for (int k = i; k < i+4; k++) { //세로
                    for (int l = j; l < j+2; l++) {
                        sum += map[k][l];
                    }
                }
                if(totalsum < sum){
                    totalsum = sum;
                    resultarr = new int[]{i,j,1}; //마지막은 방향 1은 세로
                }
            }
        }
    }

    public static void maketet(int r, int c, int dir){
        int max = Integer.MIN_VALUE;
        int sr = -1;
        int sc = -1;
        int[][] smallmap;
        PriorityQueue<int[]> qu = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[2] - o1[2];
            }
        });

        boolean[][] v = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(v[i], true);
        }
        if(dir == 0){ //가로
            for (int i = r; i < r+2; i++) {
                for (int j = c; j < c+4; j++) {
                    if(max < map[i][j]){
                        max = map[i][j];
                        sr = i;
                        sc = j;
                    }
                    v[i][j] = false;
                }
            }
        }else{ //세로
            smallmap = new int[4][2];
            for (int i = r; i < r+4; i++) {
                for (int j = c; j < c+2; j++) {
                    if(max < map[i][j]){
                        max = map[i][j];
                        sr = i;
                        sc = j;
                    }
                    v[i][j] = false;
                }
            }
        }

        qu.add(new int[]{sr,sc,max});
        v[sr][sc] = true;
        int count = 0;
        while(!qu.isEmpty()){
            int[] cur = qu.poll();
            int ar = cur[0];
            int ac = cur[1];
            count += 1;
            result += cur[2];
            if(count == 4){

                break;
            }
            for (int i = 0; i < 4; i++) {
                int nr = ar + dr[i];
                int nc = ac + dc[i];
                if(nr>=0&&nr<N&&nc>=0&&nc<M && !v[nr][nc]){
                    qu.add(new int[]{nr,nc,map[nr][nc]});
                    v[nr][nc] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        totalsum = Integer.MIN_VALUE;
        resultarr = new int[3];
        checkmax();
        result = 0;
        maketet(resultarr[0],resultarr[1],resultarr[2]);
        System.out.println(result);
    }
}
/*
3 5
5 5 5 5 5
5 100 1 1 100
5 5 5 5 5
ans: 202
 */