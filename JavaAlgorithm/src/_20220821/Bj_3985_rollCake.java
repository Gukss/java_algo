package _20220821;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_3985_rollCake {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int L = Integer.parseInt(br.readLine());
        int[] cake = new int[L+1];

        int expect = 0;
        int diff = 0;

        int N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            if(diff< e-s){
                diff = e-s;
                expect = i;
            }
            for (int j = s; j <= e; j++) {
                if(cake[j] == 0){
                    cake[j] = i;
                }
            }
        }
        int[] sum = new int[N+1];
        for (int i = 1; i <= L; i++) {
            sum[cake[i]]++;
        }
        int max = 0;
        int result = 0;
        for (int i = 1; i <= N; i++) {
            if(max < sum[i]){
                max = sum[i];
                result = i;
            }
        }
        System.out.println(expect);
        System.out.println(result);

    }
}
