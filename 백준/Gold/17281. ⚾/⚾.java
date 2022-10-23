import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,result,totalresult;
    static int[][] map;
    static boolean[] v;
    static int[] sel;
    static int turn;

    public static void checkscore(int innings){
        int out = 0;
        int[] base = new int[3]; //초기 1,2,3루 비워놓는다.
        Arrays.fill(base, -1);
//        boolean[] runner = new boolean[9];
        while(out < 3){
            if(turn == 9){
                turn -= 9;
            }
//            if(runner[turn]){
//                turn += 1;
//                continue;
//            }
            int power = map[innings][sel[turn]];
            if(power == 1){ //안타
                for (int i = 2; i >= 0; i--) {
                    if(base[i] != -1){ //주자있는 경우
                        if(i==2){ //3루에 주자 있는 경우
//                            runner[base[i]] = false;
                            base[i] = -1;
                            result += 1;
                        }else{
                            base[i] = -1;
                            base[i+1] = turn; //진루
                        }
                    }
                }
                base[0] = turn;
//                runner[turn] = true;
            }else if(power == 2){ //2루타
                for (int i = 2; i >= 0; i--) {
                    if(base[i] != -1){
                        if(i==1 || i==2){
//                            runner[base[i]] = true;
                            base[i] = -1;
                            result += 1;
                        }else{
                            base[i] = -1;
                            base[i+2] = turn; //2루타
                        }
                    }
                }
                base[1] = turn;
//                runner[turn] = true;
            }else if(power == 3){ //3루타
                for (int i = 2; i >= 0; i--) {
                    if(base[i] != -1){
                        result += 1;
//                        runner[base[i]] = false;
                        base[i] = -1;
                    }
                }
                base[2] = turn;
//                runner[turn] = true;
            }else if(power == 4){ //홈런
                for (int i = 2; i >= 0; i--) {
                    if(base[i] != -1){
                        result += 1;
//                        runner[base[i]] = false;
                        base[i] = -1;
                    }
                }
                result += 1;
            }else if(power == 0){ //아웃
                out += 1;
            }
            turn += 1;
            if(out == 3){
                break;
            }
        }
    }

    public static void perm(int idx){
        if(idx == map[0].length){
            result = 0;
            turn = 0;
            for (int i = 0; i < N; i++) {
                checkscore(i);
            }
            totalresult = Math.max(totalresult, result);
            return;
        }
        for (int i = 0; i < map[0].length; i++) {
            if(v[i]) continue;
//            if(idx==3){ //4번 타자는 항상 0으로 고정
//                perm(idx+1);
//            }else{
                v[i] = true;
                sel[i] = idx;
                perm(idx+1);
//            }
//            if(i==0){ //0은 항상 4번에 들어가있기 때문에 방문처리 x
//                continue;
//            }else{
                v[i] = false;
//            }
        }
    }

    public static void start(){
        v = new boolean[9];
        v[3] = true;
        sel = new int[9];
        sel[3] = 0;
        perm(1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][9];
        turn = 0;
        totalresult = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        start();

        System.out.println(totalresult);
    }
}
