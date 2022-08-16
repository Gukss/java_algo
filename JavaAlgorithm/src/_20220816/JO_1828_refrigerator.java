package _20220816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class JO_1828_refrigerator {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int result = 1; //냉장고 개수는 1부터 시작
        int md = arr[0][1]; //첫 번째 최고온도을 maxDegree로 저장

        for (int i = 0; i < N; i++) {
            if(arr[i][0] <= md){ //maxDegree가 최저온도 보다 클 때 -> 같은 냉장고 사용할 수 있다.
                if(arr[i][1] < md){ //maxDegree가 최고온도 보다 클 때 -> maxDegree 갱신 필요
                    md = arr[i][1];
                }
            }else{ //maxDegree가 최저온도 보다 작을 때 -> 같은 냉장고 사용 불가능
                md = arr[i][1];
                result += 1;
            }
        }
        System.out.println(result);
    }
}
