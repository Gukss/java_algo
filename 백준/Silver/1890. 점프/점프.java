import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static long[][] data;
    static int[] dr = {0, 1}; //이동할 수 있는게 오, 밑
    static int[] dc = {1, 0}; //이동할 수 있는게 오, 밑

    public static void start(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(i==N-1&&j==N-1) break; //map[N-1][N-1]이 0이기 때문에 경계조건에 걸리는게 없다. => 종료한다.
                if(data[i][j] == 0) continue;
                for (int k = 0; k < 2; k++) {
                    int nr = i + map[i][j] * dr[k];
                    int nc = j + map[i][j] * dc[k];
                    if(nr>=0&&nr<N&&nc>=0&&nc<N){
                        data[nr][nc] += data[i][j];
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        data = new long[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        data[0][0] = 1;
        start();
        System.out.println(data[N-1][N-1]);
    }
}
/*
4
1 1 1 1
1 1 1 1
1 1 1 1
1 1 1 0
ans: ?

9
3 1 2 2 3 3 1 1 2
1 1 2 1 1 2 3 1 2
2 1 1 3 2 2 1 3 1
3 3 1 1 1 3 1 2 1
3 2 2 2 1 1 3 3 1
3 1 3 2 2 3 1 3 3
3 1 1 2 1 1 1 1 1
2 3 1 3 1 3 2 2 2
3 3 3 2 3 1 3 3 0
ans: 6
 */