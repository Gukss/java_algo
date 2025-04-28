import java.util.*;

class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        //for (startday+i)
        //상품받을 배열, 총인원 만들어놓고 시작
        int num = timelogs.length;
        int answer = num;
        boolean[] v = new boolean[num];
        Arrays.fill(v,true);
        for (int i=0;i<7;i++) {
            // System.out.println("startdat: "+ startday);
            if (startday == 6 || startday == 7) {
                startday += 1;
                continue;
            }
            for(int j=0;j<num;j++) { //사람
                if (!v[j]) { //한 번 못받는 사람은 계속 못받음
                    continue;
                }
                int etime = schedules[j] / 100;
                int eminute = schedules[j] % 100 + 10;
                int stime = timelogs[j][i] / 100;
                int sminute = timelogs[j][i] % 100;
                if (eminute >= 60) {
                    etime += 1;
                    eminute -= 60;
                }
                // System.out.println("etime: "+etime+", eminute: "+eminute+", stime: "+stime+", sminute: "+sminute);
                if (etime == stime) {
                    if (eminute < sminute) {
                        v[j] = false; //지각
                        answer -= 1;
                    }
                } else if (etime < stime) {
                    v[j] = false; //지각
                    answer -= 1;
                }
                // System.out.println("answer: "+answer);
                // for(int k=0;k<num;k++) {
                //     System.out.print("v["+k+"]: "+ v[k]+", ");
                // }
                // System.out.println("");
            }
            startday = (startday + 1)%7; //요일
        }
        
        return answer;
    }
}