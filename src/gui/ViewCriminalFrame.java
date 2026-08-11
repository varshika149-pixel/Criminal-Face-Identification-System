package gui;

import dao.CriminalDAO;
import model.Criminal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewCriminalFrame extends JFrame {

    JTable table;
    DefaultTableModel model;

    public ViewCriminalFrame() {

        setTitle("View All Criminals");
        setSize(900,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {
                "ID",
                "Name",
                "Age",
                "Gender",
                "Crime",
                "Address"
        };

        model = new DefaultTableModel(columns,0);

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        loadData();

        setVisible(true);
    }

    private void loadData() {

        CriminalDAO dao = new CriminalDAO();

        List<Criminal> criminals = dao.getAllCriminals();

        for(Criminal c : criminals){

            model.addRow(new Object[]{
                    c.getCriminalId(),
                    c.getName(),
                    c.getAge(),
                    c.getGender(),
                    c.getCrime(),
                    c.getAddress()
            });

        }

    }

    public static void main(String[] args) {

        new ViewCriminalFrame();

    }
}