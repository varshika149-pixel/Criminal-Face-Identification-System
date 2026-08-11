package gui;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    JLabel title;
    JLabel userLabel;
    JLabel passLabel;

    JTextField username;
    JPasswordField password;

    JButton loginButton;
    JButton clearButton;

    public LoginFrame() {

        setTitle("Criminal Face Identification System");

        setSize(500,400);

        setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        title = new JLabel("Criminal Face Identification System");
        title.setBounds(70,30,350,30);
        title.setFont(new Font("Arial",Font.BOLD,20));

        userLabel = new JLabel("Username");
        userLabel.setBounds(70,100,100,25);

        username = new JTextField();
        username.setBounds(180,100,180,25);

        passLabel = new JLabel("Password");
        passLabel.setBounds(70,150,100,25);

        password = new JPasswordField();
        password.setBounds(180,150,180,25);

        loginButton = new JButton("Login");
        loginButton.setBounds(100,240,100,35);

        clearButton = new JButton("Clear");
        clearButton.setBounds(240,240,100,35);

        add(title);
        add(userLabel);
        add(username);
        add(passLabel);
        add(password);
        add(loginButton);
        add(clearButton);
        loginButton.addActionListener(e -> {

            String user = username.getText();
            String pass = new String(password.getPassword());

            if(user.equals("admin") && pass.equals("admin123")){

                JOptionPane.showMessageDialog(this, "Login Successful");

                dispose();

                new DashboardFrame();

            }else{

                JOptionPane.showMessageDialog(this, "Invalid Username or Password");

            }

        });

        clearButton.addActionListener(e -> {

            username.setText("");
            password.setText("");

        });
        setVisible(true);
    }
}