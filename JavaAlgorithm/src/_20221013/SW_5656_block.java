package _20221013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_5656_block {
    static int T,N,W,H;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0}; //위, 오, 밑, 왼
    static int[] dc = {0, 1, 0, -1}; //위, 오, 밑, 왼
    static int[] arr;
    static int[] sel;
    static int remain;

    public static void crush(int r, int c, int[][] map){
        Queue<int[]> qu = new LinkedList<>();
        qu.add(new int[]{r,c});
        while(!qu.isEmpty()){
            int[] cur = qu.poll();
            int sr = cur[0];
            int sc = cur[1];
            int len = map[sr][sc];
            for (int i = 0; i < len; i++) {
                map[sr][sc] = 0;
                for (int j = 0; j < 4; j++) {
                    int nr = sr + dr[j]*i;
                    int nc = sc + dc[j]*i;
                    if(nr>=0&&nr<H&&nc>=0&&nc<W && map[nr][nc] != 0){
                        if(map[nr][nc] > 1){
                            qu.add(new int[]{nr,nc});
                            continue; //1보다 크면 len 알기위해 0으로 안만든다.
                        }
                        map[nr][nc] = 0;
                    }
                }
            }
        }
    }

    public static int[][] cpmap(){
        int[][] cpmap = new int[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                cpmap[i][j] = map[i][j];
            }
        }
        return cpmap;
    }

    public static int countremain(int[][] map){
        int count = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if(map[i][j] != 0){
                    count += 1;
                }
            }
        }
        return count;
    }

    public static void fall(int[][] map){ //내리기 고치기
        int[][] tempmap = new int[H][W];
        for (int j = W-1; j >= 0; j--) {
            Queue<Integer> fallq = new LinkedList<>();
            for (int i = H-1; i >= 0; i--) {
                if(map[i][j] != 0){
                    fallq.add(map[i][j]);
                }
            }
            int row = H-1;
            while(!fallq.isEmpty()){
                map[row--][j] = fallq.poll();
            }
        }
        map = tempmap; //내리기 완료
    }

    public static int start(){
        int[][] cpmap = cpmap();
        for (int i = 0; i < sel.length; i++) {
            int col = sel[i];
            for (int j = 0; j < H; j++) {
                if(cpmap[j][col] != 0){
                    crush(j,col, cpmap); //벽돌 깨고 내리기
                    fall(cpmap);
                    break;
                }
            }
        } //벽돌깨기 끝나고 남은 개수 확인하기
        return countremain(cpmap);
    }

    public static void comb(int idx, int start){
        if(idx == sel.length){
            remain = Math.min(remain, start());
            return;
        }
        for (int i = start; i < W; i++) {
            sel[idx] = arr[i];
            comb(idx+1, i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new int[H][W];

            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            arr = new int[W];
            for (int i = 0; i < W; i++) {
                arr[i] = i;
            }
            sel = new int[N];
            remain = Integer.MAX_VALUE;
            comb(0,0);
            System.out.printf("#%d %d\n", test_case, remain);
        }
    }
}
