package _20230206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_1288 {
    public static int T, N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(st.nextToken());
        for(int test_case = 1;test_case<=T;test_case++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int mask = (1 << 10) - 1;
            int confirm = 0;
            int count=1;
            while(true){
                char[] arr = String.valueOf(N*count).toCharArray();
                for(char x: arr){
                    int num = x - '0';
                    confirm = confirm | (1 << num);
                }
                if(confirm == mask){
                    break;
                }
                count += 1;
            }
            sb.append("#"+test_case+" "+N*count+"\n");
        }
        System.out.println(sb);
    }
}
