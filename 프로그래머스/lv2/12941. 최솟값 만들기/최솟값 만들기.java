import java.util.*;

class Solution
{
    /*
    그리디는 아니다. 앞에서 제일 작은 값이라고, 뒤에서 큰값이라는 보장이 없다.
    sort해서 제일 큰값과 제일 작은 값을 곱한 것을 sum 하면 제일 작은 값이 나온다?
    */
    public static int size;
    public static int[] x,y;
    public static boolean[] av, bv;
    public static int result;
    
    
    public int solution(int []A, int []B)
    {
        x = A;
        y = B;
        size = A.length;
        result = 0;
        av = new boolean[size];
        bv = new boolean[size];
        Arrays.sort(x);
        Arrays.sort(y);
        for(int i=0;i<size;i++){
            result = result + x[i]*y[size-1-i];
        }
        return result;
    }
}