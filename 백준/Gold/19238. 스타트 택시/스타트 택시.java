import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M;
    static int[][] map;
    static List<Pos> poslist;
    static PriorityQueue<Taxi> pq;
    static boolean[][] v;
    static int[] dr = {-1, 0, 0, 1}; //위, 왼, 오, 밑 => 행이 작은 승객 먼저 선택한다. => 위 먼저
    static int[] dc = {0, -1, 1, 0}; //위, 왼, 오, 밑 => 행이 작은 승객 먼저 선택한다.
    static Queue<Taxi> qu;

    public static void movetogoalbfs(int r,int c,int f,int idx){
        qu = new ArrayDeque<>();
        qu.add(new Taxi(r,c,f,0));
        v = new boolean[N][N];
        v[r][c] = true;
        while(!qu.isEmpty()){
            Taxi cur = qu.poll();
            int sr = cur.r;
            int sc = cur.c;
            int fuel = cur.fuel;
            int movelen = cur.movelen;
            if(fuel < 0){ //연료 다 떨어지면
                System.out.println(-1);
                System.exit(0);
            }
            Pos goalpos = poslist.get(idx);
            if(sr==goalpos.gr && sc==goalpos.gc){ //목표지점 도착
                poslist.remove(idx); //목표지점에서 제거
                if(poslist.isEmpty()){ //list가 비었으면 완료
                    System.out.println(fuel+(2*movelen));
                    System.exit(0);
                }
                searchpersonbfs(sr,sc,fuel+(2*movelen));
            }
            for (int i = 0; i < 4; i++) {
                int nr = sr + dr[i];
                int nc = sc + dc[i];
                if(nr>=0&&nr<N&&nc>=0&&nc<N && map[nr][nc] != -1 && !v[nr][nc]){
                    v[nr][nc] = true;
                    qu.add(new Taxi(nr,nc,fuel-1, movelen+1));
                }
            }
        }
    }

    public static void searchpersonbfs(int r, int c, int f){
        pq = new PriorityQueue<>(new Comparator<Taxi>() {
            @Override
            public int compare(Taxi o1, Taxi o2) {
                if(o1.movelen == o2.movelen){
                    if(o1.r == o2.r){
                        return o1.c-o2.c;
                    }else{
                        return o1.r - o2.r;
                    }
                }else{
                    return o1.movelen - o2.movelen;
                }
            }
        });
        pq.add(new Taxi(r, c,f,0));
        v = new boolean[N][N];
        v[r][c] = true;

        while(!pq.isEmpty()){
            Taxi cur = pq.poll();
            int sr = cur.r;
            int sc = cur.c;
            int fuel = cur.fuel;
            int movelen = cur.movelen;
            if(fuel < 0){ //연료 다 떨어지면
                System.out.println(-1);
                System.exit(0);
            }
            if(poslist.size()==0){ //list가 비었으면 완료
                System.out.println(fuel);
                System.exit(0);
            }
            for (int i = 0; i < poslist.size(); i++) {
                Pos temp = poslist.get(i);
                if(temp.sr==sr && temp.sc==sc){
                    movetogoalbfs(sr,sc,fuel,i);
                }
            }
            for (int i = 0; i < 4; i++) {
                int nr = sr + dr[i];
                int nc = sc + dc[i];
                if(nr>=0&&nr<N&&nc>=0&&nc<N && map[nr][nc] != -1 && !v[nr][nc]){ //경계, 벽 아닌, 방문하지않은
                    v[nr][nc] = true;
                    pq.add(new Taxi(nr,nc,fuel-1, movelen+1));
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //map크기
        M = Integer.parseInt(st.nextToken()); //승객 개수
        int fuel = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken())*-1; //벽 -1
            }
        }
        st = new StringTokenizer(br.readLine());
        int startr = Integer.parseInt(st.nextToken())-1; //index 보정
        int startc = Integer.parseInt(st.nextToken())-1;

        poslist = new LinkedList<>(); //list에 시작점, 목적지 좌표 넣어놓고 도착지 확인할 때 사용한다.
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int sr = Integer.parseInt(st.nextToken())-1;
            int sc = Integer.parseInt(st.nextToken())-1;
            int gr = Integer.parseInt(st.nextToken())-1;
            int gc = Integer.parseInt(st.nextToken())-1;
            if(!(sr==gr && sc==gc)){
                poslist.add(new Pos(sr,sc,gr,gc));
            }
        }
        searchpersonbfs(startr, startc, fuel);
        System.out.println(-1);
    }

    public static class Pos{
        int sr,sc,gr,gc;

        public Pos(int sr, int sc, int gr, int gc) {
            this.sr = sr;
            this.sc = sc;
            this.gr = gr;
            this.gc = gc;
        }
    }

    public static class Taxi{
        int r,c,fuel,movelen;

        public Taxi(int r, int c, int fuel, int movelen) {
            this.r = r;
            this.c = c;
            this.fuel = fuel;
            this.movelen = movelen;
        }
    }
}
//4시간
/*
7 15 9
0 0 0 1 0 0 0
0 0 0 0 1 0 0
0 0 0 0 0 0 0
0 0 1 0 0 0 0
0 1 0 0 1 0 0
0 0 0 1 0 0 0
0 0 0 0 0 0 0
5 6
1 2 2 3
7 3 5 7
3 3 5 6
6 6 3 3
5 6 5 7
4 5 7 3
2 2 3 6
4 4 2 2
7 4 4 7
1 5 6 1
6 2 4 1
1 7 6 1
3 4 5 7
4 2 1 5
4 1 6 3
ans: 57

6 1 2
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
3 4
3 4 3 4
ans: 2

5 5 4
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
3 3
2 2 4 2
4 2 4 4
4 4 2 4
2 4 2 2
2 5 3 3
ans: 10
 */