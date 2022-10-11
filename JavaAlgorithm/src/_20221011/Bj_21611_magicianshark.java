package _20221011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_21611_magicianshark {
    static int N,M;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0}; //위, 밑, 왼, 오
    static int[] dc = {0, 0, -1, 1}; //위, 밑, 왼, 오
    static int shark_pos;
    static Queue<Integer> qu;
    static int ex_1, ex_2, ex_3;

    public static void crush(int dir, int s){
        int sr = shark_pos;
        int sc = shark_pos;

        for (int i = 1; i <= s; i++) {
            int nr = sr + dr[dir]*i;
            int nc = sc + dc[dir]*i; //경계검사할 필요 없다. -> 조건에 있다.
            map[nr][nc] = 0;
        }
    }

    public static void quin(){
        qu = new LinkedList<>();
        int r = (N+1)/2;
        int c = (N+1)/2;
        int step = 1;
        boolean flag = false;
        while(true){
            for (int i = 0; i < step; i++) { //왼
                if(r==1&&c==1){
                    flag = true;
                    break;
                }
                if(map[r][c] != 0){
                    qu.add(map[r][c]);
                }
                r += dr[2];
                c += dc[2];
            }
            if(flag) break;
            for (int i = 0; i < step; i++) { //밑
                if(r==1&&c==1){
                    flag = true;
                    break;
                }
                if(map[r][c] != 0){
                    qu.add(map[r][c]);
                }
                r += dr[1];
                c += dc[1];
            }
            if(flag) break;
            step += 1;

            for (int i = 0; i < step; i++) { //오
                if(r==1&&c==1){
                    flag = true;
                    break;
                }
                if(map[r][c] != 0){
                    qu.add(map[r][c]);
                }
                r += dr[3];
                c += dc[3];
            }
            if(flag) break;
            for (int i = 0; i < step; i++) { //위
                if(r==1&&c==1){
                    flag = true;
                    break;
                }
                if(map[r][c] != 0){
                    qu.add(map[r][c]);
                }
                r += dr[0];
                c += dc[0];
            }
            if(flag) break;
            step += 1;
        }
    }

    public static void move(){
        int r = (N+1)/2;
        int c = (N+1)/2;
        map = new int[N+1][N+1];
        int step = 1;
        boolean flag = false;

        while(true){
            for (int i = 0; i < step; i++) { //왼
                if(qu.isEmpty()){
                    flag = true;
                    break;
                }
                r += dr[2];
                c += dc[2];
                if(r<0||r>=N+1||c<0||c>=N+1) {
                    flag = true;
                    break;
                }
                map[r][c] = qu.poll();
            }
            if(flag) break;
            for (int i = 0; i < step; i++) { //밑
                if(qu.isEmpty()){
                    flag = true;
                    break;
                }
                r += dr[1];
                c += dc[1];
                if(r<0||r>=N+1||c<0||c>=N+1) {
                    flag = true;
                    break;
                }
                map[r][c] = qu.poll();
            }
            if(flag) break;
            step += 1;

            for (int i = 0; i < step; i++) { //오
                if(qu.isEmpty()){
                    flag = true;
                    break;
                }
                r += dr[3];
                c += dc[3];
                if(r<0||r>=N+1||c<0||c>=N+1) {
                    flag = true;
                    break;
                }
                map[r][c] = qu.poll();
            }
            if(flag) break;
            for (int i = 0; i < step; i++) { //위
                if(qu.isEmpty()){
                    flag = true;
                    break;
                }
                r += dr[0];
                c += dc[0];
                if(r<0||r>=N+1||c<0||c>=N+1) {
                    flag = true;
                    break;
                }
                map[r][c] = qu.poll();
            }
            if(flag) break;
            step += 1;
        }
    }

    public static void explosion(){
        quin();
        while(true){
            boolean stop = true;
            Deque<Integer> temp = new LinkedList<>();
            int beforenum = 0;
            int count = 1;
            for (Integer num : qu) {
                if(beforenum == num){
                    count += 1;
                }else{
                    if(count >= 4){
                        for (int i = 0; i < count; i++) {
                            if(beforenum == 1){
                                ex_1 += 1;
                            }else if(beforenum == 2){
                                ex_2 += 1;
                            }else if(beforenum == 3){
                                ex_3 += 1;
                            }
                            temp.pollLast();
                        }
                        stop = false;
                    }
                    count = 1;
                }
                temp.add(num);
                beforenum = num;
            }
            qu = new LinkedList<>();
            for (Integer num : temp) {
                qu.add(num);
            }
            if(stop){
                move();
                break;
            }
        }
    }

    public static void transform(){
        quin();
        Deque<Integer> temp = new LinkedList<>();
        int beforenum = qu.peek();
        int count = 0;
        for (Integer num : qu) {
            if(beforenum == num){
                count += 1;
            }else{
                temp.add(count);
                temp.add(beforenum);
                count = 1;
            }
            beforenum = num;
        }
        temp.add(count);
        temp.add(beforenum);
        qu = new LinkedList<>();
        for (Integer num : temp) {
            qu.add(num);
        }
        move();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1]; //인덱스 1부터 시작 => 상어 위치: ((N+1)/2, (N+1)/2)

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        shark_pos = (N+1)/2;
        ex_1 = 0;
        ex_2 = 0;
        ex_3 = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1; //1~4 => 0~3으로 변경
            int s = Integer.parseInt(st.nextToken()); //거리

            crush(d,s);
            quin();
            move();
            explosion();
            transform();
        }
        System.out.println(ex_1*1 + ex_2*2 + ex_3*3);
    }
}
