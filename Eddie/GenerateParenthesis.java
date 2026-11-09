package Eddie;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

class GenerateParenthesis {

    // 11/8/206
    public static List<String> generateParenthesis(int n) {
        if (n <= 0 || n > 8) {
            throw new IllegalArgumentException("Parameter Violated: n must be between or equal to 1 and 8");
        }

        List<String> result = new LinkedList<>();
        //checking the singular case on its own(I know its bad, but I was struggling)
        if (n == 1) {
            result.add("()");
        } else {
            parenthesisHelper(n*2, result, new StringBuilder());
        }

        return result;
    }

    //recursive helper method
    private static void parenthesisHelper(int n, List<String> result, StringBuilder curr) {
        // base case
        if (n == 0 && isValid(curr)) {
            result.add(curr.toString());
        } else if (n > 0){

            //choose front, explore
            curr.append("(");
            parenthesisHelper(n - 1, result, curr);
            //unchoose
            curr.deleteCharAt(curr.length() - 1);

            //choose back, explore
            curr.append(")");
            parenthesisHelper(n - 1, result, curr);
            //unchoose
            curr.deleteCharAt(curr.length() - 1);
        }
    }

    //helper method 
    private static boolean isValid(StringBuilder curr) {
        Stack<Character> checker = new Stack<>();
        for (int i = 0; i < curr.length(); i++) {
            char sym = curr.charAt(i);
            if (sym == '(') {
                checker.push(sym);
            } else {
                if (checker.isEmpty()) {
                    return false;
                } else {
                    checker.pop();
                }
            }
        }
        return checker.isEmpty();

    }

}