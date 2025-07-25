// 단순 노가단가..
// files도 별로 없네. 그냥 순서대로 돌면서 확인하면 될 것 같은데?
// 딱히 뭔 알고리즘이나 자료구조를 쓰는 것도 아닌 거 같고

import java.util.*;
import java.lang.*;

class Solution {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        List<FileInfo> fileInfos = new ArrayList<>();
        
        for(int i = 0; i < files.length; i++) {
            FileInfo fileInfo = getFileInfo(i, files[i]);
            fileInfos.add(fileInfo);
        }
        
        Collections.sort(fileInfos);
        
        for(int i = 0; i < files.length; i++) {
            answer[i] = files[fileInfos.get(i).id];
        }
             
        return answer;
    }
    
    private FileInfo getFileInfo(int idx, String file) {
        int id = idx;
        StringBuilder head = new StringBuilder();
        StringBuilder number = new StringBuilder();
        
        int checkIdx = 0;
        char[] fileName = file.toCharArray();
        for(int i = checkIdx; i < fileName.length; i++) {
            if(!isNum(fileName[i])) {
                head.append(fileName[i]);
                continue;
            }
            
            checkIdx = i;
            break;
        }
        
        for(int i = checkIdx; i < fileName.length; i++) {
            if(isNum(fileName[i])) {
                number.append(fileName[i]);
                continue;
            }
            
            checkIdx = i;
            break;
        }
        
        return new FileInfo(id, head.toString().toLowerCase(), Integer.parseInt(number.toString()), file.substring(checkIdx, file.length()));
    }
    
    private boolean isNum(char c) {
        return c - '0' >= 0 && c - '0' < 10 ? true : false;
    }
    
    class FileInfo implements Comparable<FileInfo> {
        int id;
        String head;
        int number;
        String tail;
        
        public FileInfo(int id, String head, int number, String tail) {
            this.id = id;
            this.head = head;
            this.number = number;
            this.tail = tail;
        }  
        
        @Override
        public int compareTo(FileInfo f) {
            int headCompare = this.head.compareTo(f.head);
            if(headCompare != 0) return headCompare;
            
            int numberCompare = Integer.compare(this.number, f.number);
            if (numberCompare != 0) return numberCompare;
            
            return this.id - f.id;
        }
    }
}