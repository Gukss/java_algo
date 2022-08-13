package day008;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_16234_immigrant {
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static Queue<int[]> qu;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        boolean loop = true;
        int result = 0;
        //boolean[][] visited_b = new boolean[N][N];
        qu = new LinkedList<>(); //큐는 새로 만들어줄 필요 없다. 빌때 까지 반복한다.
        boolean[][] visited_b = new boolean[N][N];

        while(loop){
            boolean change = false;
            //델타로 사방 확인하면서 국경을 열지 말지 -> 결국 bfs
            boolean[][] visited = new boolean[N][N];
            //int sum = 0;
            int flag = 1;
            int[][] visited_i = new int[N][N];


            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(!visited_b[i][j] && !visited[i][j]){
                        qu.add(new int[] {i,j});
                        while(!qu.isEmpty()){
                            int[] cur = qu.poll();
                            visited[cur[0]][cur[1]] = true;
                            visited_i[cur[0]][cur[1]] = flag; //flag로 현재 위치 표시

                            int sr = cur[0];
                            int sc = cur[1];
                            for (int k = 0; k < 4; k++) { //사방
                                int nr = sr + dr[k];
                                int nc = sc + dc[k];
                                if(nr<0||nc<0||nr>=N||nc>=N || visited[nr][nc]){ //방문했거나 벗어나면
                                    continue;
                                }
                                int diff = Math.abs(map[cur[0]][cur[1]] - map[nr][nc]); //차이
                                if(diff>= L && diff <= R){
                                    //temp += map[nr][nc];
                                    visited[nr][nc] = true;
                                    visited_b[nr][nc] = true;
                                    visited_b[cur[0]][cur[1]] = true;


                                    visited_i[nr][nc] = flag;
                                    qu.add(new int[]{nr, nc});
                                }
                            }
                        }
                        flag += 1;
                    }
                }
            }
            flag--;
            //flag=3일때 -> 0,1,2
            int[] sum = new int[flag];
            int[] count = new int[flag];

            for (int i = 1; i <= flag; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < N; k++) {
                        if (visited_i[j][k] == i) {
                            sum[i - 1] += map[j][k];
                            count[i-1] ++;
                        }
                    }
                }

                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < N; k++) {
                        if (visited_i[j][k] == i) {
                            map[j][k] = sum[i-1]/count[i-1];
                            //visited_b[j][k] = false; 모두 다 true로 바뀌어져 버린다.
                            //처음 안바뀐 값이 같은 부분은 flag가 0이지만 다음 안바뀌는 값이 같은 부분은 flag가 1이 되서 if안으로 들어올 수 있다.
                            change = true;
                        }
                    }
                }

            }

            if(change == false){
                loop = false;
            }
            if(loop){
                result++;
            }
            //map = op;
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(result));
        bw.flush(); // write로 담은 내용 출력 후, 버퍼를 비움.
        bw.close();
        //System.out.println(result);
    }
}
