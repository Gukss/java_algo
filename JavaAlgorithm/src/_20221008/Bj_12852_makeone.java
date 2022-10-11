package _20221008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj_12852_makeone {
    static int N, total, totallen;
    static int[] data;
    static String result;

    public static int makeone(int n, String str, int sum){
        if(sum > totallen){
            return data[n];
        }
        if(n==1){
            if(totallen > sum){
                totallen = sum;
                str = str+Integer.toString(n)+" ";
                result = str;
            }
            return data[n]=1;
        }
        int r2 = Integer.MAX_VALUE;
        int r3 = Integer.MAX_VALUE;
        int r1 = Integer.MAX_VALUE;
        if(n%3==0){
            r1 = makeone(n/3, str+Integer.toString(n)+" ", sum+1)+1;
        }
        if(n%2==0){
            r2 = makeone(n/2, str+Integer.toString(n)+" ", sum+1)+1;
        }
        r3 = makeone(n-1, str+Integer.toString(n)+" ", sum+1)+1;

        return data[n] = Math.min(r1, Math.min(r2, r3));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        data = new int[1000001];

        //f(n) = f(n/3)+1
        //f(n) = f(n/2)+1
        //f(n) = f(n-1)+1
        total = Integer.MIN_VALUE;
        totallen = Integer.MAX_VALUE;
        makeone(N, "", 0);
        total = data[N]-1;
        StringBuilder sb = new StringBuilder();
        sb.append(total+"\n"+result);
        System.out.println(sb);
    }
}
