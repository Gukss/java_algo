package _20220920;

import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_9184_recur {
    static int[][][] data;

    public static int dp(int a, int b, int c){
        if(a<=0||b<=0||c<=0) {
            return 1;
        }
        if(a>20||b>20||c>20) {
            return dp(20, 20, 20);
        }
        if(data[a][b][c] != 0){
            return data[a][b][c];
        }else if(a<b&&b<c){
            return data[a][b][c] = dp(a-1,b,c)+dp(a-1,b-1,c)+dp(a-1,b,c-1)-dp(a-1,b-1,c-1);
        }else{
            return data[a][b][c] = dp(a-1, b, c) + dp(a-1, b-1, c) + dp(a-1, b, c-1) - dp(a-1, b-1, c-1);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        String temp = br.readLine();
        data = new int[21][21][21];
        while(temp != ""){
            st = new StringTokenizer(temp);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(a==-1&&b==-1&&c==-1){
                break;
            }
            sb.append("w("+a+", "+b+", "+c+") = "+dp(a,b,c)+"\n");
            temp = br.readLine();
        }
        System.out.println(sb);
    }
}

