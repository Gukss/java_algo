package day005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_1600_MonkeyHorse {
    static int[][] map;
    static int[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[] hdr = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] hdc = {1, 2, 2, 1, -1, -2, -2, -1};
    static Queue<Pos> qu;
    static int W,H;
    static int result;
    public static void bfs(Pos pos){
         qu = new LinkedList<>();
         visited[pos.r][pos.c] = pos.horse;
         qu.add(pos);

         while(!qu.isEmpty()){
             Pos cur = qu.poll();
             if(cur.r == H-1 && cur.c == W-1){ //완료 조건
                 result = Math.min(result, cur.move); //최소값 계산
             }
             for (int i = 0; i < 4; i++) { //한 칸씩 이동하는 경우
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if(nr<0 || nc < 0 ||  nr >= H || nc >= W || map[nr][nc] == 1){ //map벗어나는경우, 벽인 경우
                    continue;
                }
                //i-1번 능력을 사용해서 도착한 곳이 i번 능력을 사용해서 도착한 곳이라면 다른 경로이기 때문에 다시 탐색한다.
                if(visited[nr][nc] < cur.horse) { //horse가 같은 경우 돌아가면 안된다. 능력을 사용안하고 오면 horse가 크다.
                    visited[nr][nc] = cur.horse; //nr, nc에 horse만큼 말 이동을 남기고 방문했다.
                    qu.add(new Pos(nr, nc, cur.horse, cur.move+1));
                }
             }
             if(cur.horse > 0){ //능력으로 이동할 수 있을 때 -> 말 이동으로 이동
                 for (int i = 0; i < 8; i++) {
                     int hnr = cur.r + hdr[i];
                     int hnc = cur.c + hdc[i];
                     if(hnr<0 || hnc < 0 ||  hnr >= H || hnc >= W || map[hnr][hnc] == 1){ //map벗어나는경우, 벽인 경우
                         continue;
                     }
                     if(visited[hnr][hnc] < cur.horse-1){ //능력을 쓰지 않고 오면 horse가 크다.
                         visited[hnr][hnc] = cur.horse - 1;
                        qu.add(new Pos(hnr, hnc, cur.horse - 1, cur.move+1));
                     }
                 }
             }
         }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        visited = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                visited[i][j] = -1; //visited -1로 초기화 -> horse가 0일 때도 한 칸씩 이동할 수 있어야 한다.
            }
        }
        result = Integer.MAX_VALUE;
        Pos pos = new Pos(0, 0, K, 0);
        bfs(pos); //함수 시작
        if(result == Integer.MAX_VALUE){
            System.out.println(-1);
        }else {
            System.out.println(result);
        }
    }
    public static class Pos{
        int r, c, horse, move;
        public Pos(int r, int c, int horse, int move){
            this.r = r;
            this.c = c;
            this.horse = horse;
            this.move = move;
        }
    }

}

//1
//5 5
//0 0 0 0 0
//0 0 0 0 0
//0 0 0 0 0
//0 0 0 1 1
//0 0 0 1 0
//오답: -1
