import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String jinho= scan.nextLine();
		int N=Integer.parseInt(scan.nextLine());
		int rslt=0;
		for(int i=0;i<N;i++) {
			String temp=scan.nextLine();
			if(temp.equals(jinho)) rslt++;
		}
		System.out.println(rslt);
	}
}