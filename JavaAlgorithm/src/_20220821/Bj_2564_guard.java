package _20220821;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj_2564_guard {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int c = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int num = Integer.parseInt(br.readLine());
        int[] map = new int[num];
        int cur = 0;

        for (int i = 0; i < num+1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int len = 0;

            if(x == 1){ //북
                len = y;
            } else if (x== 2) { //남
                len = c+r+c - y;
            }
            else if (x== 3) { //서
                len = c+r+c+r - y;
            }
            else if (x== 4) { //동
                len = c+ y;
            }
            if(i == num){
                cur = len;
                continue;
            }
            map[i] = len;
        }

        int sum = 0;
        for (int i = 0; i < num; i++) {
            int len1 = Math.abs(cur - map[i]);
            int len2 = Math.abs(r+r+c+c - len1);
            sum += Math.min(len1, len2);

        }
        System.out.println(sum);
    }
}
//인터넷 참고
//가장자리만 타고 이동하기 때문에 사각형이 아닌 직선으로 생각해도 상관없다.
//len2 구할 때 그냥 cur - map[i] 하면 음수가 나와서 수가 커질 수 있다. len1을 빼줘야 한다.