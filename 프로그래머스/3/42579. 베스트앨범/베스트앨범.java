import java.util.*;
import java.lang.*;

// 1. 속한 노래가 많이 재생된 장르를 먼저 수록합니다.
// 2. 장르 내에서 많이 재생된 노래를 먼저 수록합니다.
// 3. 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();
        
        // <장르, 전체 재생 횟수>
        Map<String, Integer> totalPlays = new HashMap<>();
        
        // 장르, <고유 번호, 재생 횟수>
        Map<String, Map<Integer, Integer>> eachPlays = new HashMap<>();
        
        int n = genres.length;
        
        for(int i = 0; i < n; i++) {
            // 장르 전체 재생 횟수 추가
            totalPlays.put(genres[i], totalPlays.getOrDefault(genres[i], 0) + plays[i]);
            
            // 장르 있으면 바로 추가, 없으면 새로운 HashMap 객체 생성해서 추가
            if(eachPlays.containsKey(genres[i])) {
                eachPlays.get(genres[i]).put(i, plays[i]);
            } else {
                Map<Integer, Integer> song = new HashMap<>();
                song.put(i, plays[i]);
                eachPlays.put(genres[i], song);
            }
            
            // if(eachPlays.containsKey(genres[i])) {
            //     eachPlays.get(genres[i]).put(i, plays[i]);
            // } else {
            //     eachPlays.put(genres[i], new HashMap<>(i, plays[i]));
            // }
        }
        
        /* 
         * 몰랐던 부분: set을 list로 변환하는 방법
         */
        List<String> genreList = new ArrayList<>(totalPlays.keySet());
        
        // 가장 많이 재생된 순서대로 우선 정렬
        genreList.sort((o1, o2) -> totalPlays.get(o2) - totalPlays.get(o1));
        
        // 장르 내에서 많이 재생된 순서 또 정렬, 고유 번호는 애초에 오름차순이므로 건드리지 않기
        for(String genre : genreList) {
            
            // 장르별 각 노래의 재생 횟수 HashMap 반환
            HashMap<Integer, Integer> songs = (HashMap) eachPlays.get(genre);
            
            // 각 노래 재생 횟수 비교할 고유 번호 반환
            List<Integer> nums = new ArrayList(songs.keySet());
            
            // 장르별 많이 재생된 순서대로 노래 정렬
            nums.sort((o1, o2) -> songs.get(o2) - songs.get(o1));
            
            // 첫 번째 번호 추가
            answer.add(nums.get(0));
            
            // 장르에 속한 노래가 두 개 이상인 경우
            if(nums.size() > 1) {
                answer.add(nums.get(1));
            }
        }
    
        // toArray가 생각이 안 나서 찾았습니다...
        return answer.stream()
            .mapToInt(i -> i)
            .toArray();
    }
}