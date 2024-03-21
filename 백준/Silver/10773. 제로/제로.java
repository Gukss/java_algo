import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
  public static int K;
  public static Stack<Integer> st;
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    K = Integer.parseInt(br.readLine());
    st = new Stack<>();
    for(int i=0;i<K;i++){
      int cur = Integer.parseInt(br.readLine());
      if(cur == 0){
        st.pop();
      }else{
        st.add(cur);
      }
    }
    int result = 0;
    for(int x: st){
      result += x;
    }
    System.out.println(result);
  }
}
