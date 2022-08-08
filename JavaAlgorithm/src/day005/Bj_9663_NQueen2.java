package day005;

import java.util.Scanner;

public class Bj_9663_NQueen2 {
    static int[] row;
    static int N;
    static int result;

    public static boolean check(int r){
        for (int j = 0; j < r; j++) {
            //r행 위의 행 j에서 같은 값이 있으면 같은 열에 queen이 있다. 대각선 방향 (2,4) (1,5) 행, 열끼리 빼면 절대값이 같다.
            if(row[r] == row[j] || Math.abs(row[r] - row[j]) == Math.abs(r-j)){
                return false;
            }
        }
        return true;
    }
    public static void nq(int r){
        if(r == N){ //완료 조건
            result += 1;
            return;
        }
        for (int i = 0; i < N; i++) {
            row[r] = i; //r행, i열에 queen 위치
            if(check(r)){ //r행 위로 queen위치 확인, queen이 위치하지 못하면 다음 열 확인
                nq(r+1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        result = 0;
        row = new int[N];
        nq(0);

        System.out.println(result);
    }
}
