/*
 * version: 3.0
 * since: 1.0
 *
 */

package edu.ucalgary.oop;

public class Task {
    private int taskID;
    private String description;
    private int duration;
    private int maxWindow;
    public Task(int taskID, String description, int duration, int maxWindow ){
        this.taskID = taskID;
        this.description = description;
        this.duration = duration;
        this.maxWindow = maxWindow;
    }
    public int getDuration(){
        return this.duration;
    }

    public int getTaskID() {
        return taskID;
    }

    public int getMaxWindow() {
        return maxWindow;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setMaxWindow(int maxWindow) {
        this.maxWindow = maxWindow;
    }
}