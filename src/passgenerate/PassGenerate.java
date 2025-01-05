package passgenerate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassGenerate {

    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=<>?";

    // Function to generate a strong password
    public static String generatePassword(int length) {
        StringBuilder password = new StringBuilder();
        String allCharacters = LOWERCASE + UPPERCASE + DIGITS + SPECIAL_CHARACTERS;
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allCharacters.length());
            password.append(allCharacters.charAt(index));
        }

        return password.toString();
    }

    // Function to check the strength of a password
    public static String checkPasswordStrength(String password) {
        if (password.length() < 8) {
            return "Weak: Password must be at least 8 characters long";
        }

        Pattern lowerCasePattern = Pattern.compile("[a-z]");
        Pattern upperCasePattern = Pattern.compile("[A-Z]");
        Pattern digitPattern = Pattern.compile("[0-9]");
        Pattern specialCharPattern = Pattern.compile("[!@#$%^&*()-_+=<>?]");

        Matcher hasLowerCase = lowerCasePattern.matcher(password);
        Matcher hasUpperCase = upperCasePattern.matcher(password);
        Matcher hasDigit = digitPattern.matcher(password);
        Matcher hasSpecialChar = specialCharPattern.matcher(password);

        if (hasLowerCase.find() && hasUpperCase.find() && hasDigit.find() && hasSpecialChar.find()) {
            return "Strong";
        } else if (hasLowerCase.find() && hasUpperCase.find()) {
            return "Moderate";
        } else {
            return "Weak";
        }
    }

    public static void main(String[] args) {
        // Create GUI
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Password Generator");
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(null);

            // Components
            JLabel passwordLabel = new JLabel("Generated Password:");
            passwordLabel.setBounds(20, 20, 150, 25);
            frame.add(passwordLabel);

            JTextField passwordField = new JTextField();
            passwordField.setBounds(180, 20, 180, 25);
            passwordField.setEditable(false);
            frame.add(passwordField);

            JLabel strengthLabel = new JLabel("Password Strength:");
            strengthLabel.setBounds(20, 60, 150, 25);
            frame.add(strengthLabel);

            JTextField strengthField = new JTextField();
            strengthField.setBounds(180, 60, 180, 25);
            strengthField.setEditable(false);
            frame.add(strengthField);

            JButton generateButton = new JButton("Generate Password");
            generateButton.setBounds(20, 100, 180, 30);
            frame.add(generateButton);

            // Action Listener for the button
            generateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String password = generatePassword(12);
                    String strength = checkPasswordStrength(password);

                    passwordField.setText(password);
                    strengthField.setText(strength);
                }
            });

            // Show frame
            frame.setVisible(true);
        });
    }
}
