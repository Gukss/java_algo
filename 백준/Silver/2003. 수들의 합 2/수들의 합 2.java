import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static int N,M, start, end;
  public static int[] arr;
  public static int sum, result;
  public static void main(String[] args) throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    arr = new int[N];
    st = new StringTokenizer(br.readLine());
    for(int i=0;i<N;i++){
      arr[i] = Integer.parseInt(st.nextToken());
    }
    while(true){
      if(end==N){
        break;
      }else if(sum<M){
        sum+=arr[end];
        end+=1;
      }else{
        sum-=arr[start];
        start+=1;
      }
      if(sum==M){
        result+=1;
      }
    }
    while(sum>M){
      sum-=arr[start];
      start+=1;
      if(sum==M){
        result+=1;
      }
    }
    System.out.println(result);
  }
}
