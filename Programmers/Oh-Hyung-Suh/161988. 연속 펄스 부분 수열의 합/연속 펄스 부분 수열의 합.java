import java.util.*;

class Solution {
    public long solution(int[] sequence) {
        int n = sequence.length;
        long firstValue = 0, secondValue = 0;
        long firstTmp = 0, secondTmp = 0;
        
        // first는 1부터, second는 -1부터
        int[] first = new int[n];
        int[] second = new int[n];
        
        for(int i = 0; i < n; i++) {
            if(i % 2 == 0) {
                first[i] = sequence[i];
                second[i] = sequence[i] * -1;
            } else {
                first[i] = sequence[i] * -1;
                second[i] = sequence[i];
            }
            
            if(firstTmp + first[i] <= 0) {
                firstTmp = 0;
            } else {
                firstTmp += first[i];
                firstValue = Math.max(firstValue, firstTmp);
            }
            
            if(secondTmp + second[i] <= 0) {
                secondTmp = 0;
            } else {
                secondTmp += second[i];
                secondValue = Math.max(secondValue, secondTmp);
            }
        }
        
        return Math.max(firstValue, secondValue);
    }
}