package _20220823;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class SW_1238_contact {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int T;
        T=10;

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int len = sc.nextInt();
            int s = sc.nextInt();
            /////////////////////////////////////////////////////////////////////////////////////////////
            ArrayList[] list = new ArrayList[len+1]; //ArrayList배열 초기화
            for (int i = 1; i < len+1; i++) {
                list[i] = new ArrayList<Integer>();
            }

            for (int i = 0; i < len/2; i++) { //인접 리스트 생성
                int from = sc.nextInt();
                int to = sc.nextInt();

                list[from].add(to);
            }
            Queue<int[]> qu = new LinkedList<>();
            boolean[] v = new boolean[len+1];

            qu.add(new int[] {s, 0}); //처음 값 큐에 넣고
            v[s] = true; //방문처리
            PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() { //정렬을 위한 우선순위 큐
                @Override
                public int compare(int[] o1, int[] o2) {
                    if(o2[1] == o1[1]) {
                        return Integer.compare(o2[0], o1[0]);
                    }
                    return Integer.compare(o2[1], o1[1]);
                }
            });

            while(!qu.isEmpty()) {
                int[] cur = qu.poll();
                pq.add(cur); //정렬
                for (int i = 0; i < list[cur[0]].size(); i++) { //시작점 리스트 개수만큼 반복
                    if(!v[(int) list[cur[0]].get(i)]) { //시작점에서 한 개씩 받아서 방문 처리
                        v[(int) list[cur[0]].get(i)] = true;
                        qu.add(new int[] {(int) list[cur[0]].get(i), cur[1]+1}); //큐에 넣기
                    }
                }
            }
            int[] result = pq.poll();
            System.out.printf("#%d %d\n", test_case, result[0]);
            /////////////////////////////////////////////////////////////////////////////////////////////
        }
    }

}