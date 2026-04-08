import java.util.*;
import java.lang.*;

class Solution {
    
    public int solution(String dirs) {
        int answer = 0;
        
        Set<String> visited = new HashSet<>();
        
        int x = 0, y = 0;
        
        for(char c : dirs.toCharArray()) {
            int nx = x;
            int ny = y;
            if(c == 'U') {
                nx--;
            } else if(c == 'D') {
                nx++;
            } else if(c == 'R') {
                ny++;
            } else {
                ny--;
            }
            
            if(nx < -5 || nx > 5 || ny < -5 || ny > 5) {
                continue;
            }
            
            String path = makePath(x, y, nx, ny);
            
            if(!visited.contains(path)) {
                visited.add(path);
                answer++;
            }
            x = nx;
            y = ny;
        }
        
        return answer;
    }
    
    private String makePath(int x, int y, int nx, int ny) {
        if(x < nx || (x == nx && y < ny)) {
            return x + "," + y + ":" + nx + "," + ny;
        } else {
            return nx + "," + ny + ":" + x + "," + y;
        }
    }
}