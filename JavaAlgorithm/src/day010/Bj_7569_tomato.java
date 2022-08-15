package day010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_7569_tomato {
    static Queue<int[]> qu;
    //위, 아래 한 칸 검사하고 사방 탐색
    static int[] dr = {-1, 0, 1, 0, 0, 0};
    static int[] dc = {0, 1, 0, -1, 0, 0};
    static int[] dh = {0, 0, 0, 0, 1, -1}; //위오밑왼 상하

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int result = 0;

        int[][][] map = new int[H][N][M];
        boolean[][][] visited = new boolean[H][N][M];
        qu = new LinkedList<>();

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                    if(map[i][j][k] == 1){
                        qu.add(new int[] {i,j,k,0}); //층, 행, 렬
                        visited[i][j][k] = true;
                    }else if(map[i][j][k] == -1){
                        visited[i][j][k] = true;
                    }
                }
            }
        }

        while(!qu.isEmpty()){
            int[] pos = qu.poll();
            int sr = pos[1];
            int sc = pos[2];
            int sh = pos[0];
            visited[sh][sr][sc] = true;
            for (int i = 0; i < 6; i++) {
                int nr = sr + dr[i];
                int nc = sc + dc[i];
                int nh = sh + dh[i];
                if(nr>=0 && nc>=0 && nh>=0 && nr<N && nc<M && nh<H && !visited[nh][nr][nc]){ //map==-1안해도 visited에서 걸러진다
                    visited[nh][nr][nc] = true;
                    map[nh][nr][nc] = pos[3]+1;
                    result = Math.max(result, pos[3]+1);
                    qu.add(new int[] {nh, nr, nc, pos[3]+1});
                }
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if(map[i][j][k] == 0){
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }
        System.out.println(result);

    }
}
//1시간