import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int[][] map;
    static Queue<Pos> qu;
    static boolean[][] v;
    static int[] dr = {-1, 0, 1, 0}; //위, 오, 밑, 왼
    static int[] dc = {0, 1, 0, -1}; //위, 오, 밑, 왼
    static int resulttime, resultcheeze;

    public static void start(){
        //X에서 시작해서 bfs, 0이면 qu에 넣고, 1이면 tempmap에 체크해서 뺄 수 있도록한다.
        qu = new LinkedList<>();
        v = new boolean[N][M];
        qu.add(new Pos(0,0));
        v[0][0] = true;
        int[][] tempmap = new int[N][M];

        while(!qu.isEmpty()){
            Pos cur = qu.poll();
            int sr = cur.r;
            int sc = cur.c;
            for (int i = 0; i < 4; i++) {
                int nr = sr + dr[i];
                int nc = sc + dc[i];
                if(nr>=0&&nr<N&&nc>=0&&nc<M && !v[nr][nc]){
                    if(map[nr][nc] == 0){ //0일 때 => qu에 저장
                        qu.add(new Pos(nr, nc));
                        v[nr][nc] = true;
                    }else if(map[nr][nc] == 1){ //1이면 공기랑 맞다은 곳이다.
                        v[nr][nc] = true; //방문처리하고,
                        tempmap[nr][nc] = 1; //tempmap에 1로 만들어준다. => while 끝나면 map에서 빼주기
                    }
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] -= tempmap[i][j];
            }
        }
    }

    public static boolean mapIsEmpty(){
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 1){
                    count += 1;
                }
            }
        }
        if(count == 0){
            return true;
        }else{
            resultcheeze = Math.min(resultcheeze, count);
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        resultcheeze = 0;
        resulttime = 0;
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){
                    resultcheeze += 1;
                }
            }
        }

        do{
            start();
            resulttime += 1;
        }while(!mapIsEmpty());
        System.out.println(resulttime);
        System.out.println(resultcheeze);
    }

    public static class Pos{
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
//30분
/*
5 5
0 0 0 0 0
0 1 1 0 0
0 1 0 1 0
0 1 1 1 0
0 0 0 0 0
ans: 7
 */