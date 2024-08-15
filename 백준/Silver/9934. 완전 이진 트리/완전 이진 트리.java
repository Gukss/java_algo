import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static int k;
  public static int[] arr;
  public static String[] result;
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    k = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    arr = new int[(int)Math.pow(2, k) - 1];
    result = new String[k];
    Arrays.fill(result, "");
    //arr.length/2 해서 가운데 찾아 간다음
    for(int i=0;i<(int)Math.pow(2, k) - 1;i++){
      arr[i] = Integer.parseInt(st.nextToken());
    }

    in(0, 0, ((int)Math.pow(2,k)-1));
    StringBuilder sb = new StringBuilder();
    for(int i=0;i<k;i++){
      sb.append(result[i]);
      sb.append("\n");
    }
    System.out.println(sb);
  }

  public static void in(int depth, int start, int end){
    if(k == depth){
      return;
    }
    int index = (start+end)/2;
    result[depth] = result[depth]+Integer.toString(arr[index])+" ";
    in(depth+1, start, index-1);
    in(depth+1, index+1, end);
  }
}
