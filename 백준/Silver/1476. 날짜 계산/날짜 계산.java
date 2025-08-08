import java.io.*;
import java.util.*;

public class Main {

    private final static int[] rotation = {15, 28, 19};
    private final static int[] creation = {1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int answer = 1;
        int[] years = new int[3];

        for(int i = 0; i < 3; i++) {
            years[i] = Integer.parseInt(st.nextToken());
        }

        while(!Arrays.equals(years, creation)) {
            for(int i = 0; i < 3; i++) {
                years[i] -= 1;
            }

            for(int i = 0; i < 3; i++) {
                if(years[i] == 0) {
                    years[i] = rotation[i];
                }
            }

            answer++;
        }

        System.out.println(answer);
    }
}
