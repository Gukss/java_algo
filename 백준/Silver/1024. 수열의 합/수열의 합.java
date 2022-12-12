import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int N,L; //합이 N, 길이가 적어도 L, 가장 짧은 음이아닌 정수 리스트
	public static int a,b;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		//N이 주어졌을 때, 자연수(L)로 나누게 되면 그 몫(N/L)의 앞뒤 수를 (L-1)/2만큼 더하거나 뺐을 때 연속된 수가 나온다.
		//문제에서는 음수가 나오면 안 된다고 했기 때문에 몫(N/L)을 기준으로 했을 때 연속된 수의 가장 처음 수, 
		//즉 N/L - ((L-1)/2) 값을 구해서 음수가 나온다면 -1을 출력해 주면 된다
		while(true) {
			int min = N/L - ((L-1)/2);
			if(min < 0 || L > 100) {
				System.out.println(-1);
				System.exit(0);
			}
			int sum = (min*2+L-1)*L/2; //연속된 수의 처음 수
			if(sum == N) {
				for (int i = 0; i < L; i++) {
					System.out.print((min+i) + " ");
				}
				System.exit(0);
			}
			L++;
		}
	}
}
//https://read-me.tistory.com/m/entry/JAVABOJ-1234-13
