package application;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Converter {

    private final Stack<String> opStack = new Stack<>();

    private final Queue<String> postQ = new LinkedList<>();

    private final Queue<String> infixQ;

    public Converter(Queue<String> infixQ) {
        this.infixQ = infixQ;
    }

    public Queue<String> convert() {
        String input;

        while (!infixQ.isEmpty()) {
            input = infixQ.remove();

            if (input.matches("\\d*\\.?\\d+")) {
                postQ.add(input);
                continue;
            }

            if (opStack.isEmpty() || "(".equals(input)) {
                opStack.push(input);
                continue;
            }

            if (")".equals(input)) {
                while (!"(".equals(opStack.peek())) {
                    postQ.add(opStack.pop());
                }
                opStack.pop();
            } else {
                while (!opStack.isEmpty() && !"(".equals(opStack.peek())
                        && Operator.compare(input, opStack.peek()) <= 0) {
                    postQ.add(opStack.pop());
                }
                opStack.push(input);
            }

        }

        while (!opStack.isEmpty()) {
            postQ.add(opStack.pop());
        }

        return postQ;
    }

    public Stack<String> getOpStack() {
        return opStack;
    }

}
