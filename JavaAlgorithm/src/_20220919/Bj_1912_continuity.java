package _20220919;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_1912_continuity {
    static int n;
    static int[] input;
    static int[] data;

    public static int cont(int idx){
        if(idx==0){
            return data[idx] = input[idx]; //idx가 0이면 그냥 인자 선택
        }
        return data[idx] = Math.max(cont(idx-1)+input[idx], input[idx]); //이때까지 idx-1까지 선택해온 최대값+idx값과 현재 idx값 중 큰 값을 선택
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        input = new int[n];
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        data = new int[n];
        //cont(i) = cont(i-1) + input[i]
        //data[i] = max(cont(i-1)+input[i], input[i]);
        cont(n-1);
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, data[i]);
        }
        System.out.println(result);
    }
}
