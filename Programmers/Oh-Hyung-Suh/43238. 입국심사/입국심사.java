// 1. 이분 탐색 알고리즘... 내가 못하는 것중 하나..
// 2. 한 심사대는 한 명만 심사 가능
// 3. 가장 앞에 있는 사람은 빨리 끝나는 심사대가 있으면 바로바로 갈 수 있음
// 4. 모든 사람 심사 최소 시간
// 5. 아 근데, 알고리즘 모르고 들어왔어도 인원이 10억이라 무조건 이분 탐색 적용해볼라했겠다
// 6. n이 기다리는 사람, 심사관이 한 명 심사하는데 1 ~ 10억분.......;;????????
// 7. solution return 값에 나와있지만, type은 long으로 해야겠네
// 8. times를 이분탐색 돌려면 정렬 먼저 해야겠네. 근데 어떻게 돌려야하지 ㅠ 무슨 기준?
// 9. 아래처럼 해야 최소값 28분
        // 심1: 1 3 5 6
        // 심2: 2 4
        
        // 아래처럼 하면 최소값 30분
        // 심1: 1 3 6
        // 심2: 2 4 5
// 10. 최소값은 times 원소의 배수겠네. 근데 그렇다고해서 특정 원소 값을 매번 times로 나눠서 비교할 순 없을 거 같고
// 10-1. 아 근데 심사관은 10만명 이하구나? 괜찮을 거 같기도? 심사관도 10억인 줄 알았네. 그럼 그렇게 해봐야겠다
// 10-2. times를 이분탐색 할 필요가 없겠네. 1 ~ 10억으로 가야겠다. 최대가 log(10억) * 10만 ?
// 10-3. 아 근데 또 10억을 이분탐색 해버리면 이게 나눠 떨어지는질 모르는데? 그리고 만약에 심사관 시간이 엄청 작은데, 10억 계속 돌면 개손핸디..
// 10-4. 이 부분은 참고...
// 11. 왜 이게 안 되지? 테스트 케이스가 뭐가 더 있지...?
// 12. 아 ;;; 우선 end 구할 때 (long) n 안 했다고 이러네?
// 13. 근데도 틀리네..... long 붙이면 9번만 실패고, 안 붙이면 9번 통과가 되네

import java.util.*;

class Solution {
    
    public long solution(int n, int[] times) {
        long answer = 0;
        
        Arrays.sort(times);
        
        long start = 1;
        long end = times[times.length - 1] * (long) n;
        
        while(start <= end) {
            long mid = (start + end) / 2;
            long people = 0;
            
            for(int time : times) {
                people += (mid / time);
                
                if(people > n) {
                    break;
                }
            }
            
            if(people < n) {
                start = mid + 1;
            } else if(people >= n) {
                end = mid - 1;
                answer = mid;
            }
        }
        
        return answer;
    }
}