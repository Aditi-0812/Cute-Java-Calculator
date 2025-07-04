import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class CuteCalculator {
    static double firstNumber = 0, secondNumber = 0, result = 0;
    static String operator = "";

    public static void main(String[] args) {
        JFrame frame = new JFrame("Cute Calculator");
        JTextField tf = new JTextField();
        tf.setEditable(false);
        tf.setHorizontalAlignment(SwingConstants.RIGHT);
        tf.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 5, 5)); // updated for more buttons

        String[] buttonLabels = {
            "7", "8", "9", "+",
            "4", "5", "6", "-",
            "1", "2", "3", "*",
            "C", "0", "=", "/",
            "x²", "1/x"
        };

        Font cuteFont = new Font("Comic Sans MS", Font.BOLD, 20);
        Color lightPink = new Color(255, 182, 193);

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(cuteFont);
            button.setBackground(lightPink);
            button.setFocusPainted(false);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String b = e.getActionCommand();

                    try {
                        if (b.matches("[0-9]")) {
                            tf.setText(tf.getText() + b);
                        } else if (b.matches("[+\\-*/%]")) {
                            firstNumber = Double.parseDouble(tf.getText());
                            operator = b;
                            tf.setText("");
                        } else if (b.equals("=")) {
                            secondNumber = Double.parseDouble(tf.getText());
                            switch (operator) {
                                case "+": result = firstNumber + secondNumber; break;
                                case "-": result = firstNumber - secondNumber; break;
                                case "*": result = firstNumber * secondNumber; break;
                                case "/": result = secondNumber != 0 ? firstNumber / secondNumber : Double.POSITIVE_INFINITY; break;
                                case "%": result = firstNumber % secondNumber; break;
                            }
                            tf.setText(result + "");
                        } else if (b.equals("C")) {
                            tf.setText("");
                            firstNumber = secondNumber = result = 0;
                            operator = "";
                        } else if (b.equals("x²")) {
                            double val = Double.parseDouble(tf.getText());
                            tf.setText((val * val) + "");
                        } else if (b.equals("1/x")) {
                            double val = Double.parseDouble(tf.getText());
                            tf.setText(val != 0 ? (1 / val) + "" : "Infinity");
                        }
                    } catch (Exception ex) {
                        tf.setText("Error");
                    }
                }
            });

            panel.add(button);
        }

        frame.setLayout(new BorderLayout());
        frame.add(tf, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
