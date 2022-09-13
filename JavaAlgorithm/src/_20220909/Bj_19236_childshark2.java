package _20220909;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Bj_19236_childshark2 {
    static int[][] map;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1}; //위, 왼위, 왼, 왼밑, 밑, 오른밑, 오른, 오른위
    static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1}; //위, 왼위, 왼, 왼밑, 밑, 오른밑, 오른, 오른위
    static Fish[] dirlist;
    static int result;

    public static int[][] cpmap(int[][] map){
        int[][] cpmap = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int temp = map[i][j];
                cpmap[i][j] = temp;
            }
        }
        return cpmap;
    }

    public static Fish[] cpdirlist(Fish[] dirlist){
        Fish[] cpdirlist = new Fish[dirlist.length];
        for (int i = 0; i < dirlist.length; i++) {
            cpdirlist[i] = new Fish(0, 0, 0, 0);
            cpdirlist[i].num = dirlist[i].num;
            cpdirlist[i].r = dirlist[i].r;
            cpdirlist[i].c = dirlist[i].c;
            cpdirlist[i].dir = dirlist[i].dir;
        }
        return cpdirlist;
    }


//    public static void moveshark(Fish[] dirlist, int[][] map, int sum){
//        int d = dirlist[0].dir;
//        int sr = dirlist[0].r;
//        int sc = dirlist[0].c;
//        int nr = sr + dr[d];
//        int nc = sc + dc[d];
//
//        while (nr>=0&&nc>=0&&nr<4&&nc<4) { //경계 안에 있을 때 까지 반복
//            int[][] cpmap = cpmap(map);
//            Fish[] cpdirlist = cpdirlist(dirlist);
//            int tempnum = 0;
//            int tempr = cpdirlist[0].r;
//            int tempc = cpdirlist[0].c;
//
//            if(cpmap[nr][nc] != -1){
//                //상어 옮기기
//                cpdirlist[0].dir = cpdirlist[cpmap[nr][nc]].dir; //상어의 방향을 (nr,nc)물고기의 방향으로
//                tempnum = cpmap[nr][nc];
//                cpdirlist[cpmap[nr][nc]].num = -1; //(nr,nc)의 물고기 먹힘, num이 -1이면 먹힌 물고기다.
//                cpmap[sr][sc] = -1;
//                cpmap[nr][nc] = 0; //0번은 상어
//                cpdirlist[0].r = nr;
//                cpdirlist[0].c = nc;
//
//                start(cpdirlist, cpmap, sum+tempnum);
////                sum -= tempnum;
//                cpdirlist[cpmap[nr][nc]].num = tempnum;
////                cpdirlist[0].r = tempr;
////                cpdirlist[0].c = tempc;
//            }
//            nr = nr + dr[d];
//            nc = nc + dc[d];
//        }
//        result = Math.max(result, sum);
//        return;
//    }
    public static void moveshark(Fish[] dirlist, int[][] map, int sum){
        int d = dirlist[0].dir;
        int sr = dirlist[0].r;
        int sc = dirlist[0].c;
        int nr = sr + dr[d];
        int nc = sc + dc[d];

        while (nr>=0&&nc>=0&&nr<4&&nc<4) { //경계 안에 있을 때 까지 반복
            int[][] cpmap = cpmap(map);
            Fish[] cpdirlist = cpdirlist(dirlist);
            int tempnum = 0;
            int tempdir = cpdirlist[0].dir;
            int tempr = cpdirlist[0].r;
            int tempc = cpdirlist[0].c;

            if(cpmap[nr][nc] != -1){
                //상어 옮기기
                cpdirlist[0].dir = cpdirlist[cpmap[nr][nc]].dir; //상어의 방향을 (nr,nc)물고기의 방향으로
                tempnum = cpmap[nr][nc];
                cpdirlist[cpmap[nr][nc]].num = -1; //(nr,nc)의 물고기 먹힘, num이 -1이면 먹힌 물고기다.->마지막에바꾼거
                cpmap[sr][sc] = -1;
                cpmap[nr][nc] = 0; //0번은 상어
                cpdirlist[0].r = nr;
                cpdirlist[0].c = nc;

                start(cpdirlist, cpmap, sum+tempnum);
                cpdirlist[cpmap[nr][nc]].num = tempnum;
                cpmap[sr][sc] = 0;
                cpmap[nr][nc] = tempnum;
                cpdirlist[0].dir = tempdir;
                cpdirlist[0].r = tempr;
                cpdirlist[0].c = tempc;
            }
            nr = nr + dr[d];
            nc = nc + dc[d];
        }
        result = Math.max(result, sum);
        return;
    }

    public static void start(Fish[] dirlist, int[][] map, int sum){
        int[][] cpmap = cpmap(map);
        Fish[] cpdirlist = cpdirlist(dirlist);

        for (int i = 1; i < dirlist.length; i++) {
            Fish cur = cpdirlist[i];
            int sr = cur.r;
            int sc = cur.c;
            if(cpmap[sr][sc] == 0 || cpmap[sr][sc] == -1 || cpdirlist[i].num==-1) continue; //상어 자리면 skip
            for (int j = 0; j < 8; j++) {
                int d = (cur.dir + j ) % 8;
                int nr = sr + dr[d];
                int nc = sc + dc[d];

                if(nr>=0&&nc>=0&&nr<4&&nc<4 && cpmap[nr][nc] != 0) { //경계, 상어가 아닌곳, 물고기 없는곳은 -1로 하기
                    //바뀐방향으로 방향도 바꿔줘야 한다.
                    cpdirlist[i].dir = d;

                    if(cpmap[nr][nc] == -1){ //빈칸인 경우도 이동가능
                        cpmap[sr][sc] = -1;
                        cpdirlist[i].r = nr;
                        cpdirlist[i].c = nc;
                        cpmap[nr][nc] = i;
                    }else{
                        int changeFishNum = cpdirlist[cpmap[nr][nc]].num;
                        int tempr = cpdirlist[changeFishNum].r;
                        int tempc = cpdirlist[changeFishNum].c;

                        cpdirlist[changeFishNum].r = cpdirlist[i].r;
                        cpdirlist[changeFishNum].c = cpdirlist[i].c;
                        cpmap[cpdirlist[changeFishNum].r][cpdirlist[changeFishNum].c] = changeFishNum;

                        cpdirlist[i].r = tempr;
                        cpdirlist[i].c = tempc;
                        cpmap[nr][nc] = i; //map에서 num 바꾸기
                    }

//                    System.out.println(i + " "+ d+ " "+ cpdirlist[0].dir + " " +cpdirlist[i].dir);
//                    for (int k = 0; k < 4; k++) {
//                        for (int l = 0; l < 4; l++) {
//                            System.out.printf("%d ", cpmap[k][l]);
//                        }
//                        System.out.println();
//                    }
//                    System.out.println();
//                    System.out.println(sum);
//                    System.out.println("-----------------");
                    break;
                }
            }
        } //물고기 이동 완료
        //상어 이동
        moveshark(cpdirlist, cpmap, sum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[4][4];
        dirlist = new Fish[17]; //0번은 상어, i번 물고기의 방향 저장

        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken())-1; //델타 인덱스로 0~7로 바꿔준다.
                Fish temp = new Fish(i,j,num,dir);
                dirlist[num] = temp; //num번 물고기의 행렬,번호, 방향 저장
                map[i][j] = num; //map 안에는 물고기의 번호저장
            }
        }
        dirlist[0] = new Fish(0,0,0,-1); //상어 초기화
        dirlist[0].dir = dirlist[map[0][0]].dir; //상어의 방향을 (0,0)물고기의 방향으로
        int sum = map[0][0];
        dirlist[map[0][0]].num = -1; //(0,0)의 물고기 먹힘, num이 -1이면 먹힌 물고기다.
        map[0][0] = 0; //0번은 상어
        result = Integer.MIN_VALUE;

        int[][] cpmap = cpmap(map);
        Fish[] cpdirlist = cpdirlist(dirlist);
        start(cpdirlist, cpmap, sum);
        System.out.println(result);
    }

    public static class Fish{
        int r,c,dir,num;
        public Fish(int r, int c, int num, int dir) {
            this.r = r;
            this.c = c;
            this.num = num;
            this.dir = dir;
        }
    }
}
