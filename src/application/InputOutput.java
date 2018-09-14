package application;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class InputOutput {

    public static final String OPERATOR_REGEX = "POW|[-+*/%]";

    public static final String NUMBER_REGEX = "\\d*\\.?\\d+";

    private static final String SPLIT_REGEX = "(?<=[-+*/()%])|(?=[-+*/()%])|(?<=POW)|(?=POW)";

    private static final String VAID_ELEMENT_REGEX = NUMBER_REGEX + "|" + "POW|[-+*/%()]";

    public static final String LINE_FEED = System.lineSeparator();

    public static final String ROUNDING_REGEX = "%.2f";

    private InputOutput() {

    }

    public static Queue<String> getInfixQueue(String input) {
        Queue<String> infixQ = new LinkedList<>();

        if (input == null || input.isEmpty()) {
            addError(infixQ, "No infix expression found" + LINE_FEED);
            return infixQ;
        }

        StringBuilder errorMessageBuilder = new StringBuilder();

        String[] inputArray = input.split(SPLIT_REGEX);

        if (inputArray[0].matches(OPERATOR_REGEX)) {
            errorMessageBuilder.append("Cannot start with an operator").append(LINE_FEED);
        }

        boolean isPreviousAnOperator = false;

        boolean isCurrentAnOperator = false;

        for (String element : inputArray) {
            element = element.trim();

            if (element.isEmpty()) {
                continue;
            }

            if (!element.matches(VAID_ELEMENT_REGEX)) {
                errorMessageBuilder.append("Invalid element : ").append(element).append(LINE_FEED);
                continue;
            }

            isCurrentAnOperator = element.matches(OPERATOR_REGEX);

            if (isPreviousAnOperator && isCurrentAnOperator) {
                errorMessageBuilder.append("Cannot have two operators together").append(LINE_FEED);
                continue;
            }

            isPreviousAnOperator = isCurrentAnOperator;

            infixQ.add(element);
        }

        if (errorMessageBuilder.length() > 0) {
            infixQ = new LinkedList<>();
            addError(infixQ, errorMessageBuilder.toString());
        }

        return infixQ;
    }

    private static void addError(Queue<String> infixQ, String errorMessage) {
        infixQ.add("ERROR");
        infixQ.add(errorMessage);
    }

    public static void display(Iterator<String> iterator) {
        boolean isPrevNumber = false;

        System.out.print("Postfix expression : ");

        while (iterator.hasNext()) {
            String exp = iterator.next();

            if (exp.matches(NUMBER_REGEX)) {
                if (isPrevNumber) {
                    System.out.print(" ");
                }
                isPrevNumber = true;
                System.out.print(exp);
                continue;
            }

            isPrevNumber = false;
            System.out.print(exp);
        }
    }

}
