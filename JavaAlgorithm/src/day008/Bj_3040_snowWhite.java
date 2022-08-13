package day008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Bj_3040_snowWhite {
    static int[] sel;
    static int[] input;
    public static void comb(int idx, int start){
        if(idx == sel.length){
            int sum = 0;
            for (int i = 0; i < 7; i++) {
                sum += sel[i];
            }
            if(sum == 100){
                for (int i = 0; i < 7; i++) {
                    System.out.println(sel[i]);
                }
            }
            return;
        }
        for (int i = start; i < 9; i++) {
            sel[idx] = input[i];
            comb(idx+1, i+1);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = new int[9];
        for (int i = 0; i < 9; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }
        sel = new int[7];
        comb(0, 0);

    }
}
