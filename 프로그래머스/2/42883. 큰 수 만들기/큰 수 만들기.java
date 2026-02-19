import java.util.*;

class Solution {
    public String solution(String number, int k) {
        int leftSize = number.length() - k;
        int startIdx = 0;
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < leftSize; i++) {
            char maxValue = '0';
            
            for(int j = startIdx; j <= i + k; j++) {
                if(number.charAt(j) == '9') {
                    maxValue = '9';
                    startIdx = j + 1;
                    break;
                }
                if(number.charAt(j) > maxValue) {
                    maxValue = number.charAt(j);
                    startIdx = j + 1;   
                }
            }
            
            sb.append(maxValue);
        }
        
        
        return sb.toString();
    }
}