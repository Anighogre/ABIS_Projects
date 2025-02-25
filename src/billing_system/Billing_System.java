package billing_system;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Billing_System {

    private HashMap<String, Double> customerBills = new HashMap<>();

    // Method to calculate the bill based on units consumed
    public double calculateBill(double units) {
        double ratePerUnit = 5.0; // Example rate per unit
        return units * ratePerUnit;
    }

    // Method to add customer and their bill
    public void addCustomer(String name, double units) {
        double billAmount = calculateBill(units);
        customerBills.put(name, billAmount);
    }

    // Method to pay bill
    public boolean payBill(String name) {
        if (customerBills.containsKey(name)) {
            customerBills.remove(name);
            return true;
        }
        return false;
    }

    // Method to get bill details
    public String getBill(String name) {
        return customerBills.containsKey(name) ? "Bill for " + name + ": ₹" + customerBills.get(name) : "No bill found!";
    }
    public static void main(String[] args) {
        // TODO code application logic here
        Billing_System ebs = new Billing_System();

        JFrame frame = new JFrame("Electricity Billing System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 2));

        JLabel nameLabel = new JLabel("Customer Name:");
        JTextField nameField = new JTextField();
        JLabel unitsLabel = new JLabel("Units Consumed:");
        JTextField unitsField = new JTextField();
        JButton addButton = new JButton("Add Customer & Calculate Bill");
        JButton payButton = new JButton("Pay Bill");
        JButton generateButton = new JButton("Generate Bill");
        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);

        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(unitsLabel);
        frame.add(unitsField);
        frame.add(addButton);
        frame.add(payButton);
        frame.add(generateButton);
        frame.add(outputArea);

        addButton.addActionListener(e -> {
            String name = nameField.getText();
            double units;
            try {
                units = Double.parseDouble(unitsField.getText());
                ebs.addCustomer(name, units);
                outputArea.setText("Bill calculated for " + name);
            } catch (NumberFormatException ex) {
                outputArea.setText("Invalid input for units.");
            }
        });

        payButton.addActionListener(e -> {
            String name = nameField.getText();
            if (ebs.payBill(name)) {
                outputArea.setText("Bill paid for " + name);
            } else {
                outputArea.setText("No bill found for " + name);
            }
        });

        generateButton.addActionListener(e -> {
            String name = nameField.getText();
            outputArea.setText(ebs.getBill(name));
        });

        frame.setVisible(true);
    }
    
}       
