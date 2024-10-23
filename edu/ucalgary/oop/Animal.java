/*
 * version: 2.4
 * since: 1.0
 *
 */

package edu.ucalgary.oop;

import java.util.Objects;

public class Animal {
    private int animalID;
    private String animalNickname;
    private String animalType;
    private Task[] tasks;
    private Treatment[] treatments;
    private String animalSpecies;
    private int feedingStartHour;
    private int feedPrepDuration;
    private final int FEEDINGDURATION = 5;
    private int cageCleanDuration = 5;
    private boolean beenFed = false;
    private boolean cageCleaned = false;
    private boolean isOrphan = false;
    public Animal(int animalID, String animalNickname, String animalType){
        this.animalID = animalID;
        this.animalNickname = animalNickname;
        this.animalType = animalType;
        switch (animalType) {
            case "coyote":
                this.feedPrepDuration = 10;
                this.animalSpecies = "crepuscular";
                break;
            case "porcupine":
                this.feedPrepDuration = 0;
                this.cageCleanDuration = 10;
                this.animalSpecies = "crepuscular";
                break;
            case "fox":
                this.feedPrepDuration = 5;
                this.animalSpecies = "nocturnal";
                break;
            case "raccoon":
                this.feedPrepDuration = 0;
                this.animalSpecies = "nocturnal";
                break;
            case "beaver":
                this.feedPrepDuration = 0;
                this.animalSpecies = "diurnal";
                break;
            default:
                //Add error for animalType not supported
                break;
        }
        switch (Objects.requireNonNull(animalSpecies)) {
            case "crepuscular":
                this.feedingStartHour = 19;
                break;
            case "nocturnal":
                this.feedingStartHour = 0;
                break;
            case "diurnal":
                this.feedingStartHour = 8;
                break;
        }
    }

    public void setOrphan(boolean orphan) {
        isOrphan = orphan;
    }

    public int calculateFeedEndHour(){
        return this.feedingStartHour + 3;
    }

    public int getAnimalID() {
        return animalID;
    }
    public String getAnimalNickname() {
        return animalNickname;
    }
    public String getAnimalSpecies() {
        return animalSpecies;
    }
    public Task[] getTasks() {
        return tasks;
    }
    public Treatment[] getTreatments() {
        return treatments;
    }

    public void setAnimalID(int animalID) {
        this.animalID = animalID;
    }

    public void setAnimalNickname(String animalNickname) {
        this.animalNickname = animalNickname;
    }

    public void setanimalType(String animalType) {
        this.animalType = animalType;
    }

    public void setTasks(Task[] tasks) {
        this.tasks = tasks;
    }

    public void setTreatments(Treatment[] treatments) {
        this.treatments = treatments;
    }

    public void setAnimalSpecies(String animalSpecies) {
        this.animalSpecies = animalSpecies;
    }

    public int getCageCleanDuration() {
        return cageCleanDuration;
    }

    public int getFeedingDuration() {
        return this.FEEDINGDURATION;
    }

    public int getFeedingStartHour() {
        return feedingStartHour;
    }

    public int getFeedPrepDuration() {
        return feedPrepDuration;
    }

    public boolean isBeenFed() {
        return beenFed;
    }

    public String getAnimalType() {
        return animalType;
    }

    public boolean isCageCleaned() {
        return cageCleaned;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public void setBeenFed(boolean beenFed) {
        this.beenFed = beenFed;
    }

    public void setCageCleanDuration(int cageCleanDuration) {
        this.cageCleanDuration = cageCleanDuration;
    }

    public void setCageCleaned(boolean cageCleaned) {
        this.cageCleaned = cageCleaned;
    }

    public void setFeedingStartHour(int feedingStartHour) {
        this.feedingStartHour = feedingStartHour;
    }

    public void setFeedPrepDuration(int feedPrepDuration) {
        this.feedPrepDuration = feedPrepDuration;
    }
    public boolean isOrphanAnimal(){
        return this.isOrphan;
    }
}