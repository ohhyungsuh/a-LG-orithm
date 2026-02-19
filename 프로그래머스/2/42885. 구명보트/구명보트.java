import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        int N = people.length;
        
        Arrays.sort(people);
        
        int start = 0;
        int end = N - 1;
        
        while(start <= end) {
            if(people[end] + people[start] <= limit) {
                start++;
            }
            end--;
            answer++;
        }
        
        return answer;
    }
}