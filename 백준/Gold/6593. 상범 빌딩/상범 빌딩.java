import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
  public static void main(String[] args) throws Exception {
    int L, R, C;
    char[][][] arr;
    int[] dr = {0,0,1,-1,0,0}; //상,하,남,북,동,서
    int[] dc = {0,0,0,0,1,-1}; //상,하,남,북,동,서
    int[] dh = {1,-1,0,0,0,0}; //상,하,남,북,동,서
    boolean[][][] v;
    Queue<Pos> qu;
    StringBuilder sb = new StringBuilder();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    while(true){
      st = new StringTokenizer(br.readLine());
      qu = new LinkedList<>();
      boolean finish = false;
      // if(st.hasMoreTokens()){
      //   // st = new StringTokenizer(br.readLine());
      //   break;
      // }
      L = Integer.parseInt(st.nextToken());
      R = Integer.parseInt(st.nextToken());
      C = Integer.parseInt(st.nextToken());
      if(L==0 && R==0 && C==0){
        break;
      }
      arr = new char[L][R][C];
      v = new boolean[L][R][C];
      for(int i=0;i<L;i++){
        for(int j=0;j<R;j++){
          String str = br.readLine();
          for(int k=0;k<C;k++){
            arr[i][j][k] = str.charAt(k);
            if(arr[i][j][k] == 'S'){
              qu.add(new Pos(i,j,k,0));
              v[i][j][k] = true;
            }
          }
        }
        br.readLine();
      }
      
      while(!qu.isEmpty()){
        Pos cur = qu.poll();
        for(int i=0;i<6;i++){
          int nh = cur.h + dh[i];
          int nr = cur.r + dr[i];
          int nc = cur.c + dc[i];
          if(nh>=0 && nr>=0 && nc>=0 && nh<L && nr<R && nc<C && !v[nh][nr][nc] && arr[nh][nr][nc] != '#'){
            if(arr[nh][nr][nc] == 'E'){
              int result = cur.v+1;
              sb.append("Escaped in "+ result +" minute(s).\n");
              finish = true;
            }else{
              v[nh][nr][nc] = true;
              qu.add(new Pos(nh,nr,nc,cur.v+1));
            }
          }
          if(finish){
            break;
          }
        }
        if(finish){
          break;
        }
      }
      if(!finish){
        sb.append("Trapped!\n");
      }
    }
    System.out.println(sb);
  }

  public static class Pos{
    int r,c,h,v;
    public Pos(int h, int r, int c, int v){
      this.r = r;
      this.c = c;
      this.h = h;
      this.v = v;
    }
  }
}
