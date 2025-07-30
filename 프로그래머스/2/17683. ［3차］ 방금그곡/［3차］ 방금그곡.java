// 1. 아 문제 뭔가 더러워보이네....
// 2. 멜로디로 음악 찾기네
// 3. 음악 끝과 시작 이어진 거랑 중간에 끊긴 경우...
// 4. C, C#, D, D#, E, F, F#, G, G#, A, A#, B 12개
// 5. 음악은 무조건 처음부터 재생, 1분에 1개씩, 반복 재생 가능. 최대 24시간 = 1440분
// 6. 조건이 일치하는게 여러개면 재생 시간 젤 긴 거..
// 7. 없으면 (None)
// 8. 아 뭐가 왜케 많아.. 쩝
// 9. musicinfos 가공하는게 먼저일 것 같다
// 9-1. 재생 시간 구하는 메서드도 필요하고, 제목 별 정보를 더 넣어야되나? 뭐가 너무 많아서 아직 잘 모르겠다.
// 10. 재생된 기간이라는게 '멜로디가 가장 긴 거' 일까 '멜로디가 짧아도 길게 재생된 거' 일까? 후자겠지?
// 11. 곡을 담을 class를 하나 만들어야 정렬도 편할 것 같다
// 12. '#'도 섞여있어서 처리를 잘 해야되네.
// 12-1. Stack까지 쓸까 하다가, 이러면 for문 두 번 도는 꼴 같아서 그냥 List 사용하기로함. set 메서드 있는거 처음 알았음
// 13. 그냥 맘편히 split 써도 되지만, StringTokenizer 오랜만에 쓰고 싶기도 하고, musicinfos 데이터가 누락된 거 없이 일정하기 때문에 검증 없이 맘 편히 써야겠다
// 14. compareTo는 primitive에서 못 쓰는 걸 생각도 못 함..... 멍충
        // musics.sort((o1, o2) -> {
        //     return o2.getPlayTime().compareTo(o1.getPlayTime());
        // });
// 15. isMatch로 for문 판정 좀 냄새나는데, 다른 방법 없나....

import java.util.*;
import java.lang.*;

class Solution {
    
    private static String NOT_MATCH = "(None)";
    
    public String solution(String m, String[] musicinfos) {
        List<Music> musics = new ArrayList<>();
        
        StringTokenizer st;
        for(String musicInfo : musicinfos) {
            st = new StringTokenizer(musicInfo, ",");
            
            int playTime = calPlayTime(String.valueOf(st.nextToken()), String.valueOf(st.nextToken()));
            String title = String.valueOf(st.nextToken());
            List<String> melodies = divMelody(String.valueOf(st.nextToken()));
            
            musics.add(new Music(title, playTime, melodies));
        }
        
        musics.sort((o1, o2) -> {
            return Integer.compare(o2.getPlayTime(), o1.getPlayTime());
        });
        
        List<String> searchMelody = divMelody(m);
        
        return getAnswer(searchMelody, musics);
    }
    
    private int calPlayTime(String startTime, String endTime) {
        String[] startHourMinute = startTime.split(":");
        String[] endHourMinute = endTime.split(":");
        
        int startHour = Integer.parseInt(startHourMinute[0]);
        int startMinute = Integer.parseInt(startHourMinute[1]);
        
        int endHour = Integer.parseInt(endHourMinute[0]);
        int endMinute = Integer.parseInt(endHourMinute[1]);
        
        return (endHour - startHour) * 60 + (endMinute - startMinute);
    }
    
    private List<String> divMelody(String melody) {
        int n = melody.length();
        List<String> melodies = new ArrayList<>();
        
        for(int i = 0; i < n; i++) {
            if(melody.charAt(i) == '#') {
                StringBuilder sb = new StringBuilder();
                sb.append(melodies.get(melodies.size() - 1)).append('#');
                
                melodies.set(melodies.size() - 1, sb.toString());
                continue;
            }
            melodies.add(String.valueOf(melody.charAt(i)));
        }
        
        return melodies;
    }
    
    private String getAnswer(List<String> searchMelodies, List<Music> musics) {
        List<String> fullMelodies;
        
        for(Music music : musics) {
            List<String> melodies = music.getMelodies();
            
            if(music.getPlayTime() <= melodies.size()) {
                fullMelodies = melodies.subList(0, music.getPlayTime());
            } else {
                fullMelodies = getFullMelodies(music);
            }
            
            if(searchMelodies.size() > fullMelodies.size()) {
                continue;
            }
            
            boolean isMatch = true;
            for(int i = 0; i < fullMelodies.size() - searchMelodies.size() + 1; i++) {
                isMatch = true;
                for(int j = i; j < i + searchMelodies.size(); j++) {
                    if(!fullMelodies.get(j).equals(searchMelodies.get(j - i))) {
                        isMatch = false;
                        break;
                    }
                }
                
                if(isMatch) {
                    break;
                }
            }
            
            if(isMatch) {
                return music.getTitle();
            }
        }
        
        return NOT_MATCH;
    }
    
    private List<String> getFullMelodies(Music music) {
        List<String> fullMelodies = new ArrayList<>();
        List<String> melodies = music.getMelodies();
        
        int playTime = music.getPlayTime();
        int melodySize = music.getMelodies().size();
        
        int total = playTime / melodySize;
        int left = playTime % melodySize;
        
        while(total-- > 0) {
            for(String melody : melodies) {
                fullMelodies.add(melody);
            }
        }
        
        for(int i = 0; i < left; i++) {
            fullMelodies.add(melodies.get(i));
        }
        
        return fullMelodies;
    }
}

class Music {
    private String title;
    private int playTime;
    private List<String> melodies;
    
    public Music(String title, int playTime, List<String> melodies) {
        this.title = title;
        this.playTime = playTime;
        this.melodies = melodies;
    }
    
    public String getTitle() {
        return title;
    }
    
    public int getPlayTime() {
        return playTime;
    }
    
    public List<String> getMelodies() {
        return melodies;
    }
}