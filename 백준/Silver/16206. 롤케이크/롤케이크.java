import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    LinkedList<Integer> arr1 = new LinkedList<Integer>();
    LinkedList<Integer> arr2 = new LinkedList<Integer>();
    int result = 0;
    st = new StringTokenizer(br.readLine());
    for(int i=0;i<n;i++){
      int input = Integer.parseInt(st.nextToken());
      if(input % 10 == 0){
        arr1.add(input);
      }else{
        arr2.add(input);
      }
    }
    arr1.sort(Comparator.naturalOrder());
    arr2.sort(Comparator.naturalOrder());
    if(!arr1.isEmpty()){
      for(int x: arr1){
        x = x/10;
        if(x==1){
          result += 1;
          continue;
        }
        if(m>=x-1){
          result = result + x;
          m = m-x+1;
        }else{
          result = result + m;
          m = 0;
          break;
        }
      }
    }
    if(!arr2.isEmpty()){
      for(int x: arr2){
        x = x/10;
        if(m>x){
          result = result + x;
          m = m-x;
        }else{
          result = result + m;
          m = 0;
          break;
        }
      }
    }
    System.out.println(result);
  }
}
