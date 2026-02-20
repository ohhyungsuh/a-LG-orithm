import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        
        Arrays.sort(rocks);
        int left = 1;
        int right = distance;
        
        while(left <= right) {
            int mid = (left + right) / 2;
            
            int removedRocks = getRemovedRocks(rocks, distance, mid);
            
            if(removedRocks > n) {
                right = mid - 1;
            } else {
                answer = mid;
                left = mid + 1;
            }
        }
        
        return answer;
    }
    
    private int getRemovedRocks(int[] rocks, int distance, int mid) {
        int removedRocks = 0;
        int tmp = 0;
        
        for(int rock : rocks) {
            if(rock - tmp < mid) {
                removedRocks++;
                continue;
            }
            tmp = rock;
        }
        
        if(distance - tmp < mid) {
            removedRocks++;
        }
        
        return removedRocks;
    }
}