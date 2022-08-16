package _20220816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_2839_sugar {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int result = 0;
        while(N>=0){
            if(N % 5 == 0){ //5로 나눠지면 5키로 봉지를 배달하는게 적다.
                result += N/5;
                break;
            }else{ //5로 나눠지지 않으면 3키로 봉지를 1개씩 늘린다.
                N -= 3;
                result += 1;
            }
        }
        if(N<0){
            System.out.println(-1);
        }else{
            System.out.println(result);
        }
    }
}
