import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int TERM = 5;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        int rotation = L + TERM;
        boolean[] possible = new boolean[rotation * N];

        for(int i = 0; i < possible.length; i += rotation) {
            for(int j = i; j < i + L; j++) {
                possible[j] = true;
            }
        }

        int i = 1;
        while(D * i < rotation * N) {
            if(!possible[D * i]) {
                break;
            }
            i++;
        }

        System.out.println(D * i);
    }
}
