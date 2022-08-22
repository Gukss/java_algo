package _20220822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj_1197_MST {
    static int V,E;
    static int[] parents;
    static Edge[] edgeList;

    public static void make(){
        parents = new int[V+1];
        for (int i = 1; i <= V; i++) {
            parents[i] = i; //자기 자신을 부모로 설정
        }
    }

    public static int find(int a){
        if(parents[a] == a){ // 자기 자신이 부모면 true반환
            return a;
        }
        return parents[a] = find(parents[a]); //이 코드가 나타내는 것은?
    }

    public static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot){
            return false;
        }
        parents[bRoot] = aRoot; //b->a a가 부모가 된다.
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edgeList = new Edge[E];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            edgeList[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        make();
        Arrays.sort(edgeList);
        int count = 0;
        int sumWeight = 0;

        for(Edge edge: edgeList){
            if(union(edge.from, edge.to)){
                count += 1;
                sumWeight += edge.weight;
                if(count == V-1){
                    break;
                }
            }
        }
        System.out.println(sumWeight);
    }

    public static class Edge implements Comparable<Edge>{
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}
