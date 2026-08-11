package gui;

import javax.swing.*;
import java.awt.*;
import dao.CriminalDAO;
import model.Criminal;

public class SearchCriminalFrame extends JFrame {

    JLabel imageLabel;
    JTextField idField;
    JButton searchButton;

    JTextField nameField;
    JTextField ageField;
    JTextField crimeField;

    JComboBox<String> genderBox;
    JTextArea addressArea;

    public SearchCriminalFrame() {

        setTitle("Search Criminal");
        setSize(500, 500);
        setLayout(new GridLayout(8, 2));

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel idLabel = new JLabel("Enter Criminal ID:");

        idField = new JTextField();

        searchButton = new JButton("Search");

        nameField = new JTextField();
        ageField = new JTextField();

        genderBox = new JComboBox<>(new String[]{
                "Male",
                "Female",
                "Other"
        });

        crimeField = new JTextField();

        addressArea = new JTextArea(3, 20);

        // Optional: Make fields read-only
        nameField.setEditable(false);
        ageField.setEditable(false);
        genderBox.setEnabled(false);
        crimeField.setEditable(false);
        addressArea.setEditable(false);

        add(idLabel);
        add(idField);

        add(searchButton);
        add(new JLabel());

        add(new JLabel("Name"));
        add(nameField);

        add(new JLabel("Age"));
        add(ageField);

        add(new JLabel("Gender"));
        add(genderBox);

        add(new JLabel("Crime"));
        add(crimeField);

        add(new JLabel("Address"));
        add(new JScrollPane(addressArea));

        add(new JLabel("Photo"));
        add(imageLabel);

        searchButton.addActionListener(e -> {

            try {

                int id = Integer.parseInt(idField.getText());

                CriminalDAO dao = new CriminalDAO();

                Criminal c = dao.searchCriminal(id);

                if (c != null) {

                    nameField.setText(c.getName());
                    ageField.setText(String.valueOf(c.getAge()));
                    genderBox.setSelectedItem(c.getGender());
                    crimeField.setText(c.getCrime());
                    addressArea.setText(c.getAddress());

                    String path = c.getImagePath();

                    if (path != null && !path.isEmpty()) {

                        ImageIcon icon = new ImageIcon(path);
                        Image img = icon.getImage();

                        Image newImg = img.getScaledInstance(
                                120,
                                150,
                                Image.SCALE_SMOOTH
                        );

                        imageLabel.setIcon(new ImageIcon(newImg));

                    } else {
                        imageLabel.setIcon(null);
                    }

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Criminal not found"
                    );

                    nameField.setText("");
                    ageField.setText("");
                    genderBox.setSelectedIndex(0);
                    crimeField.setText("");
                    addressArea.setText("");
                    imageLabel.setIcon(null);
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid Criminal ID."
                );
            }

        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SearchCriminalFrame();
    }
}