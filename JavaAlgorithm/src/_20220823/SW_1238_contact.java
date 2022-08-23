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
            ArrayList[] list = new ArrayList[len+1];
            for (int i = 1; i < len+1; i++) {
                list[i] = new ArrayList<Integer>();
            }

            for (int i = 0; i < len/2; i++) {
                int from = sc.nextInt();
                int to = sc.nextInt();

                list[from].add(to);
            }
            Queue<int[]> qu = new LinkedList<>();
            boolean[] v = new boolean[len+1];
            qu.add(new int[] {s, 0});
            v[s] = true;
            PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {

                @Override
                public int compare(int[] o1, int[] o2) {
                    if(o2[1] == o1[1]) {
                        return Integer.compare(o2[0], o1[0]);
                    }
                    return Integer.compare(o2[1], o1[1]);
                }
            });

            while(!qu.isEmpty()) {
//				int[] cur = qu.poll();
//				pq.add(cur);
//				if(!v[cur[0]]) {
//					v[cur[0]] = true;
//					for (int i = 0; i < list[cur[0]].size(); i++) {
//						qu.add(new int[] {(int) list[cur[0]].get(i), cur[1]+1});
//					}
//				}
                int[] cur = qu.poll();
                pq.add(cur);
                for (int i = 0; i < list[cur[0]].size(); i++) {
                    if(!v[(int) list[cur[0]].get(i)]) {
                        v[(int) list[cur[0]].get(i)] = true;
                        qu.add(new int[] {(int) list[cur[0]].get(i), cur[1]+1});
                    }
                }
            }
            int[] result = pq.poll();
            System.out.printf("#%d %d\n", test_case, result[0]);
            /////////////////////////////////////////////////////////////////////////////////////////////
        }
    }

}