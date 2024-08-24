import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static int N,M, result;
  public static int[][] arr;
  public static boolean[][] v;
  public static int[] dr = {-1, 0, 1, 0}; //위오밑왼
  public static int[] dc = {0, 1, 0, -1}; //위오밑왼
  public static int[] exdr = {0, -1, 0, 1, 0}; //자신위오밑왼
  public static int[] exdc = {0, 0, 1, 0, -1}; //자신위오밑왼
  
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    arr = new int[N][M];
    v = new boolean[N][M];

    for(int i=0;i<N;i++){
      st = new StringTokenizer(br.readLine());
      for(int j=0;j<M;j++){
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for(int i=0;i<N;i++){
      for(int j=0;j<M;j++){
        v[i][j] = true;
        dfs(i, j, 0, arr[i][j]);
        v[i][j] = false;
      }
    }

    //ㅗ모양
    for(int i=0;i<N;i++){
      for(int j=0;j<M;j++){
        for(int a=0;a<4;a++){ //ㅗ, ㅏ, ㅜ, ㅓ 방향 결정
          int count = 0;
          int sum = 0;
          for(int b=0;b<4;b++){
            if(a==b){
              continue;
            }
            int nr = i + dr[b];
            int nc = j + dc[b];
            if(nr>=0&&nc>=0&&nr<N&&nc<M) {
              count += 1;
              sum += arr[nr][nc];
            }
          }
          if(count == 3){
            result = Math.max(result, arr[i][j]+sum);
          }
        }
      }
    }
    System.out.println(result);
  }

  public static void dfs(int r, int c, int depth, int sum) {
    if(depth == 3) {
      result = Math.max(result, sum);
      return;
    }
    for(int i=0;i<4;i++){
      int nr = r + dr[i];
      int nc = c + dc[i];
      if(nr>=0&&nc>=0&&nr<N&&nc<M && !v[nr][nc]) {
        v[nr][nc] = true;
        dfs(nr, nc, depth+1, sum+arr[nr][nc]);
        v[nr][nc] = false;
      }
    }

  }
}
