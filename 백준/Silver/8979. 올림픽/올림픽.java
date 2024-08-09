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
    int result = 1;
      for(int i=0;i<N;i++){
        if(input[K-1][0] < input[i][0]){
          result += 1;
        }else if((input[K-1][0] == input[i][0]) && (input[K-1][1] < input[i][1])){
          result += 1;
        }else if((input[K-1][0] == input[i][0]) && (input[K-1][1] == input[i][1]) && (input[K-1][2] < input[i][2])){
          result += 1;
        }
      }
    if(result == 0){
      result = 1;
    }
    System.out.println(result);
  }
}
