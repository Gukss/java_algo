package _20220826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_14891_magnet {
    static int[][] m;
    static Queue<Integer> qu;
    static int num;
    static int left, right;

    public static void start(){ //dir-1->시계, "-1"->반시계
//        qu = new LinkedList<>();
        //cur에서 양옆, 벗어나나 검사하기 -> while문 2개 돌면서 같은 극 나올때 까지 큐에 담으면서 반복 -> 큐에 안담아도 되겠다.
        while(true){ //오른쪽으로
            if(right+1 == 4){ //오른쪽 없을 때
                break;
            }
            if(m[right][2] == m[right+1][6]){ //같은 극일 때
                break;
            }
            right += 1;
        }

        while(true){ //오른쪽으로
            if(left-1 == -1){ //오른쪽 없을 때
                break;
            }
            if(m[left][6] == m[left-1][2]){ //같은 극일 때
                break;
            }
            left -= 1;
        }
    }

    public static void rotate(int dir){ //left에서 right까지 for문으로 탐색하며 배열 돌리기 -> 방향은 하나 넘어갈때 마다 *-1 해주기
        for (int i = left; i <= right; i++) {

            if(dir == 1){
                int temp = m[i][7];
                for (int j = 6; j >= 0; j--) {
                    m[i][j+1] = m[i][j];
                }
                m[i][0] = temp;
            }else{
                int temp = m[i][0];
                for (int j = 0; j <= 6; j++) {
                    m[i][j] = m[i][j+1];
                }
                m[i][7] = temp;
            }
            dir *= -1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        m = new int[4][8];


            for (int i = 0; i < 4; i++) {
                String temp = br.readLine();
                for (int j = 0; j < 8; j++) {
                    m[i][j] = temp.charAt(j) - '0';
                }
            }
            int K = Integer.parseInt(br.readLine());

            for (int i = 0; i < K; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                num = Integer.parseInt(st.nextToken())-1; //회전 시작하는 자석 인덱스로 바꿔주기
                int dir = Integer.parseInt(st.nextToken());

                right = num; //현재 값 부터
                left = num; //현재 값 부터

                start();

                if(num != left){ //이거 없어도 된다.
                    for (int j = 0; j < (num-left); j++) { //차이나는 만큼 -1 곱해주기
                        dir *= -1;
                    }
                }

                rotate(dir);
            }

            int result = 0;

            for (int i = 0; i < 4; i++) {
                result += ((int)Math.pow(2, i) * m[i][0]);
            }
            System.out.printf("%d\n", result);


    }
}