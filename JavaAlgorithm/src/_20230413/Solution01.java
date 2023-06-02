package _20230413;

public class Solution01 {

    public static int Solution(String number){
        //i+1이 연속된 숫자면 i+1해준다.
        //다르면 그냥 넘어간다.
        //원래는 0다음이 1이지만, 0 다음이면 1이여도 그냥 넘어간다.
        char[] arr = number.toCharArray();
        int size = arr.length;
        int count = 0;
        for(int i=0;i<size-1;i++){ //size-1까지 반복 -> i+1을 계산해야한다.
            if(arr[i]+1 == arr[i]){ //뒤가 연속되면
                count += 1;
                if(arr[i] == '0'){ //뒤가 0이면
                    continue;
                }
                i+=1; //i++해주고
            }else{ //뒤가 연속되지 않으면
                count += 1;
            }
        }
        System.out.println(count);
        return count;
    }

    public static void main(String[] args) {
        Solution("12156");
    }
}
