import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int N;
    private static int[] numbers;
    private static char[] operators;
    private static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        String expression = br.readLine();

        splitExpression(expression);

        dfs(0, numbers[0]);

        System.out.println(answer);
    }

    private static void splitExpression(String expression) {
        numbers = new int[N / 2 + 1];
        operators = new char[N / 2];

        for(int i = 0; i < expression.length(); i++) {
            if(i % 2 == 0) {
                numbers[i / 2] = expression.charAt(i) - '0';
            } else {
                operators[i / 2] = expression.charAt(i);
            }
        }
    }

    private static void dfs(int idx, int result) {
        if(idx >= operators.length) {
            answer = Math.max(answer, result);
            return;
        }

        int includeRound = calculate(idx, result, numbers[idx + 1]);
        dfs(idx + 1, includeRound);

        if(idx < operators.length - 1) {
            int excludeRound = calculate(idx + 1, numbers[idx + 1], numbers[idx + 2]);
            dfs(idx + 2, calculate(idx, result, excludeRound));
        }

    }

    private static int calculate(int operatorIdx, int now, int next) {
        char operator = operators[operatorIdx];

        if(operator == '+') {
            return now + next;
        } else if(operator == '-') {
            return now - next;
        } else if(operator == '*') {
            return now * next;
        }

        return 0;
    }
}