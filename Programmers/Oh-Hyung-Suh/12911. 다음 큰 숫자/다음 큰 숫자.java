import java.util.*;
import java.lang.*;

class Solution {
    public int solution(int n) {
        int answer = n+1;
        int nCount = getOneCount(n);
        
        while(true) {
            if(nCount == getOneCount(answer)) {
                break;
            }
            answer++;
        }
        
        return answer;
    }
    
    private int getOneCount(int num) {
        int count = 0;
        
        while (num > 0) {
            count += (num & 1);
            
            num >>= 1;
        }
        return count;
    }
}