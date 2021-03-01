import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int LEFT_ASSOC = 0;
    private static final int RIGHT_ASSOC = 1;
    boolean check=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText textView = (EditText) findViewById(R.id.text_view);
        textView.setText(" ");
        Button one = (Button) findViewById(R.id.one);
        one.setOnClickListener(this); // calling onClick() method
        Button two = (Button) findViewById(R.id.two);
        two.setOnClickListener(this);
        Button three = (Button) findViewById(R.id.three);
        three.setOnClickListener(this);
        Button four = (Button) findViewById(R.id.four);
        four.setOnClickListener(this); // calling onClick() method
        Button five = (Button) findViewById(R.id.five);
        five.setOnClickListener(this);
        Button six = (Button) findViewById(R.id.six);
        six.setOnClickListener(this);
        Button seven = (Button) findViewById(R.id.seven);
        seven.setOnClickListener(this); // calling onClick() method
        Button eight = (Button) findViewById(R.id.eight);
        eight.setOnClickListener(this);
        Button nine = (Button) findViewById(R.id.nine);
        nine.setOnClickListener(this);
        Button zero = (Button) findViewById(R.id.zero);
        zero.setOnClickListener(this); // calling onClick() method
        Button doublezero = (Button) findViewById(R.id.doublezero);
        doublezero.setOnClickListener(this);
        Button dot = (Button) findViewById(R.id.dot);
        dot.setOnClickListener(this);

        Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(this); // calling onClick() method

        Button plus = (Button) findViewById(R.id.plus);
        plus.setOnClickListener(this);
        Button minus = (Button) findViewById(R.id.minus);
        minus.setOnClickListener(this);
        Button multiply = (Button) findViewById(R.id.multiply);
        multiply.setOnClickListener(this); // calling onClick() method
        Button divide = (Button) findViewById(R.id.divide);
        divide.setOnClickListener(this);
        Button percent = (Button) findViewById(R.id.percent);
        percent.setOnClickListener(this);

    }

    private static final Map<String, int[]> OPERATORS = new HashMap<String, int[]>();

    static {
        // Map<"token", []{precendence, associativity}>
        OPERATORS.put("+", new int[]{0, LEFT_ASSOC});
        OPERATORS.put("-", new int[]{0, LEFT_ASSOC});
        OPERATORS.put("*", new int[]{5, LEFT_ASSOC});
        OPERATORS.put("/", new int[]{5, LEFT_ASSOC});
        OPERATORS.put("%", new int[]{5, LEFT_ASSOC});
    }

    // Test if token is an operator
    private static boolean isOperator(String token) {
        return OPERATORS.containsKey(token);
    }

    // Test associativity of operator token
    private static boolean isAssociative(String token, int type) {
        if (!isOperator(token)) {
            throw new IllegalArgumentException("Invalid token: " + token);
        }

        if (OPERATORS.get(token)[1] == type) {
            return true;
        }
        return false;
    }

    // Compare precedence of operators.
    private static final int cmpPrecedence(String token1, String token2) {
        if (!isOperator(token1) || !isOperator(token2)) {
            throw new IllegalArgumentException("Invalid tokens: " + token1
                    + " " + token2);
        }
        return OPERATORS.get(token1)[0] - OPERATORS.get(token2)[0];
    }


    public static String[] infixToRPN(String[] inputTokens) {
        ArrayList<String> out = new ArrayList<String>();
        Stack<String> stack = new Stack<String>();

        // For each token
        for (String token : inputTokens) {
            // If token is an operator
            if (isOperator(token)) {
                // While stack not empty AND stack top element
                // is an operator
                while (!stack.empty() && isOperator(stack.peek())) {
                    if ((isAssociative(token, LEFT_ASSOC) &&
                            cmpPrecedence(token, stack.peek()) <= 0) ||
                            (isAssociative(token, RIGHT_ASSOC) &&
                                    cmpPrecedence(token, stack.peek()) < 0)) {
                        out.add(stack.pop());
                        continue;
                    }
                    break;
                }
                // Push the new operator on the stack
                stack.push(token);
            }
            // If token is a left bracket '('
            else if (token.equals("(")) {
                stack.push(token);  //
            }
            // If token is a right bracket ')'
            else if (token.equals(")")) {
                while (!stack.empty() && !stack.peek().equals("(")) {
                    out.add(stack.pop());
                }
                stack.pop();
            }
            // If token is a number
            else {
                out.add(token);
            }
        }
        while (!stack.empty()) {
            out.add(stack.pop());
        }
        String[] output = new String[out.size()];
        return out.toArray(output);
    }


    // To clear EditText field
    public void Clear(View view) {
        EditText textView = (EditText) findViewById(R.id.text_view);
        textView.setText("");
    }


    public void Equals(View view) {
        EditText textView = (EditText) findViewById(R.id.text_view);
        String[] postfix;
        String infix;
        infix = textView.getText().toString();
        //infix += new String(" ");
        String[] infix_to_function = infix.split(" ");
        postfix = infixToRPN(infix_to_function);
        double a = RPNtoDouble(postfix);
        String s=String.valueOf(a);
        if (s.endsWith(".0"))
            s = s.substring(0,s.length() - 2);
        textView.setText(" " + s);
    }

    @Override
    public void onClick(View v) {
        EditText textView = (EditText) findViewById(R.id.text_view);

        switch (v.getId()) {
            case R.id.one:
                textView.setText(textView.getText() + "1");
                break;
            case R.id.two:
                textView.setText(textView.getText() + "2");
                break;
            case R.id.three:
                textView.setText(textView.getText() + "3");
                break;
            case R.id.four:
                textView.setText(textView.getText() + "4");
                break;
            case R.id.five:
                textView.setText(textView.getText() + "5");
                break;
            case R.id.six:
                textView.setText(textView.getText() + "6");
                break;
            case R.id.seven:
                textView.setText(textView.getText() + "7");
                break;
            case R.id.eight:
                textView.setText(textView.getText() + "8");
                break;
            case R.id.nine:
                textView.setText(textView.getText() + "9");
                break;
            case R.id.zero:
                textView.setText(textView.getText() + "0");
                break;
            case R.id.doublezero:
                textView.setText(textView.getText() + "00");
                break;
            case R.id.dot:
                textView.setText(textView.getText() + ".");
                break;
            case R.id.plus:
                textView.setText(textView.getText() + " + ");
                break;
            case R.id.minus:
                textView.setText(textView.getText() + " - ");
                break;
            case R.id.multiply:
                textView.setText(textView.getText() + " * ");
                break;
            case R.id.divide:
                textView.setText(textView.getText() + " / ");
                break;
            case R.id.percent:
                textView.setText(textView.getText() + " % ");
                break;
            case R.id.delete:
                String text = textView.getText().toString();
                text = text.substring(0, text.length() - 1);
                textView.setText(text);
                break;
            default:
                break;
        }
    }
    public static double RPNtoDouble(String[] tokens) {
        Stack<String> stack = new Stack<String>();

        // For each token
        for (String token : tokens) {
            // If the token is a value push it onto the stack
            if (!isOperator(token)) {
                stack.push(token);
            } else {

                // Token is an operator: pop top two entries
                Double d2 = Double.valueOf(stack.pop());
                Double d1 = Double.valueOf(stack.pop());

                //Get the result
                Double result = token.compareTo("+") == 0 ? d1 + d2 :
                        token.compareTo("-") == 0 ? d1 - d2 :
                                token.compareTo("*") == 0 ? d1 * d2 :
                                        token.compareTo("/") == 0 ? d1 / d2 : (d1 / 100.0) * d2;

                // Push result onto stack
                stack.push(String.valueOf(result));
            }
        }
            return Double.valueOf(stack.pop());
    }

}
