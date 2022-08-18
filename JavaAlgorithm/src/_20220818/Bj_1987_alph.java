package _20220818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_1987_alph {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static Queue<int[]> qu;
    static int[][] map;
    static boolean[] v;
    static int R, C;
    static int result;

    public static void dfs(int r, int c, int count){
        v[(int)(map[r][c] - 'A')] = true; //방문 표시
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr>=0&&nc>=0&&nr<R&&nc<C&& !v[(int)(map[nr][nc] - 'A')]){ //지나왔던 알파벳이 아니면 count++해서 dfs
                dfs(nr, nc, count+1);
            }
        }
        v[(int)(map[r][c] - 'A')] = false; //방문 표시 해제
        result = Math.max(result, count);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        qu = new LinkedList<>();

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            String temp = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = temp.charAt(j);
            }
        }
        v = new boolean[26];
        result = 1;

        dfs(0, 0, 1);

//        v[(int)(map[0][0] - 'A')] = true;
//        qu.add(new int[] {0,0});
//        while(!qu.isEmpty()){
//            int[] cur = qu.poll();
//            int sr = cur[0];
//            int sc = cur[1];
//            for (int i = 0; i < 4; i++) {
//                int nr = sr + dr[i];
//                int nc = sc + dc[i];
//                if(nr>=0&&nc>=0&&nr<R&&nc<C&& !v[(int)(map[nr][nc] - 'A')]){
//                    v[(int)(map[nr][nc] - 'A')] = true;
//                    qu.add(new int[] {nr, nc});
//                    result += 1;
//                }
//            }
//        }
        System.out.println(result);
    }
}
//40분
/* -> dfs문제다.
3 4
ABCD
BCDA
CDEF
 */