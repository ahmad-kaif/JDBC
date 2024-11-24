import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Menu {
    Menu() {
        JFrame frame = new JFrame("Welcome To Ahmad's Library");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new GridLayout(3, 2));

        JButton registerUser = new JButton("Register User");
        JButton bookIssue = new JButton("Issue a Book");
        JButton complaint = new JButton("Complaint");

        // Action Listeners to open respective windows
        registerUser.addActionListener((e) -> {
            new RegUser();
        });

        bookIssue.addActionListener((e) -> {
            new BookIssue();
        });

        complaint.addActionListener((e) -> {
            new Complaint();
        });

   
        frame.add(registerUser);
        frame.add(bookIssue);
        frame.add(complaint);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Menu(); 
    }
}

class RegUser {
    public RegUser() {
        JFrame frame = new JFrame("Register a User");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame
        frame.setSize(600, 600);
        frame.setLayout(new GridLayout(5, 2));

        //Details
        JLabel name = new JLabel(" Name :");
        JTextField nameArea = new JTextField();
        JLabel roll = new JLabel(" Registration Number :");
        JTextField rollArea = new JTextField();
        JLabel email = new JLabel(" Email :");
        JTextField emailArea = new JTextField();
        JLabel phone = new JLabel(" Phone Number :");
        JTextField phoneArea = new JTextField();

        //submit Button
        JButton submit = new JButton("Register");
        JButton reset = new JButton("Reset");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String url = "jdbc:mysql://localhost:3306/user_management";
                String user = "root";
                String password = "";

                String name = nameArea.getText();
                String roll = rollArea.getText();
                String email = emailArea.getText();
                String phone = phoneArea.getText();
                Connection c = null;
                try {
                    c = DriverManager.getConnection(url, user, password);
                    // System.out.println("DB CONNECTED!!!");
                    String q1 = "insert into users (roll,name,email,phone) values (?,?,?,?)";
                    PreparedStatement stmt = c.prepareStatement(q1);
                    stmt.setString(1, roll);
                    stmt.setString(2, name);
                    stmt.setString(3, email);
                    stmt.setString(4, phone);
                    stmt.executeUpdate();

                    JOptionPane.showMessageDialog(frame, "Data Inserted Succesfully");

                    
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(frame,"Failed to insert data: " + err.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                    System.out.println("Error in DB COnnection"+err);
                }

            }
        });
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                nameArea.setText("");
                rollArea.setText("");
                emailArea.setText("");
                phoneArea.setText("");
            }
        });

        frame.add(name);
        frame.add(nameArea);
        frame.add(roll);
        frame.add(rollArea);
        frame.add(email);
        frame.add(emailArea);
        frame.add(phone);
        frame.add(phoneArea);

        frame.add(submit);
        frame.add(reset);



        frame.setVisible(true);
    }
}

class BookIssue {
    public BookIssue() {
        // Create and display the Book Issue frame
        JFrame frame = new JFrame("Issue a Book");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame
        frame.setSize(600, 600);
        frame.setLayout(new GridLayout(0, 2));

        JTextField bookName = new JTextField();
        JButton issue = new JButton("Issue");
        issue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

            }
        });


        JLabel rollHeader = new JLabel("Roll Number", SwingConstants.CENTER);
        JLabel cntHeader = new JLabel("Books Issued", SwingConstants.CENTER);

        frame.add(bookName);
        frame.add(issue);
        frame.add(rollHeader);
        frame.add(cntHeader);

        String[][] data = {
            {"20223pgcsca085", "2"},
            {"20223pgcsca090", "6"},
            {"20223pgcsca095", "1"},
            {"20223pgcsca032", "3"},
        };

        for (String[] row : data) {
            frame.add(new JLabel(row[0], SwingConstants.CENTER)); // Roll Number
            frame.add(new JLabel(row[1], SwingConstants.CENTER)); // Books Issued
        }

        frame.setVisible(true);
    }
}

class Complaint {
    public Complaint() {
        JFrame frame = new JFrame("File a Complaint");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        frame.setSize(600, 600); 
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // Add components
        JLabel label = new JLabel("Enter Your Complaint:");
        JTextArea complaintArea = new JTextArea(4, 10);
        JButton submitButton = new JButton("Submit");

        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        submitButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

        frame.add(label);
        frame.add(new JScrollPane(complaintArea)); // Scrollable text area
        frame.add(submitButton);

        frame.setVisible(true);

        submitButton.addActionListener(e -> {
            String complaint = complaintArea.getText().trim();
            if (!complaint.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Complaint Submitted: " + complaint);
                complaintArea.setText(""); 
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter a valid complaint.");
            }
        });
    }
}
