package application;

import java.util.Queue;
import java.util.Scanner;

public class RPNCalculator {

    public static void main(String[] args) {
        System.out.println("SAMPLE 1:");
        String test = "(3+4/2)*(5*3-6)-8";
        System.out.println("Infix expression : " + test);
        performCalculation(test);

        System.out.println("SAMPLE 2:");
        test = "(3.23+4/2)*(2POW3-6.5)-8.23";
        System.out.println("Infix expression : " + test);
        performCalculation(test);

        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.print("Infix expression (or type quit to exit) : ");

                String input = sc.nextLine();

                if ("quit".equalsIgnoreCase(input)) {
                    System.out.println(InputOutput.LINE_FEED + "Exiting RPN Calculator");
                    break;
                }

                performCalculation(input);

            }
        }
    }

    private static void performCalculation(String input) {
        Queue<String> infixQ = InputOutput.getInfixQueue(input);

        if ("ERROR".equals(infixQ.peek())) {
            System.out.println(infixQ.remove());
            System.out.println(infixQ.remove());
            return;
        }

        Converter conv = new Converter(infixQ);

        Queue<String> postQ = conv.convert();

        InputOutput.display(postQ.iterator());

        Evaluator eval = new Evaluator(postQ);

        System.out.println(eval.evaluate());
    }
}
