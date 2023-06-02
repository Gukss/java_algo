package _20230523;

import java.util.ArrayList;
import java.util.List;

public class test {
	public static void main(String[] args) throws Exception{
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);

		Integer[] arr = list.toArray(new Integer[0]);
		for(int x: arr){
			System.out.println(x);
		}
	}
}
