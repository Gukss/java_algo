package _20221005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_5597_student {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[] v = new boolean[31];
        for (int i = 0; i < 28; i++) {
            int idx = Integer.parseInt(br.readLine());
            v[idx] = true;
        }
        for (int i = 1; i < 31; i++) {
            if(!v[i]){
                System.out.println(i);
            }
        }
    }
}
