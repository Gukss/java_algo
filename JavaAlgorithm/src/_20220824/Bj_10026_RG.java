package _20220824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Bj_10026_RG {

    static char[][] map;
    static int N;
    static boolean[][] v;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static Queue<int[]> qu;

    public static void nbfs() {
        while(!qu.isEmpty()) {
            int[] pos = qu.poll();
            char cur = map[pos[0]][pos[1]];
            int sr = pos[0];
            int sc = pos[1];
            for (int j = 0; j < 4; j++) {
                int nr = sr + dr[j];
                int nc = sc + dc[j];
                if(nr>=0 && nc>=0 && nr<N && nc<N && !v[nr][nc] && map[nr][nc] == cur) {
                    qu.add(new int[] {nr, nc});
                    v[nr][nc] = true;
                }
            }
        }
    }

    public static void obfs() {
        while(!qu.isEmpty()) {
            int[] pos = qu.poll();
            char cur = map[pos[0]][pos[1]];
            int sr = pos[0];
            int sc = pos[1];
            for (int j = 0; j < 4; j++) {
                int nr = sr + dr[j];
                int nc = sc + dc[j];
                if(cur=='B') {
                    if(nr>=0 && nc>=0 && nr<N && nc<N && !v[nr][nc] && map[nr][nc] == cur) {
                        qu.add(new int[] {nr, nc});
                        v[nr][nc] = true;
                    }
                }else {
                    if(nr>=0 && nc>=0 && nr<N && nc<N && !v[nr][nc] && map[nr][nc] != 'B') {
                        qu.add(new int[] {nr, nc});
                        v[nr][nc] = true;
                    }
                }

            }
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            String temp = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = temp.charAt(j);
            }
        }

        qu = new LinkedList<>();
        v = new boolean[N][N];
        int nr = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!v[i][j]) {
                    v[i][j] = true;
                    qu.add(new int[] {i, j});
                    nbfs();
                    nr += 1;
                }
            }
        }

        qu = new LinkedList<>();
        v = new boolean[N][N];
        int or = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!v[i][j]) {
                    v[i][j] = true;
                    qu.add(new int[] {i, j});
                    obfs();
                    or += 1;
                }
            }
        }
        System.out.printf("%d %d\n", nr, or);
    }

}
//30분