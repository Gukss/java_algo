package _20220821;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_2798_blackJack {
    static int[] num;
    static int N,M;
    static int[] sel;
    static int result;
    public static void comb(int idx, int start){
        if(idx == 3){
            int sum = 0;
            for (int i = 0; i < 3; i++) {
                sum += sel[i];
            }
            if(sum <= M && result < sum){
                result = sum;
            }
            return;
        }
        for (int i = start; i < N; i++) {
            sel[idx] = num[i];
            comb(idx+1, i+1);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        num = new int[N];
        sel = new int[3];
        result = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        comb(0, 0);
        System.out.println(result);
    }
}
