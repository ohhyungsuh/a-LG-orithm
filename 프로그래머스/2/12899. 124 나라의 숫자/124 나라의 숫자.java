import java.lang.*;

class Solution {
    public String solution(int n) {
        String[] convertedNum = {"4", "1", "2"};
        
        StringBuilder sb = new StringBuilder();
        
        while(n > 0) {
            int div = n % 3;
            
            n /= 3;
            
            if(div == 0) {
                n -= 1;
            }
            
            sb.append(convertedNum[div]);
        }
            
        return sb.reverse().toString();
    }
}