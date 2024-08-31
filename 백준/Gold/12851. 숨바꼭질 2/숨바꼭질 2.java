import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  public static int N,K;
  public static Queue<Pos> qu;
  public static int[] v;
  public static void main(String[] args) throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    qu = new LinkedList<>();
    v = new int[100001];
    int dResult = 0;
    int mResult = 0;
    if(N==K) {
      System.out.println(dResult + "\n" + 1);
      System.exit(0);
    }
    qu.add(new Pos(N, 0));
    v[N] = 1;
    while(!qu.isEmpty()) {
      Pos cur = qu.poll();
      int depth = cur.depth;
      int[] arr = {cur.r+1, cur.r-1, cur.r*2};
      if(cur.r == K) {
        if(dResult == 0) {
          dResult = depth;
        }
        if(dResult == depth) {
          mResult += 1;
        }
        continue;
      }
      for(int i=0;i<3;i++){
        int nr = arr[i];
        if(nr>=0 && nr<100001 && (v[nr] == 0 || v[nr] == depth+1)) {
          v[nr] = depth+1;
          qu.add(new Pos(nr, depth+1));
        }
      }
    }
    System.out.println(dResult + "\n" + mResult);
  }

  public static class Pos{
    int r, depth;
    Pos(int r, int depth) {
      this.r = r;
      this.depth = depth;
    }
  }
}
