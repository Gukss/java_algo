import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static long A,B,C;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());
		C = Long.parseLong(st.nextToken());

		A = A % C; //

		System.out.println(pow(A, B));
	}

	public static long pow(long a, long b) {
		if (b == 1) {
			return a%C;
		}
		long temp = pow(a, b/2);
		if(b%2==1){
			return (temp * temp%C) * a %C;
		}
		return temp * temp %C;
	}
}

//2147483647 2147483647 100001