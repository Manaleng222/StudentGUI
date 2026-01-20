/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package studentgui;

/**
 *
 * @author USER
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StudentGUI extends JFrame {

    private ArrayList<Student> students = new ArrayList<>();
    private JTextField tfId, tfName, tfSurname, tfAge, tfEmail, tfCourse, tfSearch;
    private JTable table;
    private DefaultTableModel tableModel;

    public StudentGUI() {
        setTitle("Student Management System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center window
        setLayout(new BorderLayout());

        // Top Panel - Form
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        tfId = new JTextField();
        tfName = new JTextField();
        tfSurname = new JTextField();
        tfAge = new JTextField();
        tfEmail = new JTextField();
        tfCourse = new JTextField();

        formPanel.add(new JLabel("ID:"));
        formPanel.add(tfId);
        formPanel.add(new JLabel("Name:"));
        formPanel.add(tfName);
        formPanel.add(new JLabel("Surname:"));
        formPanel.add(tfSurname);
        formPanel.add(new JLabel("Age:"));
        formPanel.add(tfAge);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(tfEmail);
        formPanel.add(new JLabel("Course:"));
        formPanel.add(tfCourse);

        JButton addBtn = new JButton("Add Student");
        JButton deleteBtn = new JButton("Delete Student");
        JButton searchBtn = new JButton("Search Student");

        formPanel.add(addBtn);
        formPanel.add(deleteBtn);

        add(formPanel, BorderLayout.NORTH);

        // Center Panel - Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Surname", "Age", "Email", "Course"}, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom Panel - Search
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tfSearch = new JTextField(20);
        bottomPanel.add(new JLabel("Search by Name:"));
        bottomPanel.add(tfSearch);
        bottomPanel.add(searchBtn);

        add(bottomPanel, BorderLayout.SOUTH);

        // Button Actions
        addBtn.addActionListener(e -> addStudent());
        deleteBtn.addActionListener(e -> deleteStudent());
        searchBtn.addActionListener(e -> searchStudent());

        setVisible(true);
    }

    private void addStudent() {
        try {
            int id = Integer.parseInt(tfId.getText());
            String name = tfName.getText();
            String surname = tfSurname.getText();
            int age = Integer.parseInt(tfAge.getText());
            String email = tfEmail.getText();
            String course = tfCourse.getText();

            if (age < 16) {
                JOptionPane.showMessageDialog(this, "Student must be at least 16 years old.");
                return;
            }

            Student student = new Student(id, name, surname, age, email, course);
            students.add(student);

            tableModel.addRow(new Object[]{id, name, surname, age, email, course});
            JOptionPane.showMessageDialog(this, "Student added successfully!");

            tfId.setText(""); tfName.setText(""); tfSurname.setText(""); tfAge.setText(""); tfEmail.setText(""); tfCourse.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for ID and Age.");
        }
    }

    private void deleteStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String name = (String) tableModel.getValueAt(selectedRow, 1);
            Student student = findStudent(name);
            if (student != null) {
                students.remove(student);
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Student deleted successfully!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a student to delete from the table.");
        }
    }

    private void searchStudent() {
        String searchName = tfSearch.getText();
        Student student = findStudent(searchName);
        if (student != null) {
            JOptionPane.showMessageDialog(this, student.toString());
        } else {
            JOptionPane.showMessageDialog(this, "Student not found!");
        }
    }

    private Student findStudent(String name) {
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentGUI::new);
    }
}