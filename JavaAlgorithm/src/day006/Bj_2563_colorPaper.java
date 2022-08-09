package day006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_2563_colorPaper {
    static int[][] paper;
    static int[] arr;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        paper = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            paper[i][0] = Integer.parseInt(st.nextToken());
            paper[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] save = new int[100][100];

        for(int[] x: paper){
            for (int i = x[0]; i < x[0]+10; i++) {
                for (int j = x[1]; j < x[1]+10; j++) {
                    save[i][j] = 1;
                }
            }
        }
        int count=0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if(save[i][j] == 1) count++;
            }
        }
        System.out.println(count);
    }

}
