import java.util.Arrays;
import java.util.Scanner;

class Main {
	public static void main(String[] args) {

		Scanner stdIn = new Scanner(System.in);
		int score1 = 0;
		int score2 = 0;
		int S = 0, S2 = 0;
		
		for(int i=0; i<4; i++) {
			score1 = stdIn.nextInt();
			S += score1;
		}
		
		for(int i=0; i<4; i++) {
			score2 = stdIn.nextInt();
			S2 += score2;
		}
	
		if(S >= S2) {
			System.out.println(S);
		}else if(S < S2) {
			System.out.println(S2);
		}

    }	
	
}