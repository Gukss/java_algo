import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static int N;
  public static int[][] map;
  public static int[] sel;
  public static boolean[] v;
  public static int result;
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    N = Integer.parseInt(br.readLine());
    map = new int[N][N];
    sel = new int[N/2];
    v = new boolean[N];

    for(int i=0;i<N;i++){
      st = new StringTokenizer(br.readLine());
      for(int j=0;j<N;j++){
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    result = Integer.MAX_VALUE;
    comb(0,0);
    System.out.println(result);
  }

  public static void comb(int idx, int start){
    if(idx == N/2){
      // System.out.println(Arrays.toString(sel));
      v = new boolean[N];
      for(int i=0;i<N/2;i++){
        v[sel[i]] = true;
      }
      int a = 0;
      int b = 0;
      for(int i=0;i<N;i++){
        for(int j=0;j<N;j++){
          if(i==j)continue;
          if(v[i] && v[j]){
            a += map[i][j];
          }else if(!v[i] && !v[j]){
            b += map[i][j];
          }
        }
      }
      result = Math.min(result, Math.abs(a-b));
      return;
    }
    for(int i=start;i<N;i++){
      sel[idx] = i;
      comb(idx+1, i+1);
    }
  }
}
