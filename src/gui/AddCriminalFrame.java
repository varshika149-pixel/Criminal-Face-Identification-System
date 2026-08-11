package gui;
import javax.swing.JFileChooser;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import dao.CriminalDAO;
import model.Criminal;
public class AddCriminalFrame extends JFrame {
    private String imagePath = "";
    JLabel title, idLabel, nameLabel, ageLabel, genderLabel, crimeLabel, addressLabel;

    JTextField idField, nameField, ageField, crimeField;

    JTextArea addressArea;

    JComboBox<String> genderBox;

    JButton chooseImageBtn, saveBtn, clearBtn;

    public AddCriminalFrame() {

        setTitle("Add Criminal");

        setSize(600,650);

        setLayout(null);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        title = new JLabel("Add Criminal Details");
        title.setBounds(170,20,300,30);
        title.setFont(new Font("Arial",Font.BOLD,22));

        idLabel = new JLabel("Criminal ID");
        idLabel.setBounds(50,80,120,25);

        idField = new JTextField();
        idField.setBounds(180,80,250,25);

        nameLabel = new JLabel("Name");
        nameLabel.setBounds(50,120,120,25);

        nameField = new JTextField();
        nameField.setBounds(180,120,250,25);

        ageLabel = new JLabel("Age");
        ageLabel.setBounds(50,160,120,25);

        ageField = new JTextField();
        ageField.setBounds(180,160,250,25);

        genderLabel = new JLabel("Gender");
        genderLabel.setBounds(50,200,120,25);

        genderBox = new JComboBox<>();

        genderBox.addItem("Male");
        genderBox.addItem("Female");
        genderBox.addItem("Other");

        genderBox.setBounds(180,200,250,25);

        crimeLabel = new JLabel("Crime");
        crimeLabel.setBounds(50,240,120,25);

        crimeField = new JTextField();
        crimeField.setBounds(180,240,250,25);

        addressLabel = new JLabel("Address");
        addressLabel.setBounds(50,280,120,25);

        addressArea = new JTextArea();

        JScrollPane scroll = new JScrollPane(addressArea);

        scroll.setBounds(180,280,250,100);

        chooseImageBtn = new JButton("Choose Image");
        chooseImageBtn.setBounds(180,410,150,35);
        chooseImageBtn.addActionListener(e -> {

            JFileChooser chooser = new JFileChooser();

            int result = chooser.showOpenDialog(this);

            if(result == JFileChooser.APPROVE_OPTION){

                File file = chooser.getSelectedFile();

                imagePath = file.getAbsolutePath();

                JOptionPane.showMessageDialog(this,
                        "Image Selected Successfully!");

            }

        });
        saveBtn = new JButton("Save");
        saveBtn.setBounds(120,500,120,40);

        clearBtn = new JButton("Clear");
        clearBtn.setBounds(300,500,120,40);

        add(title);

        add(idLabel);
        add(idField);

        add(nameLabel);
        add(nameField);

        add(ageLabel);
        add(ageField);

        add(genderLabel);
        add(genderBox);

        add(crimeLabel);
        add(crimeField);

        add(addressLabel);
        add(scroll);

        add(chooseImageBtn);

        add(saveBtn);
        add(clearBtn);
        saveBtn.addActionListener(e -> {

            try {

                Criminal criminal = new Criminal();

                criminal.setCriminalId(Integer.parseInt(idField.getText()));
                criminal.setName(nameField.getText());
                criminal.setAge(Integer.parseInt(ageField.getText()));
                criminal.setGender(genderBox.getSelectedItem().toString());
                criminal.setCrime(crimeField.getText());
                criminal.setAddress(addressArea.getText());

                // We'll replace this later with the actual image path
                criminal.setImagePath(imagePath);
                System.out.println("Selected Image Path: " + imagePath);
                System.out.println("Criminal Image Path: " + criminal.getImagePath());
                CriminalDAO dao = new CriminalDAO();

                if (dao.addCriminal(criminal)) {

                    JOptionPane.showMessageDialog(this,
                            "Criminal Added Successfully!");

                } else {

                    JOptionPane.showMessageDialog(this,
                            "Failed to Add Criminal.");

                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this,
                        "Please enter valid data.");

            }

        });
        clearBtn.addActionListener(e -> {

            idField.setText("");
            nameField.setText("");
            ageField.setText("");
            crimeField.setText("");
            addressArea.setText("");
            genderBox.setSelectedIndex(0);

        });
        setVisible(true);
    }
}