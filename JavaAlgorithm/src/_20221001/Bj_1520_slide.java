package _20221001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj_1520_slide {
    static int N,M;
    static int[][] map, data;
    static int[] dr = {-1, 0, 1, 0}; //위, 오, 밑, 왼
    static int[] dc = {0, 1, 0, -1}; //위, 오, 밑, 왼

    public static int start(int r, int c){
        if(r==N-1&&c==M-1){
            return 1;
        }
        if(data[r][c] != -1){
            return data[r][c];
        }
        data[r][c] = 0;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr>=0&&nr<N&&nc>=0&&nc<M && map[r][c] > map[nr][nc]){
                data[r][c] += start(nr,nc);
            }
        }
        return data[r][c];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //문제랑 N,M바꼈다.
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        data = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(data[i], -1);
        }
        start(0,0);
        System.out.println(data[0][0]);
    }
}
