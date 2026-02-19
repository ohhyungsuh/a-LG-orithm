import java.util.*;

class Solution {
    public int solution(String name) {
        int answer = getAlphaMovement(name);
        int N = name.length();
        
        int movement = N - 1;
        for (int i = 0; i < N; i++) {
            int idx = i + 1;
            while (idx < N && name.charAt(idx) == 'A') idx++;

            movement = Math.min(movement, i * 2 + (N - idx));
            movement = Math.min(movement, (N - idx) * 2 + i);
        }
        
        return answer + movement;
    }
    
    

    // 알파벳은 26개(A에서 M까지는 12번, N까지는 앞뒤 13번 동일, O부터는 뒤로 12번)
    private int getAlphaMovement(String name) {
        int movement = 0;
        
        for(char c : name.toCharArray()) {
            movement += Math.min(c - 'A', 'Z' - c + 1);
        }
        
        return movement;
    }
}