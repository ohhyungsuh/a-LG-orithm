// 장르 별로 많이 재생된 노래를 두 개씩 모음(장르별 최대 두 개씩 모음)
// 노래가 가장 많은 장르 -> 장르 내에서 많이 재생된 노래 -> 그 중 고유 번호가 낮은 노래 먼저
// genres -> 고유번호가 i인 노래 장르, plays -> 고유 번호가 i인 노래가 재생된 횟수(둘 길이 같음)
// 고유 번호도 갖고 있어야하고, genr

import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, List<Song>> songMap = new HashMap<>();
        Map<String, Integer> totalCnt = new HashMap<>();
        
        for(int i = 0; i < genres.length; i++) {
            songMap.computeIfAbsent(genres[i], k -> new ArrayList<>())
                .add(new Song(i, plays[i]));
            
            totalCnt.put(genres[i], totalCnt.getOrDefault(genres[i], 0) + plays[i]);
        }
        
        List<String> sortedGenres = new ArrayList<>(totalCnt.keySet());
        sortedGenres.sort((o1, o2) -> totalCnt.get(o2) - totalCnt.get(o1));
        
        List<Integer> answers = new ArrayList<>();
        
        for(String genre : sortedGenres) {
            List<Song> songs = songMap.get(genre);
            
            songs.sort((o1, o2) -> {
                if(o1.playCnt == o2.playCnt) {
                    return o1.num - o2.num;
                }
                return o2.playCnt - o1.playCnt;
            });
            
            answers.add(songs.get(0).num);
            
            if(songs.size() > 1) {
                answers.add(songs.get(1).num);
            }
        }
        
        return answers.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
    
    static class Song {
        int num;
        int playCnt;
        
        public Song(int num, int playCnt) {
            this.num = num;
            this.playCnt = playCnt;
        }
    }
}