// 1. 한 번호가 다른 번호의 접두어인 경우가 있는지 확인
// 2. 전화번호 배열 -> phone_book
// 3. 같은 전화번호가 들어가있는 경우는 없음

import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Set<String> set = new HashSet<>(Arrays.asList(phone_book));

        for (String num : phone_book) {
            for (int i = 1; i < num.length(); i++) {
                if (set.contains(num.substring(0, i))) return false;
            }
        }
        return true;
    }
}