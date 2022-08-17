package _20220816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_2630_colorPaper {

    static int[][] map;
    static int[] result; //0->0, 1->1
    static void recr(int n, int r, int c){
        if(n==1){
            result[map[r][c]] += 1;
            return;
        }
        int temp = map[r][c];
        for (int i = r, end1 = n+r; i < end1; i++) {
            for (int j = c, end2 = n+c; j < end2; j++) {
                if(map[i][j] != temp){
                    recr(n/2, r, c); //인자값을 가지고 넘어가면 된다.
                    recr(n/2, r+n/2, c);
                    recr(n/2, r, c+n/2);
                    recr(n/2, r+n/2, c+n/2);
                    return;
                }
            }
        }
        result[temp] += 1;
        return;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        result = new int[2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recr(N, 0, 0);
        System.out.println(result[0]);
        System.out.println(result[1]);

    }
}
