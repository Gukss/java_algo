package _20230321;

import java.io.IOException;

public class test {
	public static void main(String[] args) throws IOException {
		int[] a = new int[4];
		System.out.println(System.identityHashCode(a));
		a[0] = 1;
		System.out.println(System.identityHashCode(a));
		int[] b = {0,1,0,0};
		System.out.println(System.identityHashCode(b));
	}
}
