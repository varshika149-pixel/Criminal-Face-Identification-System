package gui;

import dao.CriminalDAO;

import javax.swing.*;
import java.awt.*;

public class DeleteCriminalFrame extends JFrame {

    JTextField idField;
    JButton deleteButton;

    public DeleteCriminalFrame() {

        setTitle("Delete Criminal");
        setSize(400,200);
        setLayout(new GridLayout(3,2));
        setLocationRelativeTo(null);

        add(new JLabel("Criminal ID"));

        idField = new JTextField();
        add(idField);

        deleteButton = new JButton("Delete");
        add(deleteButton);

        add(new JLabel());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        deleteButton.addActionListener(e -> {

            int id = Integer.parseInt(idField.getText());

            CriminalDAO dao = new CriminalDAO();

            if (dao.deleteCriminal(id)) {

                JOptionPane.showMessageDialog(this,
                        "Criminal deleted successfully!");

                idField.setText("");

            } else {

                JOptionPane.showMessageDialog(this,
                        "Criminal not found!");

            }

        });
    }

    public static void main(String[] args) {
        new DeleteCriminalFrame();
    }
}