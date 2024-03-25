import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
  //전부 stack에 넣는다.
  //pop하고 peek와 비교한다. pop했을 때 size를 저장해놓는다. idx랑 같이 사용한다. => 한번에 출력하려니까 안됐다.
  //pop할 때 idx를 바로 출력한다.
  //pop > peek => 다시 pop -> 이때 pop은 버린다.
  //pop < peek => 출력
  public static int N;
  public static Stack<Pos> stack;
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    StringBuilder sb = new StringBuilder();
    stack = new Stack<>();
    for(int i=0;i<N;i++){
      int cur = Integer.parseInt(st.nextToken());
      while(!stack.isEmpty()){
        if(cur < stack.peek().e){
          sb.append(stack.peek().idx+" ");
          break;
        }
        stack.pop();
      }
      if(stack.isEmpty()){
        sb.append("0 ");
      }
      stack.add(new Pos(cur, i+1));
    }
    System.out.println(sb);
  }
  public static class Pos{
    int e,idx;
    public Pos(int e, int idx){
      this.e = e;
      this.idx = idx;
    }
  }
}

/*
5
1 2 3 4 5
 */