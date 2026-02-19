import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        for(int i = 1; i * i <= yellow; i++) {
            if(yellow % i != 0) {
                continue;
            }
            
            int h = i;
            int w = yellow / i;
            
            if(brown == (h + w) * 2 + 4) {
                answer[0] = w + 2;
                answer[1] = h + 2;
                break;
            }
        }
        
        return answer;
    }
}