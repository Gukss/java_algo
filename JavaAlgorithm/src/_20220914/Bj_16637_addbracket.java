package _20220914;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_16637_addbracket {
    static int N;
    static char[] op;
    static int[] num;
    static long ans;

    public static long calc(long n1, long n2, char op){
        if(op == '+'){
            return n1+n2;
        }else if(op == '-'){
            return n1-n2;
        }else if(op == '*'){
            return n1*n2;
        }
        return -1;
    }

    public static void dfs(long result, int idx){
        if(idx >= op.length){
            ans = Math.max(ans, result);
            return;
        }

        long res1 = calc(result, num[idx+1], op[idx]);
        dfs(res1, idx+1);

        if(idx +1 < op.length){
            long res2 = calc(num[idx+1], num[idx+2], op[idx+1]);
            dfs(calc(result, res2, op[idx]), idx+2);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        op = new char[N/2];
        num = new int[N/2+1];
        ans = Long.MIN_VALUE;

        String temp = br.readLine();
        int opcnt =0;
        int numcnt =0;
        for (int i = 0; i < N; i++) {
            if(i%2!=0){
                op[opcnt++] = temp.charAt(i);
            }else{
                num[numcnt++] = temp.charAt(i) - '0';
            }
        }

        dfs(num[0], 0);
        System.out.println(ans);
    }
}
//못품