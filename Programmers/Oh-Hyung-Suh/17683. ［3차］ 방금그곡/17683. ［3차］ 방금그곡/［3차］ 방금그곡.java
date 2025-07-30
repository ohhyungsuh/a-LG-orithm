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