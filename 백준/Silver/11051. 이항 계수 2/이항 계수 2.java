import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,K;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[N+1][K+1];

        //f(n,k) = f(n-1,k)+f(n-1,k-1)
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= Math.min(i,K); j++) {
                if(i==j || j==0){
                    dp[i][j] = 1;
                    continue;
                }
                if(dp[i][j] != 0){
                    continue;
                }
                dp[i][j] = (dp[i-1][j]+dp[i-1][j-1])%10007;
            }
        }
        System.out.println(dp[N][K]);
    }
}
