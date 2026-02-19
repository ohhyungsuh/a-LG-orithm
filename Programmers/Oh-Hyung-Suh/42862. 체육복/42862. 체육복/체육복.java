import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] s = new int[n + 1];
        Arrays.fill(s, 1);

        for (int l : lost) s[l]--;
        for (int r : reserve) s[r]++;

        for (int i = 1; i <= n; i++) {
            if (s[i] == 0) {
                if (i > 1 && s[i - 1] == 2) {
                    s[i - 1]--;
                    s[i]++;
                } else if (i < n && s[i + 1] == 2) {
                    s[i + 1]--;
                    s[i]++;
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (s[i] > 0) answer++;
        }
        return answer;
    }
}
