import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int N = prices.length;

        if(N == 1) {
            return new int[]{0};
        }
        
        int[] answer = new int[N];
        Stack<Integer> stack = new Stack<>();
        
        for(int i = 0; i < N; i++) {
            
            while(!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                int idx = stack.pop();
                answer[idx] = i - idx;
            }
            
            stack.push(i);
        }
        
        while(!stack.isEmpty()) {
            int idx = stack.pop();
            answer[idx] = N - idx - 1;
        }
        
        return answer;
    }
}