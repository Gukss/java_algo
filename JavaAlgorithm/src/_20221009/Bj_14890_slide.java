package _20221009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_14890_slide {
    static int N,L;
    static int[][] map;
    static boolean[][] rowslide;
    static boolean[][] colslide;
    static int result;

    public static boolean putonrowplus(int r, int c, int count){
        if(count == L){
            return true;
        }
        if(r>=0&&r<N&&c+count>=0&&c+count<N){
            if(map[r][c+count] == map[r][c-1]-1){
                rowslide[r][c+count] = true;
                if(putonrowplus(r,c,count+1)){
                    return true;
                }else{
                    rowslide[r][c+count] = false;
                }
            }
        }
        return false;
    }

    public static boolean putonrowminus(int r, int c, int count){
        if(count == L){
            return true;
        }
        if(r>=0&&r<N&&c-count>=0&&c-count<N){
            if(map[r][c-count] == map[r][c+1]-1){
                rowslide[r][c-count] = true;
                if(putonrowminus(r,c,count+1)){
                    return true;
                }else{
                    rowslide[r][c+count] = false;
                }
            }
        }
        return false;
    }

    public static boolean putoncolplus(int r, int c, int count){
        if(count == L){
            return true;
        }
        if(r+count>=0&&r+count<N&&c>=0&&c<N){
            if(map[r+count][c] == map[r-1][c]-1){
                colslide[r+count][c] = true;
                if(putoncolplus(r,c,count+1)){
                    return true;
                }else{
                    colslide[r+count][c] = false;
                }
            }
        }
        return false;
    }

    public static boolean putoncolminus(int r, int c, int count){
        if(count == L){
            return true;
        }
        if(r>=0&&r<N&&c-count>=0&&c-count<N){
            if(map[r-count][c] == map[r+1][c]-1){
                colslide[r-count][c] = true;
                if(putoncolminus(r,c,count+1)){
                    return true;
                }else{
                    colslide[r-count][c] = false;
                }
            }
        }
        return false;
    }

    public static void start(){
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N; j++) {
                if(map[i][j-1]-map[i][j] == 1){ //행, 정방향, 차이가 1이 나면 경사로 놓을 수 있는지 확인
                    if(!rowslide[i][j]){ //경사로가 안깔려있고,
                        if(putonrowplus(i,j,0)){
                            result += 1;
                        }
                    }
                }
                if(map[N-i-1][N-j] - map[N-i-1][N-j-1] == 1){ //행, 역방향
                    if(!rowslide[N-i-1][N-j-1]){
                        if(putonrowminus(N-i-1,N-j-1,0)){
                            result += 1;
                        }
                    }
                }
                if(map[j-1][i] - map[j][i] == 1){ //열, 정방향
                    if(!rowslide[j][i]){
                        if(putoncolplus(j,i,0)){
                            result+=1;
                        }
                    }
                }
                if(map[N-j][N-i-1] - map[N-j-1][N-i-1] == 1){ //열, 역방항
                    if(!rowslide[N-j-1][N-i-1]){
                        if(putoncolminus(N-j-1,N-i-1,0)){
                            result += 1;
                        }
                    }
                }
            }
        }
    }

    public static void checkroad(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == map[i][j]){ //행방향, true면 넘어가고, 숫자가 같으면 true로 만들기

                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        rowslide = new boolean[N][N];
        colslide = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = 0;
        start();
        checkroad();
        System.out.println();
    }
}
