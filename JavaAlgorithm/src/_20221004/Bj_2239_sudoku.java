package _20221004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Bj_2239_sudoku {
    static int[][] map;
    static List<Pos> list;
    static StringBuilder sb = new StringBuilder();

    public static void init(){
        list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(map[i][j] == 0){
                    list.add(new Pos(i,j));
                }
            }
        }
    }

    public static void checkrow(int r, int c, boolean[] check){
        for (int i = 0; i < 9; i++) {
            if(map[r][i]!=0){
                check[map[r][i]] = true;
            }
        }
    }
    public static void checkcol(int r, int c, boolean[] check){
        for (int i = 0; i < 9; i++) {
            if(map[i][c]!=0){
                check[map[i][c]] = true;
            }
        }
    }
    public static void checkth(int r, int c, boolean[] check){
        int nr = r/3;
        int nc = c/3;
        for (int i = nr*3; i < (nr*3)+3; i++) {
            for (int j = nc*3; j < (nc*3)+3; j++) {
                if(map[i][j]!=0){
                    check[map[i][j]] = true;
                }
            }
        }
    }

    public static boolean start(int idx){
        if(list.size()==idx){
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
            return true;
        }
        boolean[] check = new boolean[10];
        Pos cur = list.get(idx);
        checkcol(cur.r, cur.c, check);
        checkrow(cur.r, cur.c, check);
        checkth(cur.r, cur.c, check);

        for (int i = 1; i <= 9; i++) {
            if(!check[i]){
                check[i] = true;
                map[cur.r][cur.c] = i;
                if(start(idx+1)){
                    return true;
                }
                map[cur.r][cur.c] = 0;
                check[i] = false;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[9][9];
        for (int i = 0; i < 9; i++) {
            String temp = br.readLine();
            for (int j = 0; j < 9; j++) {
                map[i][j] = temp.charAt(j) - '0';
            }
        }
        init();
        start(0);
        System.out.println(sb);
    }

    public static class Pos{
        int r,c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
