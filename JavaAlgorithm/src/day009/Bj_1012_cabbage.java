package day009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_1012_cabbage {

    static int N,M;
    static int[][] map;
    static boolean[][] visited;
    static Queue<int[]> qu;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    static int result;
    public static void bfs(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 1 && !visited[i][j]){
                    result += 1;
                    visited[i][j] = true;
                    qu.add(new int[] {i, j});
                    while(!qu.isEmpty()){
                        int[] cur = qu.poll();
                        int sr = cur[0];
                        int sc = cur[1];
                        for (int k = 0; k < 4; k++) {
                            int nr = sr + dr[k];
                            int nc = sc + dc[k];
                            if(nr>=0&&nc>=0&&nr<N&&nc<M&&!visited[nr][nc]&&map[nr][nc]==1){
                                visited[nr][nc] = true;
                                qu.add(new int[]{nr, nc});
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                map[Y][X] = 1;
            }
            visited = new boolean[N][M];
            qu = new LinkedList<>();
            result = 0;
            bfs();
            System.out.println(result);

        }
    }
}
