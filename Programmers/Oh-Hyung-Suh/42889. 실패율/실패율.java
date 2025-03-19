import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        
        int leftPeople = stages.length;
        
        List<Pair> pairs = new ArrayList<>(N);
        
        Map<Integer, Integer> challenge = new HashMap<>();
        
        // 1=1, 2=3, 3=2, 4=1, 6=1
        for(int stage : stages) {
            challenge.put(stage, challenge.getOrDefault(stage, 0) + 1);
        }
        
        // 1=0.125, 2=0.4285, 3=0.5, 4=0.5, 5=0.0
        for(int i = 1; i <= N; i++) {
            int challengePeople = challenge.getOrDefault(i, 0);
            double failRate = 0;
            
            if(leftPeople != 0) {
                failRate = (double) challengePeople / leftPeople;
            }
            
            pairs.add(new Pair(i, failRate));
            leftPeople -= challengePeople;
        }
        
        // Double 부분 gpt 참고...
        Collections.sort(pairs, (o1, o2) -> {
            if(o1.failRate == o2.failRate) {
                return o1.stage - o2.stage;
            }
            return Double.compare(o2.failRate, o1.failRate);
        });
            
        // for(int i = 0; i < N; i++) {
        //     System.out.println(pairs.get(i).stage + " " + pairs.get(i).failRate);
        // }
        
        for(int i = 0; i < N; i++) {
            answer[i] = pairs.get(i).stage;
        }
        
        return answer;
    }
    
    class Pair {
        int stage;
        double failRate;
        
        public Pair(int stage, double failRate) {
            this.stage = stage;
            this.failRate = failRate;
        }
    }
}