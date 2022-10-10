import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //노드 수
        int M = Integer.parseInt(st.nextToken()); //간선 수

        Node[] list = new Node[N+1];

        for (int i = 0; i < M+1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[a] = new Node(b, c, list[a]);
            list[b] = new Node(a, c, list[b]);
        }

        int[] minEdge = new int[N+1];
        boolean[] v = new boolean[N+1];
        Arrays.fill(minEdge, Integer.MAX_VALUE); //최소값으로 만들기 위해 최대값으로 세팅

        minEdge[0] = -1;
        int minResult = 0;

        PriorityQueue<Node> minPq = new PriorityQueue<>((v1, v2) -> v1.w - v2.w);
        minPq.offer(new Node(0, minEdge[0], null));

        int mincount = 0;
        while(true){
            Node minNode = minPq.poll();
            if(v[minNode.to]) continue;

            v[minNode.to] = true;
            if(minNode.w == 0){
                minResult += 1;
            }
            //minResult += minNode.w;
            if(++mincount == N+1) break;

            for (Node temp = list[minNode.to]; temp != null ; temp = temp.next) {
                if(!v[temp.to] && minEdge[temp.to] > temp.w){
                    minEdge[temp.to] = temp.w;
                    minPq.offer(new Node(temp.to, minEdge[temp.to], null));
                }
            }
        }

        int[] maxEdge = new int[N+1];
        boolean[] maxv = new boolean[N+1];
        Arrays.fill(maxEdge, -1); //최소값으로 만들기 위해 최대값으로 세팅

        maxEdge[0] = -1;
        int maxResult = 0;

        PriorityQueue<Node> maxPq = new PriorityQueue<>((v1, v2) -> v2.w - v1.w);
        maxPq.offer(new Node(0, maxEdge[0], null));

        int maxcount = 0;
        while(true){
            Node maxNode = maxPq.poll();
            if(maxv[maxNode.to]) continue;

            maxv[maxNode.to] = true;
            if(maxNode.w == 0){
                maxResult += 1;
            }
            //maxResult += maxNode.w;
            if(++maxcount == N+1) break;

            for (Node temp = list[maxNode.to]; temp != null ; temp = temp.next) {
                if(!maxv[temp.to] && maxEdge[temp.to] < temp.w){
                    maxEdge[temp.to] = temp.w;
                    maxPq.offer(new Node(temp.to, maxEdge[temp.to], null));
                }
            }
        }

//        System.out.println(minResult);
//        System.out.println(maxResult);
        System.out.println((minResult*minResult) - (maxResult*maxResult));
    }

    public static class Node{
        int to,w;
        Node next;

        public Node(int to, int w, Node next) {
            this.to = to;
            this.w = w;
            this.next = next;
        }
    }
}
