import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
//    static int[] map;
    static long[][] dp;
    static long result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dp = new long[N + 1][3];
        dp[1][0] = dp[1][1] = dp[1][2] = 1;

        for (int i = 2; i <= N; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % 9901;
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % 9901;
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % 9901;
        }

        result = (dp[N][0] + dp[N][1] + dp[N][2]) % 9901;
//        map[0] = 2;
//        for (int i = 1; i < N; i++) {
//            map[i] += map[i-1]*(i)*2 + 2;
//        }

//        for (int i = 0; i < N; i++) {
//            for (int j = i-1; j >= 0 ; j--) {
//                if(j==i-1){
//                    map[i] += map[j]; //반대로 더하기
//                }else{
//                    map[i] += map[j]*2;
//                }
//            }
//            map[i] += 1;
//            result += map[i];
//        }
        System.out.println(result);
    }
}
