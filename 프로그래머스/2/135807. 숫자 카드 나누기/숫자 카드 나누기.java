import java.util.*;

class Solution {
    public static int[] ArrayA, ArrayB;
    public static int num;
    public static int answer;
    public int solution(int[] arrayA, int[] arrayB) {
        ArrayA = arrayA;
        ArrayB = arrayB;
        answer = 0;
        num = Math.max(ArrayA[0], ArrayB[0]);
        for(int i=2;i<=num;i++){
            if((divided(ArrayA, i) && notdivided(ArrayB, i)) || 
               (divided(ArrayB, i) && notdivided(ArrayA, i))) {
                answer = i;        
            }
        }
        
        return answer;
    }
    
    public static boolean divided(int[] arr, int n){
        for(int x: arr){
            if(x%n != 0){
                return false;
            }
        }
        return true;
    }
    
    public static boolean notdivided(int[] arr, int n){
        for(int x: arr){
            if(x%n == 0){
                return false;
            }
        }
        return true;
    }
}