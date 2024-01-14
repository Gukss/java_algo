import java.util.*;

class Solution {
    /*
    map으로 <차량번호, 입차 시각> 을 저장한다.
    시각은 23:59까지기 때문에 모두 분으로 바꿔서 저장한다.
    출력할 때 차량 번호가 작은 자동차부터 청구 요금을 출력해야한다. => 정렬을 위해 pq를 써서 클래스로 넣자
    출차가 나오면 시간을 비교해서 요금을 계산한다.
    같은 번호의 차가 여러대 나올 수 있기 때문에 timemap에 누적 시간을 저장한다.
    */
    public static int[] Fees;
    public static String[] Records;
    public static Map<String, Integer> inmap;
    public static Map<String, Integer> timemap;
    public static PriorityQueue<Pos> pq;
    
    public int[] solution(int[] fees, String[] records) {
        inmap = new HashMap<>();
        timemap = new HashMap<>();
        for(String x: records){
            StringTokenizer st = new StringTokenizer(x, " :");
            int h = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            String num = st.nextToken();
            String io = st.nextToken();
            // System.out.println("h: "+h+", s:"+s);
            int time = h*60+s;
            if(io.equals("IN")){
                inmap.put(num, time);
            }else{
                int intime = inmap.remove(num);
                int sumtime = time - intime;
                if(timemap.containsKey(num)){ //이미 들어있는 경우
                    timemap.put(num, timemap.get(num)+sumtime);
                }else{
                    timemap.put(num, sumtime);
                }
            }
        }
        //map이 비어있지 않을 때 23:59출차로 계산하기
        if(!inmap.isEmpty()){
            for(String x: inmap.keySet()){
                int intime = inmap.get(x);
                int sumtime = 23*60+59 - intime;
                if(timemap.containsKey(x)){ //이미 들어있는 경우
                    timemap.put(x, timemap.get(x)+sumtime);
                }else{
                    timemap.put(x, sumtime);
                }
            }
        }
        pq = new PriorityQueue<Pos>(new Comparator<Pos>(){
            public int compare(Pos o1, Pos o2){
                return Integer.parseInt(o1.num) - Integer.parseInt(o2.num);
            }
        });
        for(String x: timemap.keySet()){
            // System.out.println(x);
            int time = timemap.get(x);
            int price = fees[1];
            time-=fees[0];
            if(time>0){
                price += (time/fees[2]) * fees[3];
                if(time%fees[2] != 0){
                    price += fees[3];
                }
            }
            pq.add(new Pos(x, price));
        }
        int size = pq.size();
        int[] answer = new int[pq.size()];
        for(int i=0;i<size;i++){
            Pos x = pq.poll();
            answer[i] = x.p;
        }
        return answer;
    }
    
    public static class Pos{
        String num;
        int p;
        public Pos(String num, int p){
            this.num = num;
            this.p = p;
        }
    }
}