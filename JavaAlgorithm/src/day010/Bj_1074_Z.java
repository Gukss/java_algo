package day010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class Bj_1074_Z {

    static int[][] mapN = {{0,1}, {2,3}};
    static int N, r, c;
    public static int recr(int i, int j, int N){
        if(N==1){
            return mapN[i][j];
        }
        if(i < (int)Math.pow(2, N-1) && j< (int)Math.pow(2,N-1)){
            return recr(i,j,N-1);
        } else if(i < (int)Math.pow(2, N-1)){ //오
            return recr(i, j-((int)Math.pow(2, N-1)), N-1) + (int)Math.pow(4, N-1);
        } else if(j< (int)Math.pow(2,N-1)){ //밑
            return recr(i-((int)Math.pow(2, N-1)), j, N-1) + (int)Math.pow(4, N-1)*2;
        } else {
            return recr(i-((int)Math.pow(2, N-1)), j-((int)Math.pow(2, N-1)), N-1) + (int)Math.pow(4, N-1)*3;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        //N==1인 배열을 만든다.
        //N==1배열을 가지고 N-1배열을 반복문으로 구한다.
        //r,c를 가지고 사분면을 구해 N-1배열에 적절한 수를 더해 N배열의 숫자를 구한다.

        System.out.println(recr(r,c,N));
    }
}
