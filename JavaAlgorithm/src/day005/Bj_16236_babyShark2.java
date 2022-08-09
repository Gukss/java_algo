package day005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj_16236_babyShark2 {

    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static Queue<Pos> qu = new LinkedList<>();
    static int N;
    static int eat;
    static int[] fish;
    static int result;
    static boolean[][] visited;
    static int min_dist;
    static List<Pos> dist_list;

    public static boolean remain(int n){
        for (int i = 1; i < n; i++) {
            if(fish[i] > 0){
                return true;
            }
        }
        return false;
    }
    public static void bfs(Pos pos){
        qu.add(pos);
        min_dist = -1;
        dist_list = new ArrayList<Pos>();
        visited = new boolean[N][N];
        while(!qu.isEmpty()){
            Pos cur = qu.poll();
            if(!remain(cur.ssize)){ //먹을 수 있는 먹이가 없으면 종료
                result = cur.time;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if(nr<0 || nc < 0 || nr >= N || nc >= N || map[nr][nc] > cur.ssize || visited[nr][nc]){ //못가는 경우
                    continue;
                }
                if(map[nr][nc] < cur.ssize && map[nr][nc] > 0){ //물고기가 있고 먹을 수 있는 경우
                    cur.eat -= 1; //먹어야 하는 횟수 감소
                    fish[map[nr][nc]] -= 1;
                    map[nr][nc] = 0;
                    min_dist = cur.time+1;
                    dist_list.add(new Pos(nr, nc, cur.ssize, cur.eat, cur.time+1));
                    visited[nr][nc] = true;
                    if(cur.eat == 0){ //사이즈 증가
                        cur.ssize += 1;
                        cur.eat = cur.ssize;
                    }
                }
                if(map[nr][nc] >= 0){ //이동할 수 있는 경우
                    cur.time += 1;
                    cur.r = nr;
                    cur.c = nc;
                    qu.add(cur);
                    
                }
            }
        }
        //dist_list.sort();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        Pos pos = new Pos(0, 0, 2, 2, 0);
        fish = new int[10];
        result = -1;


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()); //맵 입력
                fish[map[i][j]] += 1; //남은 물고기 수 저장
                if(map[i][j] == 9){
                    pos = new Pos(i, j, 2, 2, 0); //현재 아기상어의 위치 저장
                    map[i][j] = 0;
                }
            }
        }

        while(true){

        }
        //bfs(pos);
        //System.out.println();
    }
    public static class Pos implements Comparable<Pos>{
        int r;
        int c; //좌표
        int ssize; //상어 크기
        int eat; //크기가 커지기 까지 먹어야 하는 물고기
        int time; //이동 횟수
        public Pos(int r, int c, int ssize, int eat, int time){
            this.r = r;
            this.c = c;
            this.ssize = ssize;
            this.eat = eat;
            this.time = time;
        }

        @Override
        public int compareTo(Pos o) {
            if(this.c - o.c == 0){
                return this.r - o.r;
            }else{
                return this.c - o.c;
            }
        }
    }
}
