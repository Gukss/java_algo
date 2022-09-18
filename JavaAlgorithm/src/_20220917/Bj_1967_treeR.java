package _20220917;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Bj_1967_treeR {
    static int n;
    static ArrayList<Node>[] list;
    static int result;
    static ArrayList<Node>[] resultList;

    public static void dfs(int idx, int firstsun, int start, int sum) {
        if(list[idx].size() == 0){
            int size = resultList[start].size();
            for (int i = 0; i < size; i++) {
                if(resultList[start].get(i).e == firstsun){
                    if(resultList[start].get(i).w < sum){
                        resultList[start].remove(i);
                        resultList[start].add(new Node(firstsun, sum));
                        return;
                    }else{
                        return;
                    }
                }
            }
            resultList[start].add(new Node(firstsun, sum));
            return;
        }
        for (int i = 0; i < list[idx].size(); i++) {
            dfs(list[idx].get(i).e, firstsun, start, sum+list[idx].get(i).w);
        }
    }

    public static void main(String[] args) throws IOException {
        //트리의 노드는 1부터 시작
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        //트리는 인접리스트로 표현? 인접리스트, 행렬 ,간선 리스트 => 인접 리스트로 표현
        list = new ArrayList[n + 1]; //트리의 노드가 1부터 시작하기 때문에 맞춰주기 위해
        resultList = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            list[i] = new ArrayList<>();
            resultList[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[s].add(new Node(e, w));
//            list[e].add(new Node(s,w)); //양방향이어야 한다. nn 단방향인데 역방향이어야 한다.
        }
        //인자가 1개 들어있는 노드가 마지막 말단 노드다.
        //dfs로 w의 sum 누적해 가면서 말단에서 말단으로 이동한다.
        result = Integer.MIN_VALUE;
        for (int i = 1; i < n + 1; i++) {
            if (list[i].size() >= 1) { //제일 말단 노드 제외, 가지가 1개 이상인 노드만 시작
                for (int j = 0; j < list[i].size(); j++) {
                    dfs(list[i].get(j).e, list[i].get(j).e, i,  list[i].get(j).w);
                }
            }
        }

        for (int i = 0; i < resultList.length; i++) {
            for (int j = 0; j < resultList[i].size(); j++) {
                resultList[i].sort(new Comparator<Node>() {
                    @Override
                    public int compare(Node o1, Node o2) {
                        if(o2.w==o1.w){
                            return o2.e - o1.e;
                        }else{
                            return o2.w - o1.w;
                        }
                    }
                });

                if(resultList[i].size() >=2){
                    result = Math.max(result, resultList[i].get(0).w+resultList[i].get(1).w);
                }else{
                    result = Math.max(result, resultList[i].get(0).w);
                }
            }
        }
        if(result == Integer.MIN_VALUE){
            System.out.println(0);
        }else{
            System.out.println(result);
        }
    }
    public static class Node{
        int e,w;

        public Node(int e, int w) {
            this.e = e;
            this.w = w;
        }
    }

}

//3시간
/*
4
1 3 1
2 4 1
3 2 1
ans: 3

5
1 2 3
1 3 4
1 4 5
1 5 6
ans: 11
 */