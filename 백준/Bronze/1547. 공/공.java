import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int N;
    public static int[] arr;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[4]; //3까지 인덱스로 사용한다.
        arr[1] = 1;
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int temp = arr[src];
            arr[src] = arr[dest];
            arr[dest] = temp;
        }
        for(int i=0;i<4;i++){
            if(arr[i] != 0){
                System.out.println(i);
            }
        }
    }
}
