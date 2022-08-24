package _20220824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_3055_escape {
    static int[] dr = {0, 1, 0, -1}; //오밑왼위
    static int[] dc = {1, 0, -1, 0};
    static char[][] map;
    static Queue<int[]> spos;
    static Queue<int[]> wpos;
    static int R,C;
    static int result;
    static boolean[][] sv;

    public static void bfs() {
        for (int x = 0; x < R*C; x++) { //시간이 흐른다. 돌아갈 수 있기 때문에 넉넉하게 R*C 만큼 반복해 준다.
            result += 1;
            int wqsize = wpos.size();
            for(int i = 0; i<wqsize;i++) { //큐 사이즈 만큼 반복
                int[] wcur = wpos.poll();
                int wsr = wcur[0];
                int wsc = wcur[1];
                for (int j = 0; j < 4; j++) {
                    int wnr = wsr + dr[j];
                    int wnc = wsc + dc[j];
                    if(wnr>=0 && wnc>=0 && wnr<R && wnc<C && map[wnr][wnc] != 'X' && map[wnr][wnc] != 'D' && map[wnr][wnc] != '*') { //경계, 돌 아니면
                        map[wnr][wnc] = '*'; //물로 바꾸기
                        wpos.add(new int[] {wnr, wnc}); //물로 바뀐 자리 넣어주기
                    }
                }
            }
            //고슴 옮기기
            int sqsize = spos.size();
            for (int i = 0; i < sqsize; i++) {
                int[] scur = spos.poll();
                if(map[scur[0]][scur[1]] == 'D') {
                    return;
                }
                int ssr = scur[0];
                int ssc = scur[1];
                for (int j = 0; j < 4; j++) {
                    int snr = ssr + dr[j];
                    int snc = ssc + dc[j];
                    if(snr>=0 && snc>=0 && snr<R && snc <C && map[snr][snc] != 'X' && map[snr][snc] != '*' && !sv[snr][snc]) {
                        sv[snr][snc] = true;
                        spos.add(new int[] {snr, snc});
                    }
                }
            }


        }
    }

    public static void main(String[] args) throws IOException {
        //0901시작~1027끝
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        spos = new LinkedList<>(); //고슴도치 큐
        wpos = new LinkedList<>(); //물 큐
        sv = new boolean[R][C]; //고슴도치 visited, 물은 필요없다. map에 표시할꺼다.

        for (int i = 0; i < R; i++) {
            String temp = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = temp.charAt(j);
                if(map[i][j] == 'S') {
                    spos.add(new int[] {i, j});
                    sv[i][j] = true;
                }
                if(map[i][j] == '*') {
                    wpos.add(new int[] {i, j});
                }
            }
        }
        result = -1;
        bfs();

        System.out.println(result != R*C-1 ? result : "KAKTUS");
    }
}

//종료조건을 어떻게 설정해야 할지 어렵다.
//처음에 물은 bfs, 고슴도치는 dfs로 탐색하려 했다. 잘못된 접근 -> dfs하면 map도 상태를 보존해서 인자로 넘겨야 하고, 복잡해 진다.
//큐를 두 개 사용해서 물 따로, 고슴도치 따로 탐색시키면 된다. 탐색 순서가 중요하다.