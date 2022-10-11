package _20221008;

import java.math.BigInteger;
import java.util.Scanner;

public class Bj_1271_rich {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BigInteger a = sc.nextBigInteger();
        BigInteger b = sc.nextBigInteger();
        sc.close();
        System.out.println(a.divide(b));
        System.out.println(a.remainder(b));
    }
}