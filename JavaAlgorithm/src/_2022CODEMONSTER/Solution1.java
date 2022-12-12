package _2022CODEMONSTER;

public class Solution1 {
    static int[] marbles;
    static int[] sel;
    static boolean[] v;
    static int diffleftright;
    static double totalscore;
    static int weight;
    static int turn;
    static int[] answer;

    public static void checkleftrightsame(){
        int left = 0;
        int right = sel.length-1;
        int value = sel[left] - sel[right];
        while(left > right){
            if(value == 0){
                left+=1;
                right-=1;
                value = sel[left]-sel[right];
            }else if(value < 0){ //right가 큰 경우
                left+=1;
                value = sel[left] + value;
            }else if(value > 0){ //left가 큰 경우
                right -= 1;
                value = value + sel[right];
            }
        }
        diffleftright = value;
    }

    public static void checkweight(){
        for (int i = 0; i < sel.length; i++) {
            weight += sel[i];
        }
    }

    public static void checkresult(double curscore){
        int[] temp = new int[sel.length];
        if(totalscore < curscore){
            totalscore = curscore;
            for (int i = 0; i < sel.length; i++) {
                temp[i] = sel[i];
            }
        }else if(totalscore == curscore){
            if(answer.length > sel.length){ //짧은게 이김
                for (int i = 0; i < sel.length; i++) {
                    temp[i] = sel[i];
                }
            }else if(answer.length < sel.length){
                for (int i = 0; i < sel.length; i++) {
                    temp[i] = answer[i];
                }
            }else{//같을 때
                for (int i = 0; i < sel.length; i++) {
                    if(answer[i] > sel[i]){ //크면 진다.
                        temp[i] = sel[i];
                    }else{
                        temp[i] = answer[i];
                    }
                }
            }
        }
        answer = temp;
    }

    public static void perm(int idx, int len){
        if(idx == len){
            checkleftrightsame();
            checkweight();
//            totalscore = len-diffleftright+weight;
            checkresult((len/marbles.length)-diffleftright+weight);
            return;
        }
        for (int i = 0; i < len; i++) {
            if(v[idx]) continue;
            v[idx] = true;
            sel[idx] = i;
            perm(idx+1, len);
            v[idx] = false;
        }

    }

    public static void main(String[] args) {
        marbles = new int[] {1,2,3,4,4};
        for (int i = 1; i <= marbles.length; i++) { //구슬 개수만큼 뽑기
            v = new boolean[i];
            sel = new int[i];
            diffleftright=0;
            totalscore=0;
            weight=0;
            perm(0, i);
        }
    }
}
