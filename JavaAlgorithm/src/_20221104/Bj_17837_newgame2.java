package _20221104;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class Bj_17837_newgame2 {
    static int N,K;
    static List<Info>[][] map;
    static int[][] colormap;
    static int[] dr = {0,0,1,-1}; //오,왼,위,밑
    static int[] dc = {1,-1,0,0}; //오,왼,위,밑
    static Info[] order;

    public static void white(int r, int c, int nr, int nc, int turn){
        List<Info> temp = new ArrayList<>(); //밑에 남겨놓을 말 저장
        for (int i = 0; i < map[r][c].size(); i++) {
            Info cur = map[r][c].get(i); //저장
            if(cur.order == turn){ //옮길말 부터
                for (int j = i; j < map[r][c].size(); j++) { //반복하면서 옮기기
                    Info move = map[r][c].get(j);
                    map[nr][nc].add(new Info(move.dir, move.order));
                }
                //다 옮기고
                map[r][c] = temp; //밑에만 남기고 나머지 삭제
                return;
            }
            temp.add(new Info(cur.dir, cur.order));
        }
    }

    public static void red(int r,int c,int nr, int nc, int turn){
//        Stack<Integer> tempst = new Stack<>();
//        for(int dir: map[r][c]){
//            tempst.add(dir);
//        }
//        for(int dir: tempst){
//            map[nr][nc].add(dir);
//        }
//        map[r][c] = new LinkedList<>();

        List<Info> temp = new ArrayList<>(); //밑에 남겨놓을 말 저장
        for (int i = 0; i < map[r][c].size(); i++) {
            Info cur = map[r][c].get(i); //저장
            if(cur.order == turn){ //옮길말 부터
                for (int j = map[r][c].size()-1; j >= i; j--) { //반복하면서 옮기기 -> reverse
                    Info move = map[r][c].get(j);
                    map[nr][nc].add(new Info(move.dir, move.order));
                }
                //다 옮기고
                map[r][c] = temp; //밑에만 남기고 나머지 삭제
                return;
            }
            temp.add(new Info(cur.dir, cur.order));
        }
    }

    public static void blue(int r,int c, int nr, int nc, int beforedir, int turn){
        int curdir = -1;
        if(beforedir % 2 == 0){
            curdir = beforedir + 1;
        }else {
            curdir = beforedir - 1;
        }
        List<Info> temp = new ArrayList<>(); //밑에 남겨놓을 말 저장
        for (int i = 0; i < map[r][c].size(); i++) {
            Info cur = map[r][c].get(i); //저장
            if (cur.order == turn) {
                cur.dir = curdir; //현재 방향 바꿔주기

                for (int j = i; j < map[r][c].size(); j++) { //반복하면서 옮기기
                    Info move = map[r][c].get(j);
                    map[nr][nc].add(new Info(move.dir, move.order));
                }
                //다 옮기고
                map[r][c] = temp; //밑에만 남기고 나머지 삭제
                return;
            }
            temp.add(new Info(cur.dir, cur.order));
        }
    }

    public static boolean endcheck(int r, int c){
        if(map[r][c].size() >= 4){
            return true;
        }else{
            return false;
        }
    }

    public static boolean start(){
        boolean flag = false;
        for (int i = 0; i < order.length; i++) {
            Info turn = order[i]; //순서
            Info curinfo = turn;
            int r = -1;
            int c = -1;
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    for (int l = 0; l < map[j][k].size(); l++) {
                        if(map[j][k].get(l).order == curinfo.order){
                            r = j;
                            c = k;
                            break;
                        }
                    }
                }
            }
            for (int j = 0; j < map[r][c].size(); j++) {
                if(map[r][c].get(j).order == i){
                    curinfo = map[r][c].get(j);
                    break;
                }
            }
//            int curover = map[turn.r][turn.c].size()-1;
            int nr = r+dr[curinfo.dir];
            int nc = c+dc[curinfo.dir];
            if(nr>=0&&nr<N&&nc>=0&&nc<N){ //경계조건
                if(colormap[nr][nc] == 0){ //흰색
                    white(r, c, nr, nc, i);
                }else if(colormap[nr][nc] == 1){ //빨간색
                    red(r,c,nr,nc,i);
                }else if(colormap[nr][nc] == 2){ //파란색
                    blue(r,c,nr,nc,curinfo.dir,i);
                }
            }else{
                nr = r+dr[curinfo.dir]*-1;
                nc = c+dc[curinfo.dir]*-1;
                blue(r,c,nr,nc,curinfo.dir,i);
            }
            if(endcheck(nr,nc)){ //종료조건 확인
                flag = true;
                break;
            }
        }
        return flag;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //체스판 크기
        K = Integer.parseInt(st.nextToken()); //말의 개수
        //0-흰, 1-빨, 2-파
        map = new ArrayList[N][N];
        colormap = new int[N][N];
        order = new Info[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                colormap[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int dir = Integer.parseInt(st.nextToken())-1;
            Info temp = new Info(dir,i);
            map[r][c].add(temp);
            order[i] = temp;
        }
        int count = 0;
        for (int i = 1; i <= 1001 ; i++) {
            count += 1;
            if(start()){
                break;
            }
        }
        if(count > 1000){
            System.out.println(-1);
        }else{
            System.out.println(count);
        }
    }

    public static class Info {
        int dir, order;

        public Info(int dir, int order) {
            this.dir = dir;
            this.order = order;
        }
    }

    public static class Pos{
        int r,c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
