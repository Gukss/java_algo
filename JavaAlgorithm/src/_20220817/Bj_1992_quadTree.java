package _20220817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_1992_quadTree {
    static int[][] map;
    static StringBuilder sb = new StringBuilder();

    public static void recr(int N, int r, int c) {
        int temp = map[r][c]; //왼쪽 위 값
        for (int i = r; i < N+r; i++) {
            for (int j = c; j < N+c; j++) {
                if(map[i][j] != temp) { //왼쪽 위 값이랑 다르면 분할, 하나 들어갈 때 마다 "("추가
                    sb.append("(");
                    recr(N/2, r, c); //왼쪽 위
                    recr(N/2, r, c+N/2); //오른 위
                    recr(N/2, r+N/2, c); //왼쪽 아래
                    recr(N/2, r+N/2, c+N/2); //오른 아래
                    sb.append(")");
                    return;
                }
            }
        }
        sb.append(map[r][c]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }
        recr(N, 0, 0);
        System.out.println(sb.toString());
    }
}
