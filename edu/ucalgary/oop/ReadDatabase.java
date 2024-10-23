/*
 * version: 8.4
 * since: 1.0
 *
 */

package edu.ucalgary.oop;
import java.sql.*;
import java.util.HashMap;

public class ReadDatabase{
    private Connection dbConnect;
    private ResultSet results;
    public ReadDatabase(){
        createConnection();
    }

    public ReadDatabase(String link, String username, String password){
        createConnection(link, username, password);
    }

    public void createConnection(String link, String username, String password){
        try{
            dbConnect = DriverManager.getConnection(link, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createConnection(){
        try{
            dbConnect = DriverManager.getConnection("jdbc:mysql://localhost/EWR", "oop", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public HashMap<Integer, Animal> readAnimals() {
        //Returns Data From ANIMALS in HashMap
        HashMap<Integer, Animal> animalList = new HashMap<>();
        try {
            Statement myStmt = dbConnect.createStatement();

            results = myStmt.executeQuery("SELECT * FROM ANIMALS");
            while(results.next()){
                Animal theAnimal = new Animal(results.getInt("AnimalID"),
                        results.getString("AnimalNickname"), results.getString("AnimalSpecies"));
                animalList.put(results.getInt("AnimalID"), theAnimal);
            }
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return animalList;
    }
    public HashMap<Integer, Treatment> readTreatments() {
        //Returns Data From TREATMENTS in HashMap
        HashMap<Integer, Treatment> treatmentList = new HashMap<>();
        try {
            Statement myStmt = dbConnect.createStatement();

            results = myStmt.executeQuery("SELECT * FROM TREATMENTS");
            while(results.next()) {
                Treatment theTreatment = new Treatment(results.getInt("TreatmentID"), results.getInt("AnimalID"),
                        results.getInt("TaskID"), results.getInt("StartHour"));
                treatmentList.put(results.getInt("TreatmentID"), theTreatment);
            }
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return treatmentList;
    }
    public HashMap<Integer, Task> readTasks() {
        //Returns Data From TASKS in HashMap
        HashMap<Integer, Task> taskList = new HashMap<>();
        try {
            Statement myStmt = dbConnect.createStatement();

            results = myStmt.executeQuery("SELECT * FROM TASKS");
            while(results.next()){
                Task theTask = new Task(results.getInt("TaskID"), results.getString("Description"),
                        results.getInt("Duration"), results.getInt("MaxWindow"));
                taskList.put(results.getInt("TaskID"), theTask);
            }
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return taskList;

    }

}