import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int T; //테스트 케이스
	public static int a,b; //밑, 지수
	public static int[] map; //주기 구하기 위해 저장하는 배열
	//지수승의 1의 자리 맞추기
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int test_case=0;test_case<T;test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
//			map = new int[b+1]; //b만큼 크기로 초기화
//			int turn = -1; //주기 저장할 변수
//			for(int i=1;i<=b;i++) { //1승 부터 b승까지 반복하면서 주기를 구한다.
//				map[i] = 1;
//				//a의 i승의 1의자리를 배열의 i번째에 저장한다.
//				for(int j=1;j<=i;j++) { //9^635와 같이 큰 수가 들어오면 pow로 메모리초과가 발생한다.
//					map[i] = (map[i] * a)%10;
//				}
//				if(map[1]==map[i] && i!=1) { //첫 번째 값과 같으면 주기가 돌아왔다.
//					turn = i-1; //주기 저장
//					break; //반복문 탈출
//				}
//			}
//			if(turn == -1) { //반복문을 그냥 통과한 경우
//				turn = b;
//			}
//			
//			int result = -1;  
//			if(b%turn == 0) { //turn으로 나눴을 때 0이 나오면 항상 같은 값이 나온다는 뜻이다. -> turn이 1이라는 뜻이다. -> 첫 번째 값을 답으로 한다. 
//				result = map[turn];
//			}else {				
//				result = map[b%turn]; ///b를 turn으로 나눈 나머지; 주기를 구해서 답을 찾는다.
//			}
//			sb.append(result+"\n");
			if(a % 10 == 0) { //10으로 나눠 떨어지면 제곱해고 계속 일의자리는 0, 10번 컴퓨터에서 데이터가 처리된다.
				sb.append(10+"\n");
				continue;
			}
			
			int result = 1;
			for(int i=1;i<=b;i++) { //b만큼 반복
				result = (result * a) % 10; //숫자가 커지면 메모리 초과가 발생 => 10으로 나누면서 나머지만 가지고 진행
			}
			sb.append(result+"\n");
		}
		System.out.println(sb);
	}
}
