/*
 * version: 1.2
 * since: 1.0
 *
 */

package edu.ucalgary.oop;


public class Treatment {
    private int treatmentID;
    private int animalID;
    private int taskID;
    private int startHour;
    public Treatment(int treatmentID, int animalID, int taskID, int startHour) {
        this.treatmentID = treatmentID;
        this.animalID = animalID;
        this.taskID = taskID;
        this.startHour = startHour;
    }

    public int calculateEndHour(){
        int end_hr = startHour + AnimalRecords.getTaskList().get(taskID).getMaxWindow();
        return end_hr;
    }

    public void setTreatmentID(int treatmentID){
        this.treatmentID = treatmentID;
    }
    public void setAnimalID(int animalID){
        this.animalID = animalID;
    }
    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }
    public void setStartHour(int startHour){
        this.startHour = startHour;
    }
    public int getTreatmentID(){
        return this.treatmentID;
    }
    public int getAnimalID(){
        return this.animalID;
    }
    public int getTaskID(){
        return this.taskID;
    }
    public int getStartHour(){
        return this.startHour;
    }
}