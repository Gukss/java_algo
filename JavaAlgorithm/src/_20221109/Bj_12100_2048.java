package _20221109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_12100_2048 {
    static int N;
    static int[][] map;
    static int[] sel;
    static int[] dr = {-1, 0, 1, 0}; //위, 오, 밑, 왼
    static int[] dc = {0, 1, 0, -1}; //위, 오, 밑, 왼
    static int max;
    static Queue<Integer> qu;

    public static int[][] cpmap(){
        int[][] cpmap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cpmap[i][j] = map[i][j];
            }
        }
        return cpmap;
    }
//
//    public static void pull(int[][] cpmap, int dir, int rowcol){
//        int r = -1;
//        int c = -1;
//        int j = rowcol;
////        for (int j = 0; j < N; j++) { //행 or 열 반복
//        if(dir==0){ //밑으로 이동하기
//            r = N-1;
//            for (int k = r; k > 0; k--) { //올라가기
//                if(cpmap[k][j] == 0 && cpmap[k-1][j] != 0){ //0이 있고, 다음 값이 0이 아니면 당기기, 제일 마지막 값은 확인 안해도 된다.
////                        for (int i = k; i > 0; i--) {
//////                            if(cpmap[i][j])
////                            int temp = cpmap[i-1][j]; //swap
////                            cpmap[i-1][j] = cpmap[i][j];
////                            cpmap[i][j] = temp;
////                        }
//                    int temp = cpmap[k-1][j]; //swap
//                    cpmap[k-1][j] = cpmap[k][j];
//                    cpmap[k][j] = temp;
//                }
//            }
//        }else if(dir == 2){ //위로 이동하기
//            r = 0;
//            for (int k = r; k < N-1; k++) { //내려가기
//                if(cpmap[k][j] == 0 && cpmap[k+1][j] != 0){ //같으면 합치기
////                        for (int i = k; i > 0; i--) {
////                            int temp = cpmap[i+1][j]; //swap
////                            cpmap[i+1][j] = cpmap[i][j];
////                            cpmap[i][j] = temp;
////                        }
//                    int temp = cpmap[k+1][j]; //swap
//                    cpmap[k+1][j] = cpmap[k][j];
//                    cpmap[k][j] = temp;
//                }
//            }
//        }
//        if(dir==1){ //왼쪽으로 이동하기
//            c = 0;
//            for (int k = c; k < N-1; k++) { //오른쪽으로 이동
//                if(cpmap[j][k] == 0 && cpmap[j][k+1] != 0){ //같으면 합치기
////                        for (int i = k; i >= 0; i--) {
////                            int temp = cpmap[j][i+1]; //swap
////                            cpmap[j][i+1] = cpmap[j][i];
////                            cpmap[j][i] = temp;
////                        }
//
//                    int temp = cpmap[j][k+1]; //swap
//                    cpmap[j][k+1] = cpmap[j][k];
//                    cpmap[j][k] = temp;
//
//                }
//            }
//        }else if(dir == 3){ //오른쪽으로 이동하기
//            c = N-1;
//            for (int k = c; k > 0; k--) { //왼쪽으로 이동
//                if(cpmap[j][k] == 0 && cpmap[j][k-1] != 0){ //0이 있고, 다음 값이 0이 아니면 당기기, 제일 마지막 값은 확인 안해도 된다.
////                        for (int i = k; i >= 0; i--) {
////                            int temp = cpmap[j][i-1]; //swap
////                            cpmap[j][i-1] = cpmap[j][i];
////                            cpmap[j][i] = temp;
////                        }
//                    int temp = cpmap[j][k-1]; //swap
//                    cpmap[j][k-1] = cpmap[j][k];
//                    cpmap[j][k] = temp;
//
//                }
//            }
//        }
////        }
//    }
//
//    public static void move(int[][] cpmap){
//
//        for (int i = 0; i < sel.length; i++) {
//            int dir = sel[i];
//            int r = -1;
//            int c = -1;
//
//            for (int j = 0; j < N; j++) { //행 or 열 반복
//                if(dir==0){ //밑으로 이동하기
//                    r = N-1;
//                    for (int k = r; k > 0; k--) { //올라가기
//                        if(cpmap[k][j] == cpmap[k-1][j]){ //같으면 합치기
//                            cpmap[k][j] *= 2;
//                            max = Math.max(max, cpmap[k][j]);
//                            cpmap[k-1][j] = 0;
//                        }
//                        pull(cpmap, dir, j);
//                    }
//                    if(cpmap[r][j] == cpmap[r-1][j]){ //다 옮기고 같으면 => 마지막으로 옮겨주기
//                        cpmap[r][j] *= 2;
//                        max = Math.max(max, cpmap[r][j]);
//                        cpmap[r-1][j] = 0;
//                    }
//                }else if(dir == 2){ //위로 이동하기
//                    r = 0;
//                    for (int k = r; k < N-1; k++) { //내려가기
//                        if(cpmap[k][j] == cpmap[k+1][j]){ //같으면 합치기
//                            cpmap[k][j] *= 2;
//                            max = Math.max(max, cpmap[k][j]);
//                            cpmap[k+1][j] = 0;
//                        }
//                        pull(cpmap, dir,j);
//                    }
//                    if(cpmap[r][j] == cpmap[r+1][j]){ //다 옮기고 같으면 => 마지막으로 옮겨주기
//                        cpmap[r][j] *= 2;
//                        max = Math.max(max, cpmap[r][j]);
//                        cpmap[r+1][j] = 0;
//                    }
//                }
//                if(dir==1){ //왼쪽으로 이동하기
//                    c = 0;
//                    for (int k = c; k < N-1; k++) { //오른쪽으로 이동
//                        if(cpmap[j][k] == cpmap[j][k+1]){ //같으면 합치기
//                            cpmap[j][k] *= 2;
//                            max = Math.max(max, cpmap[j][k]);
//                            cpmap[j][k+1] = 0;
//                        }
//                        pull(cpmap, dir,j);
//                    }
//                    if(cpmap[j][c] == cpmap[j][c+1]){ //같으면 합치기
//                        cpmap[j][c] *= 2;
//                        max = Math.max(max, cpmap[j][c]);
//                        cpmap[j][c+1] = 0;
//                    }
//                }else if(dir == 3){ //오른쪽으로 이동하기
//                    c = N-1;
//                    for (int k = c; k > 0; k--) { //왼쪽으로 이동
//                        if(cpmap[j][k] == cpmap[j][k-1]){ //같으면 합치기
//                            cpmap[j][k] *= 2;
//                            max = Math.max(max, cpmap[j][k]);
//                            cpmap[j][k-1] = 0;
//                        }
//                        pull(cpmap, dir,j);
//                    }
//                    if(cpmap[j][c] == cpmap[j][c-1]){ //같으면 합치기
//                        cpmap[j][c] *= 2;
//                        max = Math.max(max, cpmap[j][c]);
//                        cpmap[j][c-1] = 0;
//                    }
//                }
//            }
//        }
//    }

    public static void move(int[][] cpmap){
        for (int i = 0; i < sel.length; i++) {
            int dir = sel[i];
            int r = -1;
            int c = -1;

            for (int j = 0; j < N; j++) { //행 or 열 반복

                qu = new ArrayDeque<>();

                if(dir==0){ //밑으로 이동하기
                    r = N-1;
                    for (int k = r; k >= 0; k--) { //올라가기
                        if(map[k][j] != 0){
                            qu.add(map[k][j]);
                        }
                    }
                    while(!qu.isEmpty()){
                        int num1 = qu.poll();

                    }
//                    if(!qu.isEmpty()){
//                        for (int k = r; k >= 0; k--) {
//                            int num1 = qu.poll();
//                            if(!qu.isEmpty()){
//                                int num2 = qu.peek();
//                                if(num1==num2){
//                                    if(num1==0){
//                                        qu.add(0);
//                                    }else{
//                                        int temp = qu.poll();
//                                        map[k][j] = num1*2;
//    //                                    k-=1;
//                                    }
//                                }else{
//                                    map[k][j] = num1;
//                                }
//                            }else{
////                                map[k][j] = 0;
//                                qu.add(0);
//                                }
//                            }
//                        }

                }else if(dir == 2){ //위로 이동하기
                    r = 0;
                    for (int k = r; k <= N-1; k++) { //내려가기
                        if(map[k][j] != 0){
                            qu.add(map[k][j]);
                        }
                    }
                    if(!qu.isEmpty()){
                        for (int k = r; k <= N-1; k++) {
                            int num1 = qu.poll();
                            if(!qu.isEmpty()){
                                int num2 = qu.peek();
                                if(num1==num2){
                                    if(num1==0){
                                        qu.add(0);
                                    }else{
                                        int temp = qu.poll();
                                        map[k][j] = num1*2;
    //                                    k+=1;
                                    }
                                }else{
                                    map[k][j] = num1;
                                }
                            }else{
//                                map[k][j] = 0;
                                qu.add(0);
                            }
                        }
                    }
                }
                if(dir==1){ //왼쪽으로 이동하기
                    c = 0;
                    for (int k = c; k <= N-1; k++) { //오른쪽으로 이동
                        if(map[j][k] != 0){
                            qu.add(map[j][k]);
                        }
                    }
                    if(!qu.isEmpty()){
                        for (int k = c; k <= N-1; k++) {
                            int num1 = qu.poll();
                            if(!qu.isEmpty()){
                                int num2 = qu.peek();
                                if(num1==num2){
                                    if(num1==0){
                                        qu.add(0);
                                    }else{
                                        int temp = qu.poll();
                                        map[j][k] = num1*2;
    //                                    k+=1;
                                    }
                                }else{
                                    map[j][k] = num1;
                                }
                            }else{
//                                map[j][k] = 0;
                                qu.add(0);
                            }
                        }
                    }
                }else if(dir == 3){ //오른쪽으로 이동하기
                    c = N-1;
                    for (int k = c; k >= 0; k--) { //왼쪽으로 이동
                        if(map[j][k] != 0){
                            qu.add(map[j][k]);
                        }
                    }
                    if(!qu.isEmpty()){
                        for (int k = c; k >= 0; k--) {
                            int num1 = qu.poll();
                            if(!qu.isEmpty()){
                                int num2 = qu.peek();
                                if(num1==num2){
                                    if(num1==0){
                                        qu.add(0);
                                    }else{
                                        int temp = qu.poll();
                                        map[j][k] = num1*2;
    //                                    k-=1;
                                    }
                                }else{
                                    map[j][k] = num1;
                                }
                            }else{
//                                map[j][k] = 0;
                                qu.add(0);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void check(int[][] cpmap){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(cpmap[i][j], max);
            }
        }
    }

    public static void perm(int idx, int turn){
        if(idx == turn){
//            System.out.println(Arrays.toString(sel));
            int[][] cpmap = cpmap();
            move(cpmap);
            check(cpmap);
            return;
        }
        for (int i = 0; i < 4; i++) { //상하좌우 4개
            //중복해서 가능
            sel[idx] = i;
            perm(idx+1, turn);
        }
    }

    public static void start(){
        for (int i = 1; i <= 5; i++) { //최대 5번 이동
            sel = new int[i];
            perm(0,i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
            }
        }
//        max = 2; //최소값이 2다.
        //최대 5번 이동, 가장 큰 블록의 값 => 1!~5!
        //방향키 누르는 함수, 값이 2개 같은 때 당기는 함수
        start();
        System.out.println(max);
    }
}
//2시간
/*
4
0 64 2 1024
2 512 8 0
0 32 512 256
64 64 8 2
ans: 2048

5
2 0 0 0 0
2 0 0 0 0
4 0 0 0 0
2 0 0 0 0
2 0 0 0 0
=>
4 0 0 0 0
4 0 0 0 0
4 0 0 0 0
0 0 0 0 0
0 0 0 0 0

3
2 2 2
2 2 2
2 2 2
ans: 8

5
2 2 4 8 16
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
2 2 4 8 16
ans: 64
 */