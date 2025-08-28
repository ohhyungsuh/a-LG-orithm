import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;

        int N = Integer.parseInt(br.readLine());
        String[] inputs = br.readLine().split(" ");
        int[] buildings = new int[N];

        for(int i = 0; i < N; i++) {
            buildings[i] = Integer.parseInt(inputs[i]);
        }

        if(N == 1) {
            System.out.println(0);
            return;
        }

        for(int i = 0; i < N; i++) {
            int viewCount = getViewCount(i, N, buildings);
            answer = Math.max(answer, viewCount);
        }

        System.out.println(answer);
    }

    private static int getViewCount(int idx, int N, int[] buildings) {
        int viewCount = 0;
        double leftMinSlope = 0, rightMaxSlope = 0;

        if(idx != 0) {
            leftMinSlope = (buildings[idx] - buildings[idx - 1]);
            viewCount++;
        }

        if(idx != N - 1) {
            rightMaxSlope = (buildings[idx + 1] - buildings[idx]);
            viewCount++;
        }

        for(int i = idx - 2; i >= 0; i--) {
            double tmpSlope = (double) (buildings[i] - buildings[idx]) / (i - idx);
            if(leftMinSlope > tmpSlope) {
                leftMinSlope = tmpSlope;
                viewCount++;
            }
        }

        for(int i = idx + 2; i < N; i++) {
            double tmpSlope = (double) (buildings[i] - buildings[idx]) / (i - idx);
            if(rightMaxSlope < tmpSlope) {
                rightMaxSlope = tmpSlope;
                viewCount++;
            }
        }

        return viewCount;
    }
}
