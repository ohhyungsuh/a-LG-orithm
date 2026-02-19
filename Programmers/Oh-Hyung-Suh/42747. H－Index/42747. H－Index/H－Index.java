import java.util.*;
// 논문 n편 중, h번 이상 인용된 논문이 h편이상이고,
// 나머지 논문이 h번 이하면 h의 최댓값이 과학자의 h-index
class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        int N = citations.length;
        
        Arrays.sort(citations);
        
        for(int i = 0; i < N; i++) {
            int h = N - i;
            
            if(citations[i] >= h) {
                return h;
            }
        }
        
        return answer;
    }
}