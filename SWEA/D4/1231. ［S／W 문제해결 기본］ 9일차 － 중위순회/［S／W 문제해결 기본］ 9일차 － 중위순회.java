import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static int N;
    public static char[] arr;
    public static StringBuilder sb;

    public static void inOrder(int idx){
        if(idx > N) return;
        inOrder(2*idx);
        sb.append(arr[idx]);
        inOrder(2*idx+1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        for(int test_case = 1; test_case<=10;test_case++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            arr = new char[N+1];

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                int idx = Integer.parseInt(st.nextToken());
                arr[idx] = st.nextToken().charAt(0);
            }
            sb.append("#"+test_case+" ");
            inOrder(1);
            sb.append("\n");
        }
        System.out.println(sb);

    }
}
