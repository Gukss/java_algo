import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static int t, n, k;
  public static int[] arr;
  public static void main(String[] args) throws Exception {
    //정렬
    //가장 양끝에서 포인터 시작
    //k와 양포인터 abs(합의 차이)를 저장해놓고
    //차이가 양수면 왼쪽포인터+=1, 음수면 오른쪽포인터+=1
    //abs(합의차이)가 작아지면 result = 1;
    //abs(합의차이)가 같으면 result += 1;
    //두 포인터가 교차하면 break;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuffer sb = new StringBuffer();
    t = Integer.parseInt(br.readLine());
    for(int i=0;i<t;i++){
      StringTokenizer st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      k = Integer.parseInt(st.nextToken());
      arr = new int[n];
      st = new StringTokenizer(br.readLine());
      int result = 0;
      for(int j=0;j<n;j++){
        arr[j] = Integer.parseInt(st.nextToken());
      }
      Arrays.sort(arr);
      int l = 0;
      int r = n-1;
      int sum = Integer.MAX_VALUE;
      int diff = Integer.MAX_VALUE;
      int minAbsDiff = Math.abs(k - (arr[l] + arr[r]));
      int curAbsDiff = Integer.MAX_VALUE;
      while(l<r){
        sum = arr[l] + arr[r];
        diff = k - sum;
        curAbsDiff = Math.abs(diff);

        if(minAbsDiff != 0 && curAbsDiff == 0){
          result = 1;
        }
        
        if(diff > 0){
          l+=1;
        }else if(diff < 0){
          r-=1;
        }else{
          l+=1;
          r-=1;
        }

        if(minAbsDiff == curAbsDiff){
          result += 1;
        }else if(minAbsDiff > curAbsDiff){
          result = 1;
        }

        minAbsDiff = Math.min(minAbsDiff, curAbsDiff);
      }

      sb.append(result + "\n");
    }
    System.out.println(sb);
  }
}