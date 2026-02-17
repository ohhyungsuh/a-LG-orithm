import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        Queue<Process> q = new ArrayDeque<>();
        
        for(int i = 0; i < priorities.length; i++) {
            q.add(new Process(i, priorities[i]));
        }
        
        int answer = 0;
        
        while(!q.isEmpty()) {
            Process currentP = q.poll();
            
            boolean hasHigher = false;
            for(Process p : q) {
                if(p.priority > currentP.priority) {
                    hasHigher = true;
                    break;
                }
            }
            
            if(hasHigher) {
                q.offer(currentP);
            } else {
                answer++;
                if(currentP.idx == location) {
                    return answer;
                }
            }
        }
        
        
        return -1;
    }
    
    static class Process {
        int idx;
        int priority;
        
        public Process(int idx, int priority) {
            this.idx = idx;
            this.priority = priority;
        }
    }
}