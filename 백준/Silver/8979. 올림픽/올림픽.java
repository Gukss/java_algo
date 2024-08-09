import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static int N, K;
  public static int[][] input;
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    input = new int[N][3];
    for(int i=0;i<N;i++){
      st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      for(int j=0;j<3;j++){
        input[n-1][j] = Integer.parseInt(st.nextToken());
      }
    }
    int result = 0;
    boolean[] v = new boolean[N];
    for(int i=0;i<=2;i++){
      for(int j=0;j<N;j++){
        if(j==K-1){
          continue;
        }
        if(input[K-1][i] < input[j][i] && !v[j]){
          result += 1;
          v[j] = true;
        }
      }
    }
    System.out.println(result);
  }
}
