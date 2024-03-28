import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
  public static int N;
  public static Stack<Character> stack;
  public static void main(String[] args) throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    int result = 0;
    for(int i=0;i<N;i++){
      stack = new Stack<>();
      String str = br.readLine();
      for(int j=0;j<str.length();j++){
        char cur = str.charAt(j);
        if(!stack.isEmpty()){
          if(stack.peek() == cur){
            stack.pop();
          }else{
            stack.add(cur);
          }
        }else{
          stack.add(cur);
        }
      }
      if(stack.size() == 0){
        result += 1;
      }
    }
    System.out.println(result);
  }
}
