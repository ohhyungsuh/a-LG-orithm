import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        
        int n = queue1.length;
        long q1Sum = 0, q2Sum = 0;
        int q1MoveCount = 0, q2MoveCount = 0;
    
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        
        for(int i = 0; i < n; i++) {
            q1Sum += queue1[i];
            q2Sum += queue2[i];
            
            q1.add(queue1[i]);
            q2.add(queue2[i]);
        }
        
        long qSum = q1Sum + q2Sum;
        
        if(qSum % 2 == 1) {
            return -1;
        }
        
        /*
         * 놓친 조건
         * 작, 크 -> 크, 작 반복시, 무한루프 가능성
         */
        while(!q1.isEmpty() && !q2.isEmpty() &&
             q1MoveCount < 2 * n && q2MoveCount < 2 * n) {
            if(q1Sum == q2Sum) {
                return answer;
            }
            
            if(q1Sum < q2Sum) {
                int q2Num = q2.poll();
                q1.add(q2Num);
                q1Sum += q2Num;
                q2Sum -= q2Num;
                q1MoveCount++;
            } else if(q1Sum > q2Sum) {
                int q1Num = q1.poll();
                q2.add(q1Num);
                q1Sum -= q1Num;
                q2Sum += q1Num;
                q2MoveCount++;
            }

            answer++;
        }
        
        return -1;
    }
}