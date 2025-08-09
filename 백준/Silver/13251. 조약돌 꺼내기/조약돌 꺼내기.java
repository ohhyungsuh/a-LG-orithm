import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int M = Integer.parseInt(br.readLine());
        int[] stones = new int[M];
        int N = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            stones[i] = Integer.parseInt(st.nextToken());
            N += stones[i];
        }

        int K = Integer.parseInt(br.readLine());

        double total = getCombination(K, N);

        double sameColor = 0.0;
        for(int i = 0; i < M; i++) {
            int stone = stones[i];
            if(stone >= K) {
                sameColor += getCombination(K, stone);
            }
        }

        System.out.println(sameColor / total);
    }

    private static double getCombination(int K, int N) {
        int k = Math.min(K, N - K);

        double combination = 1.0;

        for(int i = 1; i <= k; i++) {
            combination *= N--;
            combination /= i;
        }

        return combination;
    }
}
