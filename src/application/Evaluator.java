package application;

import java.util.Queue;
import java.util.Stack;

public class Evaluator {

    private final Stack<Double> eval = new Stack<>();

    private final Queue<String> postQ;

    public Evaluator(Queue<String> postQ) {
        this.postQ = postQ;
    }

    public String evaluate() {
        String input;

        double answer;

        double topNum;

        double nextNum;

        try {
            while (!postQ.isEmpty()) {
                input = postQ.remove();

                if (input.matches(InputOutput.NUMBER_REGEX)) {
                    eval.push(Double.valueOf(input));
                    continue;
                }

                topNum = eval.pop();
                nextNum = eval.pop();
                answer = Operator.toOperator(input).evaluate(nextNum, topNum);
                eval.push(answer);
            }
        } catch (ArithmeticException e) {
            return InputOutput.LINE_FEED + "ERROR" + InputOutput.LINE_FEED + e.getMessage();
        }

        return InputOutput.LINE_FEED + "Answer : " + String.format(InputOutput.ROUNDING_REGEX, eval.pop())
                + InputOutput.LINE_FEED;
    }
}