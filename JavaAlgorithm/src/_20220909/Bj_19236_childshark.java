package _20220909;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj_19236_childshark {
    static Sk[][] map;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1}; //위, 왼위, 왼, 왼밑, 밑, 오른밑, 오른, 오른위
    static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1}; //위, 왼위, 왼, 왼밑, 밑, 오른밑, 오른, 오른위
    static ArrayList<Sk> list;

    public static void start(){
        list.sort(new Comparator<Sk>() {
            @Override
            public int compare(Sk o1, Sk o2) {
                return o1.num - o2.num;
            }
        }); //정렬하면 첫번째는 항상 상어
        for (int i = 1; i < list.size(); i++) {
            Sk cur = list.get(i);
            int sr = cur.r;
            int sc = cur.c;
            for (int j = 0; j < 8; j++) {
                int d = (cur.dir + j ) % 8;
                int nr = sr + dr[d];
                int nc = sc + dc[d];
                if(nr>=0&&nc>=0&&nr<4&&nc<4 && (nr != list.get(0).r || nc != list.get(0).c) && map[nr][nc] != null) { //경계, 상어가 아닌곳, 물고기 없는곳은 null로 하기
                    Sk temp = map[sr][sc];
                    map[sr][sc] = map[nr][nc];
                    map[nr][nc] = temp;
//                    for (int k = 0; k < 4; k++) {
//                        for (int l = 0; l < 4; l++) {
//                            System.out.printf("%d ", map[k][l].num);
//                        }
//                        System.out.println();
//                    }
//                    System.out.println();
//                    System.out.println("-----------------");
                    break;
                }
            }
        } //물고기 이동 완료

        //상어 이동


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new Sk[4][4];
        list = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken())-1; //델타 인덱스로 0~7로 바꿔준다.
                Sk temp = new Sk(i,j,num,dir);
                list.add(temp);
                map[i][j] = temp;
            }
        }

        for (int i = 0; i < list.size(); i++) { //0,0에 해당하는 상어 리스트에서 빼기
            Sk temp = list.get(i);
            if(temp.r == 0 && temp.c == 0){
                list.remove(i);
                break;
            }
        }
        Sk temp = new Sk(0, 0, 0, map[0][0].dir);
        list.add(temp); //첫 상어 넣기
        map[0][0] = temp;
        start();
    }

    public static class Sk{
        int r,c,dir,num;

        public Sk(int r, int c, int num, int dir) {
            this.r = r;
            this.c = c;
            this.num = num;
            this.dir = dir;
        }
    }
}
