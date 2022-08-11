package day007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_17406_rotateMatrix4 {
    static int[] arr;
    static int[] sel;
    static boolean[] visited;
    static Queue<int[]> qu; //실행 순서 저장할 큐
    public static void perm(int idx){ //순열,
        if(idx == sel.length){
            int[] temp = new int[sel.length];
            System.arraycopy(sel, 0, temp, 0, sel.length);
            qu.add(temp);
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if(!visited[i]){
                visited[i] = true;
                sel[idx] = arr[i];
                perm(idx+1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        int[][] temp = new int[N][M]; //원본
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                temp[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = temp[i][j];
            }
        }

        arr = new int[K]; //0,1,2,3 담을 배열
        sel = new int[K]; //순열 저장할 배열
        visited = new boolean[K];
        qu = new LinkedList<>();

        int[][] pos = new int[K][3]; //좌표 저장
        for (int i = 0; i < K; i++) {
            arr[i] = i;

            st = new StringTokenizer(br.readLine());
            pos[i][0] = Integer.parseInt(st.nextToken());
            pos[i][1] = Integer.parseInt(st.nextToken());
            pos[i][2] = Integer.parseInt(st.nextToken());
        }
        perm(0);

        int[] dr = {1, 0, -1, 0}; //밑, 오, 위, 왼,
        int[] dc = {0, 1, 0, -1}; //밑, 오, 위, 왼,
        int result = Integer.MAX_VALUE;
        
        while(!qu.isEmpty()){
            map = new int[N][M]; //연산 순서 바꿀 때 마다 새로운 map 생성해서 돌리기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    map[i][j] = temp[i][j];
                }
            }

            int[] turn = qu.poll(); //turn에 연산 수행 순서 들어있다. 수행 순서 바꿔서 실행하기 ex[0,1,2,3], [3,2,1,0]
            for (int i = 0; i < turn.length; i++) { //연산 순서대로 수행하기
                int sr = pos[turn[i]][0] - pos[turn[i]][2]-1; //-1해서 배열 인덱스로 맞춰주기, 시작행
                int sc = pos[turn[i]][1] - pos[turn[i]][2]-1; //시작열
                int er = pos[turn[i]][0] + pos[turn[i]][2]-1; //끝행
                int ec = pos[turn[i]][1] + pos[turn[i]][2]-1; //끝열

                for (int j = 0; j < pos[turn[i]][2]; j++) { //층수
                    int d=0; //델타

                    int start = map[sr][sc]; //시작점 저장
                    int nr = sr; //바꿔줄 nr
                    int nc = sc; //바꿔줄 nc
                    while(d<4){
                        int xr = nr + dr[d]; //이동하기
                        int xc = nc + dc[d];
                        if(xr<sr || xc<sc||xr>er||xc>ec||(xr==sr && xc==sc)||xr<0||xc<0||xr>=N||xc>=M){ //아마 마지막에 0, N, M은 안해도 된다.
                            d++;
                            continue;
                        }
                        map[nr][nc] = map[xr][xc]; //이동
                        nr = xr;
                        nc = xc;
                    }
                    map[sr][sc+1] = start; //마지막 값 넣어주기

                    //j씩 빼는게 아니고 1 씩 빼줘야 한다.
                    sr = sr += 1;
                    sc = sc += 1;
                    er = er -= 1;
                    ec = ec -= 1;
                }
            }
            //돌리기 연산 끝나면 합 구하기
            for (int j = 0; j < N; j++) {
                int sum = 0;
                for (int k = 0; k < M; k++) {
                    sum += map[j][k];
                }
                result = Math.min(result, sum);
            }
        }
        System.out.println(result);
    }
}
