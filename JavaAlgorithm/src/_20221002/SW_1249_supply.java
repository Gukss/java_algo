package _20221002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_1249_supply {
    static int T,N;
    static int[][] map, data;
    static int result;
    static boolean[][] v;
    static int[] dr = {-1, 0, 1, 0}; //위, 오, 밑, 왼
    static int[] dc = {0, 1, 0, -1}; //위, 오, 밑, 왼
    static Queue<Pos> qu;

    public static void bfs(int r, int c){
        qu.add(new Pos(0,0));
        while(!qu.isEmpty()){
            Pos cur = qu.poll();
            int sr = cur.r;
            int sc = cur.c;
            if(sr==N-1&&sc==N-1){
                result = Math.min(result, data[sr][sc]);
            }
            if(result <= data[sr][sc]){
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nr = sr + dr[i];
                int nc = sc + dc[i];
                if(nr>=0&&nr<N&&nc>=0&&nc<N && (!v[nr][nc] || data[nr][nc] > data[sr][sc] + map[nr][nc])){
                    v[nr][nc] = true;
                    data[nr][nc] = data[sr][sc] + map[nr][nc];
                    qu.add(new Pos(nr,nc));
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());

            map = new int[N][N];
            data = new int[N][N];
            v = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                String temp = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = temp.charAt(j) - '0';
                }
            }

            result = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                Arrays.fill(data[i], Integer.MAX_VALUE);
            }
            data[0][0] = 0;
            qu = new LinkedList<>();

            bfs(0,0);

            sb.append("#"+test_case+" "+result+"\n");
        }
        System.out.println(sb);
    }

    public static class Pos{
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
