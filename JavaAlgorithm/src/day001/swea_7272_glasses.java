package day001;

import java.util.Scanner;

public class swea_7272_glasses {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int[] map = {1,2,0,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0};
		
		for (int tc = 1; tc <= T; tc++) {
			
			String x = sc.next();
			String y = sc.next();
			boolean flag = true;
			//글자수가 다르면 다른 문자열이다.
			if(x.length() != y.length()) {
				flag = false;
			} else {
				//글자수가 같다. 한 글자씩 map에서 비교한다.
				for (int j = 0; j < x.length(); j++) {
					if(map[x.charAt(j) - 65] != map[y.charAt(j) - 65]) {
						flag = false;
						break;
					}
				}
			}
			if(flag) {
				System.out.printf("#%d SAME\n", tc);
			}else {
				System.out.printf("#%d DIFF\n", tc);
			}
			
			
//			int x_count = 0;
//			int y_count = 0;
//			
//			for (int j = 0; j < x.length(); j++) {
//				x_count += map[x.charAt(j) - 65];
//			}
//			for (int j = 0; j < y.length(); j++) {
//				y_count += map[y.charAt(j) - 65];
//			}
//			
//			if(x_count == y_count) {
//				System.out.printf("#%d SAME\n", tc);
//			} else {
//				System.out.printf("#%d DIFF\n", tc);
//			}
		}
	}

}
