package _20220815;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_2178_maze {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        Queue<int[]> qu = new LinkedList<>();
        boolean[][] v = new boolean[N][M];
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};


        for (int i = 0; i < N; i++) {
            String temp = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = temp.charAt(j) - '0'; //붙어서 입력되는 수 분리해서 저장
            }
        }

        qu.add(new int[] {0,0, 1}); //시작점 큐에 넣기
        v[0][0] = true;
        while(!qu.isEmpty()){
            int[] pos = qu.poll();
            int sr = pos[0];
            int sc = pos[1];
            for (int i = 0; i < 4; i++) {
                int nr = sr + dr[i];
                int nc = sc + dc[i];
                if(nr>=0&&nc>=0&&nr<N&&nc<M&&!v[nr][nc]&&map[nr][nc]==1){ //범위를 벗어나지 않고 방문하지 않았고 1인 곳 방문하기
                    v[nr][nc] = true;
                    if(nr==N-1 && nc==M-1){ //종료 조건
                        System.out.println(pos[2]+1);
                        return;
                    }
                    qu.add(new int[] {nr, nc, pos[2]+1}); //방문 처리하고 이동한 좌표와 누적 거리 큐에 넣기
                }
            }
        }
    }
}

//20분
