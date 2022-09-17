package _20220916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Bj_1043_lier {
    static int N,M,tnum;
    static ArrayList<Integer> tpeople;
    static boolean[] v;

    static ArrayList<Integer>[] list;

    public static void dfs(int idx){
        if(idx == M){
            return;
        }
        for (int i = 1; i <= M; i++) { //list 개수만큼 반복
            for (int j = 0; j < list[i-1].size(); j++) { //list안의 요소만큼 반복
                if(!v[i]){
                    for (int k = 0; k < tpeople.size(); k++) { //진실을 아는 사람 수 만큼 반복
                        if(tpeople.get(k) == list[i-1].get(j)){ //list안의 요소와 진실을 아는 사람이랑 같으면
                            v[i] = true; // 진실을 아는 파티 true
//                            tpeople.add(list[i-1].get(j));
                        }
                    }
                }
            }
        }
        for (int i = 1; i <= M; i++) {
            if(v[i]) { //진실을 아는 파티면
                for (int j = 0; j < list[i-1].size(); j++) { //파티원들이 속한 파티 true로 만들기
                    int p = list[i-1].get(j);
                    for (int k = 1; k <= M; k++) {
                        for (int l = 0; l < list[k-1].size(); l++) {
                            if(list[k-1].get(l) == p){
                                v[k]=true;
                            }
                        }
                    }
                }
            }
        }
        dfs(idx+1);
        return;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //사람 수
        M = Integer.parseInt(st.nextToken()); //파티의 수
        v = new boolean[M+1]; //진실을 아는 파티 구분

        st = new StringTokenizer(br.readLine());
        tnum = Integer.parseInt(st.nextToken());
        tpeople = new ArrayList<>();

        for (int i = 0; i < tnum; i++) {
            tpeople.add(Integer.parseInt(st.nextToken())); //진실을 아는 사람 번호
        }

        list = new ArrayList[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            list[i] = new ArrayList<>();
            int temp = Integer.parseInt(st.nextToken());
            for (int j = 0; j < temp; j++) {
                list[i].add(Integer.parseInt(st.nextToken()));
            }
        }
        if(tpeople.size() == 0){
            System.out.println(M);
        }else{
            dfs(0);
            int result = 0;
            for (int i = 1; i <= M; i++) {
                if(!v[i]) result +=1;
            }
            System.out.println(result);
        }
    }
}
