package day008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_1010_bridge {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            //29C13 -> 29P13/13! -> 13! 필요,

            /* 0.5초 시간초과 발생
            Long upper = 1L;
            int n = M;
            for (int j = N; j >0; j--) {
                upper *= n;
                n-=1;
            }

            Long under = 1L;
            for (int j = N; j > 0; j--) {
                under *= j;
            }
            Long result = upper/under;
            System.out.println(result);
            */
        }
    }
}
