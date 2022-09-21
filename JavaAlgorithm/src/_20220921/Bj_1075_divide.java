package _20220921;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_1075_divide {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int F = Integer.parseInt(br.readLine());

        N = N/100;
        N = N*100;
        int result = -1;
        for (int i = N; i < N+100; i++) {
            if(i%F == 0){
                result = i-N;
                break;
            }
        }
        if(result < 10){
            System.out.printf("0%d\n",result);
        }else{
            System.out.println(result);
        }
    }
}
