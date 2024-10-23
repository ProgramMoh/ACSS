/*
 * version: 25.4
 * since: 1.0
 *
 */

package edu.ucalgary.oop;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.util.regex.*;


public class GUI extends JFrame implements ActionListener {
    private JButton startProgramButton, dataButton, scheduleButton;
    private DefaultTableModel model;

    public static void main(String[] args) throws IllegalScheduleException {
        new GUI();
    }
    private JPanel schedulePanel;
    public GUI() {
        // Set up the main frame
        setTitle("Schedule Builder");
        setSize(800, 600);
        Color customColor = new Color(191, 193, 255);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(customColor);
        Font font = new Font("Calibri Light", Font.LAYOUT_LEFT_TO_RIGHT, 16);
        Font labelFont = new Font("Calibri Light", Font.LAYOUT_LEFT_TO_RIGHT, 14);


        // Set up the start button and some customizations
        JLabel messageLabel = new JLabel("<html>Please click Schedule button if you want to view the generic database's schedule! (based on credentials in ReadDatabase.java)" +
                "<br><br>Please click on Change SQL Database button to input your credentials to generate a schedule!<html>");
        messageLabel.setFont(labelFont);
        startProgramButton = new JButton("Click Here to Start Program!");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 150, 150));
        startProgramButton.addActionListener(this);
        startProgramButton.setBackground(customColor);
        startProgramButton.setFont(font);
        add(startProgramButton, BorderLayout.CENTER);
        buttonPanel.add(startProgramButton);
        buttonPanel.setBackground(customColor);
        add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.add(messageLabel, BorderLayout.NORTH);

        // Set up the data button and customizing it
        dataButton = new JButton("Change SQL Database");
        dataButton.setVisible(false);
        dataButton.addActionListener(this);
        dataButton.setFont(font);
        add(dataButton, BorderLayout.NORTH);

        // Set up the schedule button and customizing it
        scheduleButton = new JButton("Schedule");
        scheduleButton.setVisible(false);
        scheduleButton.addActionListener(this);
        scheduleButton.setFont(font);
        add(scheduleButton, BorderLayout.SOUTH);

        JPanel optionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 120, 120)); // create a new JPanel with FlowLayout
        optionsPanel.setBackground(customColor);
        optionsPanel.add(dataButton); // add dataButton to the panel
        optionsPanel.add(scheduleButton); // add scheduleButton to the panel
        add(optionsPanel, BorderLayout.SOUTH); // add the new panel to the SOUTH of the main panel

        // Create the table model
        String[] headers = {"Time", "Task/Treatment", "Backup Volunteer"};
        model = new DefaultTableModel(headers, 0);

        // Show the GUI
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startProgramButton) {
            startProgramButton.setVisible(false);
            dataButton.setVisible(true);
            scheduleButton.setVisible(true);
        } else if (e.getSource() == dataButton) {
            // Changing SQL Database button functionality
            JLabel label = new JLabel("Please make sure your database follows the same format for accurate schedule result and representation", SwingConstants.CENTER);
            JPanel inputPanel = new JPanel(new GridLayout(0, 1));
            JTextField urlField = new JTextField(); //text field to get url
            JTextField userField = new JTextField();//text field to get username
            JTextField passField = new JTextField();//text field to get password
            inputPanel.add(label);
            inputPanel.add(new JLabel("Enter URL:"));
            inputPanel.add(urlField);
            inputPanel.add(new JLabel("Enter username:"));
            inputPanel.add(userField);
            inputPanel.add(new JLabel("Enter password:"));
            inputPanel.add(passField);
            int result = JOptionPane.showConfirmDialog(null, inputPanel, "Update!", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) { //if user submits SQL database credentials then make a new schedule and view it
                String url = urlField.getText();
                String username = userField.getText();
                String password = passField.getText();
                try {
                    ReadDatabase myDatabase = new ReadDatabase(url, username, password);
                    HashMap<Integer, Treatment> treatmentList = myDatabase.readTreatments();
                    HashMap<Integer, Animal> animalList = myDatabase.readAnimals();
                    HashMap<Integer, Task> taskList = myDatabase.readTasks();

                    new AnimalRecords(animalList, treatmentList, taskList);
                    Schedule schedule = new Schedule();
                    String theString = schedule.toString();
                    PrintText.log(theString);
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader("Schedule.txt"));
                        String line; // string that holds the lines read
                        String time = "";
                        int counter = 0;
                        ArrayList<String> backupTime = new ArrayList<>();
                        Pattern timePattern = Pattern.compile("\\d{1,2}:\\d{2}"); //pattern to match and find time stamp
                        while ((line = reader.readLine()) != null) {
                            Matcher timeMatcher = timePattern.matcher(line);// match the string of time to the regex
                            if ((line.matches("\\d{1,2}:\\d{2}") || timeMatcher.find()) && !line.endsWith(" [+ backup volunteer]")) { //if time stamp only and no backup volunteer needed
                                // This is a time stamp
                                time = line.substring(0,5);
                                time = time.trim();

                                String minute = line.substring(time.length());
                                model.addRow(new Object[]{time, "Time Available :" + minute, "Not needed"});
                                time = ""; //empty the time string
                                minute = "";
                            } else if (line.startsWith("*")) { //if it is a task
                                // This is a task
                                String task = line.substring(2);// assign the task without the * and the leading space
                                model.addRow(new Object[]{time, task, ""});
                            } else if (line.endsWith(" [+ backup volunteer]")) { //if its a time stamp and requires a volunteer
                                time = line.substring(0,5);// time takes a temp string of the time stamp
                                time = time.trim(); //trim any trailing white space for times that are 4 chars long, ex 3:00
                                String minute = line.substring(time.length(), time.length()+3);
                                minute = minute.trim();
                                backupTime.add(time);
                                model.addRow(new Object[]{time, "Time Available (in minutes):" + minute, "Needed"});
                                time = ""; //empty time string
                                counter++;
                            }
                        }
                        if(counter > 0) {
                            for(int i = 0; i < backupTime.size(); i++) {
                                JOptionPane.showMessageDialog(this, "Please confirm that the backup volunteer is contacted for: " + backupTime.get(i), "Volunteer confirmation", JOptionPane.INFORMATION_MESSAGE);
                                if (JOptionPane.OK_OPTION == 1){
                                    continue;
                                }
                            }
                        }
                        reader.close();
                        JTable table = new JTable(model); // make table using the table model done
                        table.getColumnModel().getColumn(2).setCellRenderer(new CustomTableCellRenderer());//change the color of cells
                        table.getColumnModel().getColumn(1).setCellRenderer(new CustomTableCellRenderer());//change the color of cells
                        table.getColumnModel().getColumn(0).setCellRenderer(new CustomTableCellRenderer());// change the color of cells
                        table.getColumnModel().getColumn(1).setPreferredWidth(450); // set the preferred width of the second column to 300 pixels
                        JScrollPane scrollPane = new JScrollPane(table);
                        scrollPane.setPreferredSize(new Dimension(800, 600));//increase size to view table
                        JOptionPane.showMessageDialog(null, scrollPane);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                catch(IllegalScheduleException schedule){
                    JOptionPane.showMessageDialog(this, schedule.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                catch (Exception anErr) {
                    JOptionPane.showMessageDialog(this, "An error occurred when generating schedule, ensure correct format ", "Error", JOptionPane.ERROR_MESSAGE);
                }
                model = new DefaultTableModel(new String[]{"Time", "Task/Treatment", "Backup Volunteer"}, 0);
            }
        } else if (e.getSource() == scheduleButton) {
            // schedule button functionality
            try {
                ReadDatabase myDatabase = new ReadDatabase();
                HashMap<Integer, Treatment> treatmentList = myDatabase.readTreatments();
                HashMap<Integer, Animal> animalList = myDatabase.readAnimals();
                HashMap<Integer, Task> taskList = myDatabase.readTasks();

                new AnimalRecords(animalList, treatmentList, taskList);
                Schedule schedule = new Schedule();
                String theString = schedule.toString();
                PrintText.log(theString);
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("Schedule.txt"));
                    String line; // string that holds the lines read
                    String time = "";
                    int counter = 0;
                    ArrayList<String> backupTime = new ArrayList<>();
                    Pattern timePattern = Pattern.compile("\\d{1,2}:\\d{2}"); //pattern to match and find time stamp
                    while ((line = reader.readLine()) != null) {
                        Matcher timeMatcher = timePattern.matcher(line);// match the string of time to the regex
                        if ((line.matches("\\d{1,2}:\\d{2}") || timeMatcher.find()) && line.length() <=20) { //if time stamp only and no backup volunteer needed
                            // This is a time stamp
                            time = line.substring(0,5);
                            time = time.trim();

                            String minute = line.substring(time.length());
                            model.addRow(new Object[]{time, "Time Available (in minutes):" + minute, "Not needed"});
                            time = ""; //empty the time string
                            minute = "";
                        } else if (line.startsWith("*")) { //if it is a task
                            // This is a task
                            String task = line.substring(2);// assign the task without the * and the leading space
                            model.addRow(new Object[]{time, task, ""});
                        } else if (line.endsWith(" [+ backup volunteer]")) { //if its a time stamp and requires a volunteer
                            time = line.substring(0,5);// time takes a temp string of the time stamp
                            time = time.trim(); //trim any trailing white space for times that are 4 chars long, ex 3:00
                            String minute = line.substring(time.length(), time.length()+3);
                            minute = minute.trim();
                            backupTime.add(time);
                            model.addRow(new Object[]{time, "Time Available (in minutes):" + minute, "Needed"});
                            time = ""; //empty time string
                            counter++;
                        }
                    }
                    if(counter > 0) {
                        for(int i = 0; i < backupTime.size(); i++) {
                            JOptionPane.showMessageDialog(this, "Please confirm that the backup volunteer is contacted for: " + backupTime.get(i), "Volunteer confirmation", JOptionPane.INFORMATION_MESSAGE);
                            if (JOptionPane.OK_OPTION == 1){
                                continue;
                            }
                        }
                    }
                    reader.close();
                    JTable table = new JTable(model); // make table using the table model done
                    table.getColumnModel().getColumn(2).setCellRenderer(new CustomTableCellRenderer());//change the color of cells
                    table.getColumnModel().getColumn(1).setCellRenderer(new CustomTableCellRenderer());//change the color of cells
                    table.getColumnModel().getColumn(0).setCellRenderer(new CustomTableCellRenderer());// change the color of cells
                    table.getColumnModel().getColumn(1).setPreferredWidth(450); // set the preferred width of the second column to 300 pixels
                    JScrollPane scrollPane = new JScrollPane(table);
                    scrollPane.setPreferredSize(new Dimension(800, 600));//increase size to view table
                    JOptionPane.showMessageDialog(null, scrollPane);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            catch(IllegalScheduleException schedule){
                JOptionPane.showMessageDialog(this, schedule.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            catch (Exception anErr) {
                JOptionPane.showMessageDialog(this, "An error occurred when generating schedule, ensure correct format ", "Error", JOptionPane.ERROR_MESSAGE);
            }
            model = new DefaultTableModel(new String[]{"Time", "Task/Treatment", "Backup Volunteer"}, 0);
        }

    }

    class CustomTableCellRenderer extends DefaultTableCellRenderer {
        // class to customize the cells and color coordinate them
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            Component original = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            //custom colors
            Color customCyan = new Color(102, 255, 199);
            Color customPink = new Color(255, 140, 182);
            if (value != null && value.toString().matches("\\d{1,2}:\\d{2}")) {//if its a time stamp
                original.setBackground(customCyan); // set background color for rows with time value
            }else {
                original.setBackground(Color.LIGHT_GRAY);
            }
            if (column == 1) { // if its the cell next to time stamp
                if ((value.toString().startsWith("Time"))) {
                    ((JLabel) original).setHorizontalAlignment(SwingConstants.CENTER);
                    original.setBackground(customCyan); // set background color for rows with time value
                }else {
                    ((JLabel) original).setHorizontalAlignment(SwingConstants.LEFT);
                }
            }
            if (column == 2) { //if its the backup volunteer column
                if (value.equals("Needed")) { //if needed
                    original.setBackground(customPink);
                }
                else if (value.equals("Not needed")){ //if not needed
                    original.setBackground(customCyan);
                }else{ //else paint everything to the main background color
                    original.setBackground(Color.LIGHT_GRAY);
                }
            }

            return original;
        }
    }
}