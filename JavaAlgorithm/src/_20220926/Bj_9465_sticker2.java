package _20220926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_9465_sticker2 {
    static int n, col;
    static int[][] map;
    static int[][] data;
    static int result;

    public static void check(){
        int row = 0;
        result = 0;
        for (int i = 2; i < col; i++) {
            data[0][i] = Math.max(data[1][i - 2], data[1][i - 1]) + map[0][i];
            data[1][i] = Math.max(data[0][i - 2], data[0][i - 1]) + map[1][i];

        }
        result = Math.max(data[0][col - 1], data[1][col - 1]);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            col = Integer.parseInt(br.readLine());
            map = new int[2][100001];
            for (int j = 0; j < 2; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < col; k++) {
                    map[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            data = new int[2][100001];

            data[0][0] = map[0][0];
            data[1][0] = map[1][0];
            data[0][1] = data[1][0] + map[0][1];
            data[1][1] = data[0][0] + map[1][1];
            check();

            sb.append(result+"\n");
        }
        System.out.println(sb);
    }
}
