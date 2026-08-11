package gui;

import javax.swing.*;
import java.awt.*;
import dao.CriminalDAO;
import model.Criminal;

public class UpdateCriminalFrame extends JFrame {
    JLabel imageLabel;
    JTextField idField;
    JButton searchButton;
    JButton updateButton;
    JTextField nameField;
    JTextField ageField;
    JTextField crimeField;
    private String currentImagePath;
    JComboBox<String> genderBox;

    JTextArea addressArea;
    public UpdateCriminalFrame() {
        imageLabel = new JLabel();
        imageLabel.setBounds(350,50,120,150);

        add(imageLabel);
        setTitle("Search Criminal");
        setSize(500,500);
        setLayout(new GridLayout(8,2));


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

        addressArea = new JTextArea(3,20);

        updateButton = new JButton("Update");
        add(updateButton);

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


        searchButton.addActionListener(e -> {

            int id = Integer.parseInt(idField.getText());

            CriminalDAO dao = new CriminalDAO();

            Criminal c = dao.searchCriminal(id);


            if(c != null) {

                nameField.setText(c.getName());

                ageField.setText(String.valueOf(c.getAge()));

                genderBox.setSelectedItem(c.getGender());

                crimeField.setText(c.getCrime());

                addressArea.setText(c.getAddress());
                String path = c.getImagePath();
                System.out.println("Image Path = " + path);
                ImageIcon icon = new ImageIcon(path);

                Image img = icon.getImage();

                Image newImg = img.getScaledInstance(
                        120,
                        150,
                        Image.SCALE_SMOOTH
                );

                imageLabel.setIcon(new ImageIcon(newImg));
            }
            else {

                JOptionPane.showMessageDialog(
                        this,
                        "Criminal not found"
                );

            }

        });

        updateButton.addActionListener(e -> {

            Criminal criminal = new Criminal();

            criminal.setCriminalId(Integer.parseInt(idField.getText()));
            criminal.setName(nameField.getText());
            criminal.setAge(Integer.parseInt(ageField.getText()));
            criminal.setGender(genderBox.getSelectedItem().toString());
            criminal.setCrime(crimeField.getText());
            criminal.setAddress(addressArea.getText());

            // Keep the same image path
            criminal.setImagePath(currentImagePath);
            CriminalDAO dao = new CriminalDAO();
            dao.updateCriminal(criminal);
            if (dao.updateCriminal(criminal)) {
                JOptionPane.showMessageDialog(this, "Criminal updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Update failed!");
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    public static void main(String[] args) {

        new SearchCriminalFrame();

    }
}
