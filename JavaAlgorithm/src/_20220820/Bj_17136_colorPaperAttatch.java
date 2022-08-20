package _20220820;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_17136_colorPaperAttatch {
    static int[][] map;
    static int[] remain = {0, 5, 5, 5, 5, 5};
    static int result = Integer.MAX_VALUE;

    public static boolean attatchCheck(int r, int c, int size){
        for (int i = r; i < r+size; i++) {
            for (int j = c; j < c+size; j++) {
                if(i>9 || j > 9 || map[i][j] != 1 ){ //범위 벗어나는지 먼저 검사하기
                    return false;
                }
            }
        }
        return true;
    }

    public static void attatch(int r, int c, int size){
        for (int i = r; i < r+size; i++) {
            for (int j = c; j < c+size; j++) {
                map[i][j] = 0;
            }
        }
    }

    public static void dettatch(int r, int c, int size){
        for (int i = r; i < r+size; i++) {
            for (int j = c; j < c+size; j++) {
                map[i][j] = 1;
            }
        }
    }

    public static void dfs(int r, int c, int count){

        if(r>=9 && c>9){ //끝까지 갔을 때
            result = Math.min(result, count);
            return;
        }
        if(result <= count){ //백트래킹
            return;
        }

        if(c>9){ // 오른쪽 끝까지 갔을 때 행 바꾸기
            dfs(r+1, 0, count);
            return;
        }

        if(map[r][c] == 1){ //1이 있으면
            for (int i = 5; i > 0 ; i--) { //큰 것 부터
                if(remain[i] > 0 && attatchCheck(r, c, i)){ //해당 크기의 색종이가 남아있고, 붙일 수 있으면
                    attatch(r, c, i);
                    remain[i] -= 1;

                    dfs(r, c+1, count+1);

                    dettatch(r, c, i);
                    remain[i] += 1;
                }
            }
        }else{
            dfs(r, c+1, count);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[10][10];

        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);
        if(result == Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(result);
        }
    }
}
