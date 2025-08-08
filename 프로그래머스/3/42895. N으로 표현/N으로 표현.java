// 1. 1 <= N <= 9
// 2. 1 <= number <= 32,000
// 3. 괄호 및 사칙연산, 나머지는 무시
// 4. 최솟값이 8 초과면 -1 반환
// 5. 약간 백트래킹 해야할 거 같기도 하고? 그거 아니면 사칙 연산 답도 없네. 아 근데 백트래킹 까진 필요 없을 듯
// 6. 무조건 N에서 시작할거고... 아님 말고
// 7. 배열 개념으로 가도 안 될 거 같은데
// 8. 숫자 N을 몇 번 썼는지를 저장하는 Collection을 만들어놔야겠다. 그리고 계산하면서 중복될 수 있으니까 제네릭에 Set?
// 9. 근데 그럼 그 Collection에 어떤 숫자가 이미 체크됐는지 알아야하니까 그걸 저장해두는 Set을 만들어야되나?
// 9-1. 예를 들어서 5를 한 번 쓴게 최소인데, 3번 쓴 곳에 5가 또 나오면 안 되니까?
// 10. 얘를 그럼 얼마나 반복해야하는거지? number가 딱 계산되는 순간까지?

import java.util.*;
import java.lang.*;

class Solution {
    
    private final static int MAX_COMB = 8;
    
    public int solution(int N, int number) {
        
        if(N == number) {
            return 1;
        }
        
        Set<Integer> duplications = new HashSet<>();
        List<Set<Integer>> combinations = init(N, number, duplications);
        
        for(int i = 2; i <= MAX_COMB; i++) {
            getCombinedNumbers(i, duplications, combinations);
            
            if(combinations.get(i).contains(number)) {
                return i;
            }
        }
        
        return -1;
    }
    
    private List<Set<Integer>> init(int N, int number, Set<Integer> duplications) {
        List<Set<Integer>> combinations = new ArrayList<>(MAX_COMB + 1);
        
        for(int i = 0; i <= MAX_COMB; i++) {
            combinations.add(new HashSet<>());
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= MAX_COMB; i++) {
            sb.append(N);
            
            int attachedNumber = Integer.parseInt(sb.toString());
            combinations.get(i).add(attachedNumber);
            duplications.add(attachedNumber);
        }
        
        return combinations;
    }
    
    private void getCombinedNumbers(int index, Set<Integer> duplications, 
                                    List<Set<Integer>> combinations) {
        
        Set<Integer> nextCombination = new HashSet<>();
        
        for(int i = 1; i <= index / 2; i++) {
            int leftSide = index - i;
            
            Set<Integer> firstCombination = combinations.get(i);
            Set<Integer> secondCombination = combinations.get(leftSide);
            
            getNextCombination(duplications, firstCombination, secondCombination, nextCombination);
        }
        
        combinations.get(index).addAll(nextCombination);
    }
    
    private void getNextCombination(Set<Integer> duplications, 
                                    Set<Integer> firstCombination, 
                                    Set<Integer> secondCombination,
                                    Set<Integer> nextCombination) {
        for(int first : firstCombination) {
            for(int second : secondCombination) {
                int big = Math.max(first, second);
                int small = Math.min(first, second);
                
                int[] combination = {big + small, big - small, big * small, big / small};
                
                for(int i = 0; i < 4; i++) {
                    if(combination[i] != 0 && !duplications.contains(combination[i])) {
                        duplications.add(combination[i]);
                        nextCombination.add(combination[i]);
                    }
                }
            }
        }
    }
}