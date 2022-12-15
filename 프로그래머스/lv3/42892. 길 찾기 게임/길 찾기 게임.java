import java.util.*;

class Solution {
    //이진트리, 전,후위순회=>하기위해서 재귀를 해야한다.
    //트리를 구성하기 위해 y좌표 오름차정렬하고, x좌표로 분류해야한다.
    
    //입력을 y로 오름차정렬, x도 오름차정렬
    //루트노드 만든다.
    //남은 노드를 하나씩 트리에 넣으면서 x를 기준으로 curNode를 바꿔가면서 left, right에 붙인다.
    
    public static PriorityQueue<Pos> pq; //정렬을 위한 pq, 어차피 반복문 돌면서 x,y,k를 입력해야한다.
    public static Pos tree;
    public static int[][] result;
    public static int size;
    public static int idx;
    
    public static void init(){ //좌, 우는 null로 초기화, center는 가장 위에 노드로 한다.
        Pos root = pq.poll();
        tree = root;
    }
    
    public static void makeTree(Pos root, Pos son){
        if(son.x < root.x){ //왼쪽
            if(root.left == null){ //비어있을 때
                root.left =  son; //왼쪽에 노드를 넣어주고 반복종료
            }else{ //왼쪽에 노드가 있으면 왼쪽을 root로 해서 재귀호출 => left or right가 비어있을 때 재귀가 종료된다. 
                makeTree(root.left, son);
            }
        }else{ //오른쪽
            if(root.right == null){ //비어있을 때
                root.right = son; //왼쪽에 노드를 넣어주고 반복종료
            }else{ //왼쪽에 노드가 있으면 왼쪽을 root로 해서 재귀호출
                makeTree(root.right, son);
            }
        }
    }
    
    public static void preorder(Pos cur){
        if(cur != null){
            result[0][idx++] = cur.k;
            preorder(cur.left);
            preorder(cur.right);
        }
    }
    
    public static void postorder(Pos cur){
        if(cur != null){
            postorder(cur.left);
            postorder(cur.right);
            result[1][idx++] = cur.k;
        }
    }
    
    public int[][] solution(int[][] nodeinfo) {
        pq = new PriorityQueue<>(new Comparator<>(){ //정렬을 위한 pq, 어차피 반복문 돌면서 x,y,k를 입력해야한다.
            public int compare(Pos o1, Pos o2){
                if(o1.y == o2.y){
                    return o1.x - o2.x;
                }else{
                    return o2.y - o1.y;
                }    
            } 
        });
        
        for(int i=0;i<nodeinfo.length;i++){ //반복문 돌면서 pq에 노드 넣어주기(정렬)
            pq.add(new Pos(nodeinfo[i][0], nodeinfo[i][1], i+1));
        }
        size = pq.size(); //전체 노드 개수
        result = new int[2][size]; //결과를 저장할 전역변수
        
        init(); //트리 초기화 => 루트노드 넣기
        while(!pq.isEmpty()){ //pq가 빌 때 까지 반복하면서
            Pos son = pq.poll();
            makeTree(tree, son); //트리 만들기 => 남은 노드 반복하면서 트리를 만든다.        
        }
        
        idx = 0;
        preorder(tree);
        idx=0;
        postorder(tree);
        
        int[][] answer = {};
        answer = result;
        return answer;
    }
    
    public static class Pos{ //좌표와 노드 번호 저장
        int x,y,k;
        Pos left, right;
        public Pos(int x,int y,int k){
            this.x = x;
            this.y = y;
            this.k = k;
            this.left = null;
            this.right = null;
        }
    }
}