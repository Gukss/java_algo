import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
  public static void main(String[] args) throws Exception {
    //닫는 괄호가 나왔을 때
    //바로 앞이 여는 괄호면 레이저,
    //바로 앞이 닫는 괄호면 막대기끝
    //peek는 (()()) 같이 레이저 사이에 괄호가 없으면 제일 바깥 괄호도 레이저로 인식한다.
    //peek대신 str.charAt(i-1)사용
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    int size = str.length();
    Stack<Character> st = new Stack<>();
    int result = 0;
    for(int i=0;i<size;i++){
      if(str.charAt(i) == ')'){
        st.pop();
        if(str.charAt(i-1) == '('){
          result += st.size();
        }else{
          result += 1;
        }
      }else{
        st.add(str.charAt(i));
      }
    }
    System.out.println(result);
  }
  
}
