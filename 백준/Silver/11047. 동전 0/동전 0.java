import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N,K;
	public static int[] arr;
	public static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		result = 0;

		arr = new int[N];

		for(int i=0;i<N;i++){
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for(int i=N-1;i>=0;i--){
			result += K/arr[i];
			K = K%arr[i];
			if(K==0){
				break;
			}
		}
		System.out.println(result);
	}

}
