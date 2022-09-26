package _20220925;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_16234_move {
    static int N,L,R;
    static int[][] map;
    static int[][] v;
    static int[] dr = {-1, 0, 1, 0}; //위, 오, 밑, 왼
    static int[] dc = {0, 1, 0, -1};
    static int result = 0;
    static Queue<int[]> qu;


    public static void check(int countflag){
        for (int k = 1; k <= countflag; k++) {
            int count = 0;
            int sum = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(v[i][j] == k){
                        count += 1;
                        sum += map[i][j];
                    }
                }
            }
            if(count != 0){
                int update = sum/count;

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if(v[i][j] == k){
                            map[i][j] = update;
                        }
                    }
                }
            }
        }

    }

    public static boolean move(){
        boolean flag = false;
        qu = new LinkedList<>();
        v = new int[N][N];

        int countflag = 1;
        int value = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                qu.add(new int[]{i,j, countflag});

                while(!qu.isEmpty()){
                    int[] cur = qu.poll();
                    for (int k = 0; k < 4; k++) {
                        int nr = cur[0] + dr[k];
                        int nc = cur[1] + dc[k];
                        if(nr>=0&&nc>=0&&nr<N&&nc<N && v[nr][nc] == 0){
                            int diff = Math.abs(map[cur[0]][cur[1]] - map[nr][nc]);
                            if(diff>=L && diff<=R){
                                v[nr][nc] = cur[2];
                                v[cur[0]][cur[1]] = cur[2];
                                qu.add(new int[]{nr,nc, cur[2]});
                                flag = true;
                                value = countflag;
                            }
                        }
                    }
                }

                countflag += 1;
            }
        }
        check(value);

        if(flag){
            result += 1;
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        result = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(move());
        System.out.println(result);
    }
}
