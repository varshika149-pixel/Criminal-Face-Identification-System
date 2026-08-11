package gui;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    public DashboardFrame() {

        setTitle("Dashboard");
        setSize(600, 450);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("Criminal Face Identification System");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBounds(90, 20, 400, 30);
        JButton addBtn = new JButton("Add Criminal");
        addBtn.setBounds(180, 80, 220, 35);
        addBtn.addActionListener(e -> new AddCriminalFrame());

        JButton searchBtn = new JButton("Search Criminal");
        searchBtn.setBounds(180, 130, 220, 35);
        searchBtn.addActionListener(e -> new SearchCriminalFrame());

        JButton updateBtn = new JButton("Update Criminal");
        updateBtn.setBounds(180, 180, 220, 35);
        updateBtn.addActionListener(e -> new UpdateCriminalFrame());

        JButton deleteBtn = new JButton("Delete Criminal");
        deleteBtn.setBounds(180, 230, 220, 35);
        deleteBtn.addActionListener(e -> new DeleteCriminalFrame());

        JButton viewBtn = new JButton("View Criminals");
        viewBtn.setBounds(180, 280, 220, 35);
        viewBtn.addActionListener(e -> new ViewCriminalFrame());

        JButton exitBtn = new JButton("Exit");
        exitBtn.setBounds(180, 330, 220, 35);   // Move below View button
        exitBtn.addActionListener(e -> System.exit(0));

        add(title);
        add(addBtn);
        add(searchBtn);
        add(updateBtn);
        add(deleteBtn);
        add(viewBtn);
        add(exitBtn);

        setVisible(true);
    }
}