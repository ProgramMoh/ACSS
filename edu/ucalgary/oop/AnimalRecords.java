/*
 * version: 3.2
 * since: 1.0
 *
 */

package edu.ucalgary.oop;

import java.util.HashMap;
import java.util.Map;

public class AnimalRecords {
    private static HashMap<Integer, Animal> animalList;
    private static HashMap<Integer, Treatment> treatmentList;
    private static HashMap<Integer, Task> taskList;

    public AnimalRecords(HashMap<Integer, Animal> animalList, HashMap<Integer, Treatment> treatmentList, HashMap<Integer, Task> taskList){
        AnimalRecords.animalList = animalList;
        AnimalRecords.treatmentList = treatmentList;
        AnimalRecords.taskList = taskList;
        if (treatmentList.containsKey(1)){
            AnimalRecords.animalList.get(treatmentList.get(1).getAnimalID()).setOrphan(true);
        }
    }

    public static HashMap<Integer, Animal> getAnimalList() {
        return animalList;
    }

    public static HashMap<Integer, Task> getTaskList() {
        return taskList;
    }

    public static void setAnimalList(HashMap<Integer, Animal> animalList) {
        AnimalRecords.animalList = animalList;
    }

    public static void setTaskList(HashMap<Integer, Task> taskList) {
        AnimalRecords.taskList = taskList;
    }

    public static void setTreatmentList(HashMap<Integer, Treatment> treatmentList) {
        AnimalRecords.treatmentList = treatmentList;
    }

    public static HashMap<Integer, Treatment> getTreatmentList() {
        return treatmentList;
    }
}