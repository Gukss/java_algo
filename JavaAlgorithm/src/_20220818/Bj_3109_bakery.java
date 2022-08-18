package _20220818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_3109_bakery {
    static int[] dr = {-1, 0, 1}; //오른 위, 중간, 밑
    static int[] dc = {1, 1, 1};
    static int R, C;
    static char[][] map;
    static int result = 0;
    static boolean[][] v;
    public static boolean check2(int row, int col){
        if(col == C-1){
            result += 1;
            return true;
        }
        int sr = row;
        int sc = col;

        for (int i = 0; i < 3; i++) {
            int nr = sr + dr[i];
            int nc = sc + dc[i];
            if(nr>=0 && nc >= 0 && nr <R && nc < C && map[nr][nc] != 'x' && map[nr][nc] != 'o'){
                if(check2(nr, nc)){
                    map[nr][nc] = 'o';
                    return true;
                }
            }
        }
        return false;
    }

//    public static void check(int row){
//        if(row>R){
//            return;
//        }
//        int col = 0;
//        int sr = row;
//        int sc = 0;
//        boolean eflag = false;
//        while(col<C){
//            for (int i = 0; i < 3; i++) {
//                int nr = sr + dr[i];
//                int nc = sc + dc[i];
//                if(nr>=0 && nc >= 0 && nr <R && nc < C && map[nr][nc] != 'x' && map[nr][nc] != 'o'){
//                    map[nr][nc] = 'o';
//                    sr = nr;
//                    sc = nc;
//                    break;
//                }
//            }
//            col += 1;
//            if(sc == C-1){
//                eflag = true;
//            }
//        }
//        if(eflag) result += 1;
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String temp = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = temp.charAt(j);
            }
        }
        for (int i = 0; i < C; i++) {
            check2(i, 0);
        }
        System.out.println(result);
    }
}
//실패했던 자리에 도착한 경우 실패하도록 처리하면 시간 절약
/*
5 9
.x.....x.
.x..x..x.
.x..x..x.
....x....
.x..x..x.
*/
