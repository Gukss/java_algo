package _20221103;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj_11559_puyopuyo {
    static char[][] map;
    static int[] dr = {-1, 0, 1, 0}; //위, 오, 밑, 왼
    static int[] dc = {0, 1, 0, -1}; //위, 오, 밑, 왼
    static boolean[][] v;
    static int count;

    public static boolean dfs(int r, int c, int depth){
//        v[r][c] = true;
//        if(depth == 4){
//            return true;
//        }
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr>=0&&nr<12&&nc>=0&&nc<6 && !v[nr][nc] && map[nr][nc] != '.' && map[nr][nc]==map[r][c]){
                v[nr][nc] = true;
                if(dfs(nr,nc,depth+1)){
                    map[nr][nc] = '1';
                    return true;
                }
            }
        }
        if(depth >= 4){
            return true;
        }
        return false;
    }
    
    //dfs로 v에 표시하면서 탐색
    public static void start(){
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if(!v[i][j] && map[i][j] != '.'){
                    if(i+1<12 && j+1<6 && j-1>=0 && (map[i+1][j] == map[i][j]) &&(map[i+1][j+1] == map[i][j]) &&(map[i+1][j-1] == map[i][j])){
                        map[i][j] = '1';
                        map[i+1][j+1] = '1';
                        map[i+1][j-1] = '1';
                        map[i+1][j] = '1';
                        v[i][j] = true;
                        v[i+1][j+1] = true;
                        v[i+1][j-1] = true;
                        v[i+1][j] = true;
                    }
                    v[i][j] = true;
                    if(dfs(i,j, 1)){
                        map[i][j] = '1';
                    }
                }
            }
        }
    }

    public static boolean fall(){
        boolean result = false;
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 12; i++) { //위부터 검사
                if(map[i][j] == '1'){
                    for (int k = i; k > 0; k--) {
                        map[k][j] = map[k-1][j];
                        map[k-1][j] = '.';
                    }
                    result = true;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[12][6];
        for (int i = 0; i < 12; i++) {
            String temp = br.readLine();
            for (int j = 0; j < 6; j++) {
                map[i][j] = temp.charAt(j);
            }
        }
        count = 0;

        do{
            v = new boolean[12][6];
            start();
            count += 1;
        }while(fall());
        System.out.println(count-1);
    }
}
/*
......
......
......
......
......
......
.G....
RR....
RY....
RYG...
RRY...
RYYGG.
ans: 3
 */