package _20220817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj_15683_watch {
    //1- 4가지0123
    //2- 2가지01
    //3- 4가지0123
    //4- 4가지0123
    //5- 1가지0
    static Queue<int[]> qu;
    static int[] dr = {0, 1, 0, -1}; //왼 위 오 아래
    static int[] dc = {1, 0, -1, 0};
    static int N,M;
    static int result = Integer.MAX_VALUE;

    public static void check(int[][] map, Queue<int[]> qu, int[][][] mapL, int squsize){
        int[] cur = qu.poll();
        if(cur == null){
            int temp = 0;
            int[][] resultarr = new int[N][M];
            for (int k = 0; k < squsize; k++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        if(mapL[k][i][j] == 0){
                            //temp += 1;
                            if(resultarr[i][j] == 0){
                                resultarr[i][j] = mapL[k][i][j];
                                resultarr[i][j] = map[i][j];
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(resultarr[i][j] == 0){
                        temp += 1;
                    }
                }
            }
            result = Math.min(result, temp);
            return;
        }

        int sr = cur[0];
        int sc = cur[1];
        if(cur[2] == 2){
            for (int i = 0; i < 2; i++) {
                int temp=1;
                while(true){
                    int nr = sr + dr[i]*temp;
                    int nc = sc + dc[i]*temp;
                    if(nr>=0&&nc>=0&&nr<N&&nc<M&&map[nr][nc] != 6){
                        mapL[qu.size()][nr][nc] = '#';
                        temp += 1;
                    }else{
                        break;
                    }
                }

                temp=1;
                while(true){
                    int nr = sr + dr[(i+2)%4]*temp;
                    int nc = sc + dc[(i+2)%4]*temp;
                    if(nr>=0&&nc>=0&&nr<N&&nc<M&&map[nr][nc] != 6){
                        mapL[qu.size()][nr][nc] = '#';
                        temp += 1;
                    }else{
                        break;
                    }
                }
                check(map, qu, mapL, squsize);
            }
        }else if(cur[2] == 5){
            int temp = 1;
            for (int i = 0; i < 4; i++) {
                while(true){
                    int nr = sr + dr[i]*temp;
                    int nc = sc + dc[i]*temp;
                    if(nr>=0&&nc>=0&&nr<N&&nc<M&&map[nr][nc] != 6){
                        mapL[qu.size()][nr][nc] = '#';
                        temp += 1;
                    }else{
                        break;
                    }
                }
            }
            check(map, qu, mapL, squsize);
        }else if(cur[2] == 1){
            for (int i = 0; i < 4; i++) {
                int temp=1;
                while(true){
                    int nr = sr + dr[i]*temp;
                    int nc = sc + dc[i]*temp;
                    if(nr>=0&&nc>=0&&nr<N&&nc<M&&map[nr][nc] != 6){
                        mapL[qu.size()][nr][nc] = '#';
                        temp += 1;
                    }else{
                        break;
                    }
                }
                check(map, qu, mapL, squsize);
            }
        }else if(cur[2] == 3){
            for (int i = 0; i < 4; i++) {
                int temp=1;
                while(true){
                    int nr = sr + dr[i]*temp;
                    int nc = sc + dc[i]*temp;
                    if(nr>=0&&nc>=0&&nr<N&&nc<M&&map[nr][nc] != 6){
                        mapL[qu.size()][nr][nc] = '#';
                        temp += 1;
                    }else{
                        break;
                    }
                }
                temp=1;
                while(true){
                    int nr = sr + dr[(i+1)%4]*temp;
                    int nc = sc + dc[(i+1)%4]*temp;
                    if(nr>=0&&nc>=0&&nr<N&&nc<M&&map[nr][nc] != 6){
                        mapL[qu.size()][nr][nc] = '#';
                        temp += 1;
                    }else{
                        break;
                    }
                }
                check(map, qu, mapL, squsize);
            }
        }else if(cur[2] == 4){
            for (int i = 0; i < 4; i++) {
                int temp=1;
                while(true){
                    int nr = sr + dr[i]*temp;
                    int nc = sc + dc[i]*temp;
                    if(nr>=0&&nc>=0&&nr<N&&nc<M&&map[nr][nc] != 6){
                        mapL[qu.size()][nr][nc] = '#';
                        temp += 1;
                    }else{
                        break;
                    }
                }

                temp=1;
                while(true){
                    int nr = sr + dr[(i+1)%4]*temp;
                    int nc = sc + dc[(i+1)%4]*temp;
                    if(nr>=0&&nc>=0&&nr<N&&nc<M&&map[nr][nc] != 6){
                        mapL[qu.size()][nr][nc] = '#';
                        temp += 1;
                    }else{
                        break;
                    }
                }

                temp=1;
                while(true){
                    int nr = sr + dr[(i+2)%4]*temp;
                    int nc = sc + dc[(i+2)%4]*temp;
                    if(nr>=0&&nc>=0&&nr<N&&nc<M&&map[nr][nc] != 6){
                        mapL[qu.size()][nr][nc] = '#';
                        temp += 1;
                    }else{
                        break;
                    }
                }
                check(map, qu, mapL, squsize);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        qu = new LinkedList<>();
        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0 && map[i][j] != 6){
                    qu.add(new int[] {i,j, map[i][j]});
                }
            }
        }
        int squsize = qu.size();
        int[][][] mapL = new int[squsize][N][M];
        check(map, qu, mapL, squsize);
        System.out.println(result);
    }
}
