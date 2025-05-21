class Solution {
       
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        int len = cards1.length;
        int llen = cards2.length;
        int i = 0;
        int ii = 0;
        int flag = 0;
        for (String x: goal) {
            if (i != len && cards1[i].equals(x)) {
                i += 1;
            } else if (ii != llen && cards2[ii].equals(x)) {
                ii += 1;
            } else {
                break;
            }
            // System.out.println("i: "+i+", ii: "+ii);
            flag += 1;
        }
        String answer = "";
        if (flag == goal.length) {
            answer = "Yes";
        } else {
            answer = "No";
        }
        return answer;
    }
}