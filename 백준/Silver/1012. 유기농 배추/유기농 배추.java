import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  public static int T, M, N, K, result;
  public static int[][] arr;
  public static boolean[][] v;
  public static int[] dr = {-1, 0, 1, 0}; //위오밑왼
  public static int[] dc = {0, 1, 0, -1}; //위오밑왼
  public static Queue<Pos> qu;
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    T = Integer.parseInt(br.readLine());
    StringBuilder sb = new StringBuilder();
    for(int TC = 0;TC<T;TC++){
      StringTokenizer st = new StringTokenizer(br.readLine());
      M = Integer.parseInt(st.nextToken());
      N = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
  
      arr = new int[N][M];
      v = new boolean[N][M];
      for(int i=0;i<K;i++){
        st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        arr[r][c] = 1;
      }

      qu = new LinkedList<>();
      result = 0;
      for(int i=0;i<N;i++){
        for(int j=0;j<M;j++){
          if(arr[i][j] == 1 && !v[i][j]){ //방문 안했을 때
            result += 1;
            qu.add(new Pos(i, j));
            while(!qu.isEmpty()){
              Pos cur = qu.poll();
              v[cur.r][cur.c] = true;
              for(int k=0;k<4;k++){
                int nr = cur.r + dr[k];
                int nc = cur.c + dc[k];
                if(nr>=0&&nc>=0&&nr<N&&nc<M && arr[nr][nc] == 1 && !v[nr][nc]){
                  v[nr][nc] = true;
                  qu.add(new Pos(nr, nc));
                }
              }
            }
          }
        }
      }
      sb.append(result+" \n");
    }
    System.out.println(sb);
  }

  public static class Pos {
    int r, c;
    Pos(int r, int c) {
      this.r = r;
      this.c = c;
    }
  }
}
