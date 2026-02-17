// 먼저 배포되어야 하는 순서대로 작업 진도가 적힌 정수 배열 progresses
// 각 작업 개발 속도 speeds
// 뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포
// 각 배포마다 몇 개의 기느이 배포?

import java.util.*;

class Solution {
    
    private final static int MAX_PROGRESS = 100;
    
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        int N = progresses.length;
        int[] leftProgresses = new int[N];
        
        for(int i = 0; i < N; i++) {
            leftProgresses[i] = (MAX_PROGRESS - progresses[i]) % speeds[i] == 0 ?
                (MAX_PROGRESS - progresses[i]) / speeds[i] :
            (MAX_PROGRESS - progresses[i]) / speeds[i] + 1;
        }
        
        int currentMaxProgress = leftProgresses[0];
        int cnt = 0;
        for(int p : leftProgresses) {
            if(currentMaxProgress >= p) {
                cnt++;
                continue;
            }
            
            answer.add(cnt);
            currentMaxProgress = p;
            cnt = 1;
        }
        
        answer.add(cnt);
        
        return answer.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}