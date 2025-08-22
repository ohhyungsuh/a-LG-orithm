import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = Integer.MAX_VALUE;

        String[] input = br.readLine().split(" ");
        int C = Integer.parseInt(input[0]);
        int N = Integer.parseInt(input[1]);

        int total = C + 101;
        int[] dp = new int[total];
        Arrays.fill(dp, 1_000_000);
        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            int money = Integer.parseInt(input[0]);
            int customer = Integer.parseInt(input[1]);

            for (int j = customer; j < total; j++) {
                dp[j] = Math.min(dp[j], dp[j - customer] + money);
            }
        }

        for(int i = C; i < total; i++) {
            answer = Math.min(answer, dp[i]);
        }

        System.out.println(answer);
    }
}