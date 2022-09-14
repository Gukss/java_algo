package _202209013;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_1405_crazyrobot {
    static int num,E,W,N,S;
    static int[] dir;
    static double result;
    static int[] dr = {0, 0, 1, -1}; //동, 서, 남, 북
    static int[] dc = {1, -1, 0, 0};

    static boolean[][] map;

    public static void dfs(int depth, int r, int c, double per){
        if(depth == 0){ //종료 조건, 종료시 확률을 결과에 더해준다.
            result += per;
            return;
        }
        map[r][c] = true; //재귀 함수 들어와서 방문체크해 준다.
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(nr>=0&&nc>=0&&nr<map.length&&nc<map.length && !map[nr][nc]){ //경계조건, 방문하지 않은곳만 방문
                dfs(depth-1, nr, nc, per*dir[i]*0.01);
                map[nr][nc] = false; //재귀함수 빠져나오면 방문 취소해 준다.
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        dir = new int[4];
        num = Integer.parseInt(st.nextToken());
        map = new boolean[num*2+2][num*2+2];
        dir[0] = Integer.parseInt(st.nextToken());
        dir[1] = Integer.parseInt(st.nextToken());
        dir[2] = Integer.parseInt(st.nextToken());
        dir[3]  = Integer.parseInt(st.nextToken());
        result = 0;

        dfs(num, num,num, 1); //확률 1부터 시작해서 줄어들면서 재귀반복

        System.out.println(result);
    }
}
/*
3 25 25 25 25
ans: 0.5625
 */