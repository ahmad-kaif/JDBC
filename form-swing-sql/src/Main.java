
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sign Up Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLayout(new GridLayout(5,2));

        //name
        JLabel name = new JLabel("Name: ");
        JTextField nameArea = new JTextField();
        //email
        JLabel email = new JLabel("Email: ");
        JTextField emailArea = new JTextField();
        //password
        JLabel pwd = new JLabel("Password: ");
        JPasswordField pwdArea = new JPasswordField();
        //gender
        JLabel gender = new JLabel("Gender");
        JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});

        //buttons
        JButton btn1 = new JButton("Register");
        JButton btn2 = new JButton("Reset");

        //action listner on buttons
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String name = nameArea.getText();
                String email = emailArea.getText();
                String pwd = new String(pwdArea.getPassword());
                String gender = (String) genderComboBox.getSelectedItem();

                String url = "jdbc:mysql://localhost:3306/user_management";
                String user = "root";
                String password = "";
                Connection c = null;
                try {
                    c = DriverManager.getConnection(url, user, password);
                    String q1 = "insert into users (name,email,pwd,gender) values (?,?,?,?)";
                    PreparedStatement stmt = c.prepareStatement(q1);
                    stmt.setString(1, name);
                    stmt.setString(2, email);
                    stmt.setString(3, pwd);
                    stmt.setString(4, gender);
                    stmt.executeUpdate();

                    JOptionPane.showMessageDialog(frame, "Data Inserted Succesfully", "Success", JOptionPane.INFORMATION_MESSAGE);


                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame,"Failed to insert data: " + ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                    System.out.println("User not Registered: "+e);
                }
            }
        });

        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                nameArea.setText("");
                emailArea.setText("");
                pwdArea.setText("");
                genderComboBox.setToolTipText("");
            }
        });

        frame.add(name);
        frame.add(nameArea);
        frame.add(email);
        frame.add(emailArea);
        frame.add(pwd);
        frame.add(pwdArea);
        frame.add(gender);
        frame.add(genderComboBox);
        frame.add(btn1);
        frame.add(btn2);

        frame.setVisible(true);
    }
}
