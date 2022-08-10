package day007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_16935_rotateMatrix3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] op = new int[R];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }

        for(int o: op){
            switch(o){
                case 1:
                    //1번 상하반전
                    for (int i = 0; i < map.length/2; i++) {
                        int[] temp = map[i];
                        map[i] = map[map.length-1-i];
                        map[map.length-i-1] = temp;
                    }
                    break;
                case 2:
                    //2번 좌우 반전
                    for (int i = 0; i < map.length; i++) {
                        for (int j = 0; j < map[0].length/2; j++) {
                            int temp = map[i][j];
                            map[i][j] = map[i][map[0].length-1-j];
                            map[i][map[0].length-1-j] = temp;
                        }
                    }
                    break;
                case 3:
                    //오른쪽으로 90도 회전
                    int[][] temp = new int[map[0].length][map.length];
                    for (int i = 0; i < map.length; i++) {
                        for(int j=0;j<map[0].length;j++){
                            temp[j][map.length-1-i] = map[i][j];
                        }
                    }
                    map = temp;
                    break;
                case 4:
                    //왼쪽으로 90도 회전
                    temp = new int[map[0].length][map.length];
                    for (int i = 0; i < map.length; i++) {
                        for(int j=0;j<map[0].length;j++){
                            temp[map[0].length-1-j][i] = map[i][j];
                        }
                    }
                    map = temp;
                    break;
                case 5:
                    //1번그룹->2번그룹, 2번그룹->3번그룹, 3번 그룹->4번그룹, 4번그룹->1번그룹
                    int[][] temp1 = new int[map.length/2][map[0].length/2];
                    int[][] temp2 = new int[map.length/2][map[0].length/2];
                    int[][] temp3 = new int[map.length/2][map[0].length/2];
                    int[][] temp4 = new int[map.length/2][map[0].length/2];
                    for (int i = 0; i < map.length/2; i++) {
                        for (int j = 0; j < map[0].length/2; j++) {
                            temp1[i][j] = map[i][j];
                            temp2[i][j] = map[i][map[0].length/2+j];
                            temp3[i][j] = map[map.length/2+i][map[0].length/2+j];
                            temp4[i][j] = map[map.length/2+i][j];
                        }
                    }
                    for (int i = 0; i < map.length/2; i++) {
                        for (int j = 0; j < map[0].length/2; j++) {
                            map[i][j] = temp4[i][j]; //4->1
                            map[i][map[0].length/2+j] = temp1[i][j]; //1->2
                            map[map.length/2+i][map[0].length/2+j] = temp2[i][j]; //2->3
                            map[map.length/2+i][j] = temp3[i][j]; //3->4
                        }
                    }

                    break;
                case 6:
                    //1번그룹->4번그룹, 4번그룹->3번그룹, 3번 그룹->2번그룹, 2번그룹->1번그룹
                    temp1 = new int[map.length/2][map[0].length/2];
                    temp2 = new int[map.length/2][map[0].length/2];
                    temp3 = new int[map.length/2][map[0].length/2];
                    temp4 = new int[map.length/2][map[0].length/2];
                    for (int i = 0; i < map.length/2; i++) {
                        for (int j = 0; j < map[0].length/2; j++) {
                            temp1[i][j] = map[i][j];
                            temp2[i][j] = map[i][map[0].length/2+j];
                            temp3[i][j] = map[map.length/2+i][map[0].length/2+j];
                            temp4[i][j] = map[map.length/2+i][j];
                        }
                    }
                    for (int i = 0; i < map.length/2; i++) {
                        for (int j = 0; j < map[0].length/2; j++) {
                            map[i][j] = temp2[i][j]; //2->1
                            map[i][map[0].length/2+j] = temp3[i][j]; //3->2
                            map[map.length/2+i][map[0].length/2+j] = temp4[i][j]; //4->3
                            map[map.length/2+i][j] = temp1[i][j]; //1->4
                        }
                    }
                    break;
            }
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
