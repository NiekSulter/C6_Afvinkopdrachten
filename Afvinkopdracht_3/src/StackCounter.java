import java.util.Stack;

public class StackCounter {

    String input = "Dit is een [] ({}) ()";
    Stack<Character> charStackOpen = new Stack<>();

    public static void main(String[] args) {
        StackCounter sc = new StackCounter();
        sc.countBrackets();
    }

    public void countBrackets() {
        for (char i : input.toCharArray()) {
            if (i == '[' || i == '(' || i == '{') {
                charStackOpen.push(i);
            } else if (i == ']' || i == ')' || i == '}') {
                checkBrackets(i);
            }
        }
        if (!charStackOpen.isEmpty()) {
            System.out.println("Error char " + charStackOpen.pop() + " opened but not closed");
        }
    }

    public void checkBrackets(Character ch) {
        char closePop = ch;
        if (!charStackOpen.isEmpty()) {
            char openPop = charStackOpen.peek();

            if (openPop == '{' && closePop == '}' ||
                    openPop == '(' && closePop == ')' ||
                    openPop == '[' && closePop == ']') {
                charStackOpen.pop();
            } else {
                System.out.println("Error char " + openPop + " and " + closePop + " do not match ");
            }
        } else {
            System.out.println("Error char " + ch);
        }
    }
}