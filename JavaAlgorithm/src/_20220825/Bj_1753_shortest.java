package _20220825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj_1753_shortest {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int start = Integer.parseInt(br.readLine());

        ArrayList<Node>[] list = new ArrayList[V+1];

        for (int i = 0; i < V+1; i++) {
            list[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[u].add(new Node(v, w));
//            list[v].add(new Node(u, w));
        }

        //거리저장, 방문처리
        int[] dist = new int[V+1];
        boolean[] v = new boolean[V+1];

        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.w - o2.w;
            }
        });
        dist[start] = 0;
        pq.offer(new Node(start, 0)); //시작점->시작점 => 자기자신이니까 w는 0

//        for(int i=0;i<V;i++){
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(v[cur.to]) continue; //스킵해도 되나? -> 안됨

            v[cur.to] = true;

            for(Node next: list[cur.to]){
                if(!v[next.to] && (dist[next.to] > dist[cur.to] + next.w)){ //cur: 경유할 노드 | next: 이동할 노드 | next.w-> 1에서 2로 가는 가중치 dist[cur.to]->1까지 오는 가중치(현재까지), dist[next.to] -> 2까지 가는 가중치(다음꺼 까지 바로)
                    dist[next.to] = dist[cur.to] + next.w;
                    pq.offer(new Node(next.to, dist[next.to]));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            sb.append(dist[i] != Integer.MAX_VALUE ? dist[i] : "INF");
            sb.append("\n");
        }
        System.out.print(sb);
    }

    public static class Node{
        int to, w;

        public Node(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }
}
