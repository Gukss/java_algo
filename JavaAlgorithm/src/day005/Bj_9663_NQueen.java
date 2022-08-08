package day005;

import java.util.Scanner;

public class Bj_9663_NQueen {
    static boolean[][] board;
    static int N;
    static int[] dr = {-1, -1, -1};
    static int[] dc = {-1, 0, 1};
    static int result;
    public static void nq(int row, int col){
//        if(row>N-1){
//            result+=1;
//            board = new boolean[N][N];
//            return;
//        }
        board[row][col] = true;

        for (int i = 1; i <= row; i++) {
            for (int j = 0; j < 3; j++) {
                int nr = row + dr[j] * i;
                int nc = col + dc[j] * i;
                if(nr<0 || nc < 0 || nc >= N){
                    continue;
                }
                if(board[nr][nc] == true){
                    board[row][col] = false;
                    return;
                }
            }
        }
        for (int j = 0; j < N; j++) {
            if(row+1 < N){
                nq(row+1, j);
                board[row+1][j] = false;
            }
            else{
                result+=1;
                return;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        result = 0;
        board = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            nq(0, i);
            board = new boolean[N][N];
        }
        System.out.println();
    }
}
