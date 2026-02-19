import java.util.*;

class Solution {
    
    private static int[] first = {1, 2, 3, 4, 5};
    private static int[] second = {2, 1, 2, 3, 2, 4, 2, 5};
    private static int[] third = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
    
    public int[] solution(int[] answers) {        
        List<Integer> people = new ArrayList<>();
        
        int[] correct = new int[3];
        
        for(int i = 0; i < answers.length; i++) {
            if(answers[i] == first[i % first.length]) {
                correct[0]++;
            }
            
            if(answers[i] == second[i % second.length]) {
                correct[1]++;
            }
            
            if(answers[i] == third[i % third.length]) {
                correct[2]++;
            }
        }
        
        int maxValue = Math.max(Math.max(correct[0], correct[1]), correct[2]);
        
        for(int i = 0; i < 3; i++) {
            if(maxValue == correct[i]) {
                people.add(i + 1);
            }
        }
        
        return people.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}